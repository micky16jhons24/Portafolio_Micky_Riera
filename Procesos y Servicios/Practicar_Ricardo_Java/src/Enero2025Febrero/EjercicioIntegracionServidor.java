package Enero2025Febrero;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.Base64;
import java.util.concurrent.*;

public class EjercicioIntegracionServidor {
    private static final int PUERTO = 12345; // Puerto donde el servidor escuchará conexiones
    private static final int NUM_HILOS = 3; // Número de hilos concurrentes para ejecutar comandos
    private static final BlockingQueue<Runnable> colaTareas = new LinkedBlockingQueue<>(); // Cola de tareas para ejecución ordenada
    private static final ConcurrentHashMap<String, Boolean> usuariosEnEjecucion = new ConcurrentHashMap<>(); // Control de usuarios en ejecución
    private static final ConcurrentHashMap<String, String> resultados = new ConcurrentHashMap<>(); // Almacena resultados firmados
    private static KeyPair keyPair; // Par de claves para firma digital
    private static final String DELIMITADOR = "||FIRMA:"; // Delimitador para separar el resultado y la firma

    public static void main(String[] args) {
        generarClaves(); // Genera las claves RSA para la firma digital
        ExecutorService ejecutor = Executors.newFixedThreadPool(NUM_HILOS); // Crea un pool de hilos con NUM_HILOS
        new Thread(new ProcesadorTareas(ejecutor)).start(); // Inicia el procesador de tareas en otro hilo

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);
            while (true) {
                Socket cliente = serverSocket.accept(); // Espera conexiones de clientes
                new Thread(new ManejadorCliente(cliente)).start(); // Crea un nuevo hilo para manejar cada cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase que procesa las tareas en la cola de ejecución
    static class ProcesadorTareas implements Runnable {
        private final ExecutorService ejecutor;
        public ProcesadorTareas(ExecutorService ejecutor) {
            this.ejecutor = ejecutor;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable tarea = colaTareas.take(); // Toma una tarea de la cola
                    ejecutor.execute(tarea); // La ejecuta en un hilo disponible
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Clase que maneja la comunicación con cada cliente
    static class ManejadorCliente implements Runnable {
        private final Socket cliente;
        public ManejadorCliente(Socket cliente) {
            this.cliente = cliente;
        }
        @Override
        public void run() {
            try (
                DataInputStream dis = new DataInputStream(cliente.getInputStream());
                DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
            ) {
                String mensaje = dis.readUTF(); // Lee el mensaje enviado por el cliente
                if (mensaje == null) {
                    System.out.println("Cliente desconectado sin enviar datos.");
                    return;
                }

                // Si el cliente solicita la clave pública, se la enviamos
                if (mensaje.equals("CLAVE_PUBLICA")) {
                    dos.writeUTF(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
                    return;
                }

                // Si el cliente consulta un resultado, se lo enviamos
                if (mensaje.startsWith("CHECK@")) {
                    String usuario = mensaje.substring(6);
                    String resultadoFirmado = resultados.getOrDefault(usuario, "PENDIENTE");

                    // 🔍 Depuración: imprimir el resultado enviado al cliente
                    System.out.println("Enviando resultado al cliente: " + resultadoFirmado);
                    dos.writeUTF(resultadoFirmado);
                    return;
                }

                // Dividir el mensaje en usuario y comando
                String[] partes = mensaje.split("@#@");
                if (partes.length < 2) {
                    System.out.println("Formato incorrecto recibido del cliente.");
                    return;
                }
                String usuario = partes[0];
                String comando = partes[1];

                // Si el usuario ya tiene un comando en ejecución, se rechaza
                if (usuariosEnEjecucion.putIfAbsent(usuario, true) != null) {
                    dos.writeUTF("ERROR: El usuario ya está ejecutando un comando.");
                    return;
                }

                // Agregar la tarea de ejecución a la cola
                colaTareas.add(new TareaEjecucion(usuario, comando));
                dos.writeUTF("Petición recibida, revisa más tarde.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Clase que ejecuta los comandos en un hilo separado
    static class TareaEjecucion implements Runnable {
        private final String usuario;
        private final String comando;
        public TareaEjecucion(String usuario, String comando) {
            this.usuario = usuario;
            this.comando = comando;
        }
        @Override
        public void run() {
            try {
                // Ejecutar el comando en la terminal de Windows
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
                pb.redirectErrorStream(true);
                Process proceso = pb.start();

                // Leer la salida del comando
                BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                proceso.waitFor();

                // Asegurar que el resultado no esté vacío
                String salidaTexto = sb.toString().trim();
                if (salidaTexto.isEmpty()) {
                    salidaTexto = "Comando ejecutado sin salida.";
                }

                // Firmar el resultado del comando
                String firmado = firmarResultado(salidaTexto);

                // Depuración: imprimir el resultado firmado antes de almacenarlo
                System.out.println("Resultado almacenado en servidor: " + firmado);

                // Almacenar el resultado firmado en el mapa de resultados
                resultados.put(usuario, firmado);
                usuariosEnEjecucion.remove(usuario);
            } catch (IOException | InterruptedException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
                e.printStackTrace();
            }
        }
    }

    // Generar el par de claves RSA
    private static void generarClaves() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            keyPair = keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Firma el resultado de la ejecución de un comando con la clave privada.
     * Devuelve una cadena con el formato:
     * Base64(salida) + DELIMITADOR + Base64(firma)
     */
    private static String firmarResultado(String resultado) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature firma = Signature.getInstance("SHA256withRSA"); // Algoritmo de firma digital
        firma.initSign(keyPair.getPrivate()); // Inicializa con la clave privada
        firma.update(resultado.getBytes()); // Firma los datos
        byte[] firmaBytes = firma.sign();

        // Codificar la salida y la firma en Base64
        String salidaEncoded = Base64.getEncoder().encodeToString(resultado.getBytes());
        String firmaEncoded = Base64.getEncoder().encodeToString(firmaBytes);

        // 🔍 Depuración: eliminar saltos de línea en Base64
        salidaEncoded = salidaEncoded.replaceAll("\n", "").replaceAll("\r", "");
        firmaEncoded = firmaEncoded.replaceAll("\n", "").replaceAll("\r", "");

        return salidaEncoded + DELIMITADOR + firmaEncoded;
    }
    
    
}


