package UTR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

// Servidor que maneja múltiples clientes y ejecuta comandos de forma remota
public class Servidor {

    private static final ConcurrentMap<String, String> taskResults = new ConcurrentHashMap<>();
    private static final Queue<String[]> commandQueue = new ConcurrentLinkedQueue<>();
    private static final int NUM_THREADS = 3;

    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Iniciar hilos para ejecutar comandos
            for (int i = 0; i < NUM_THREADS; i++) {
                new Thread(new HiloEjecutorComandos()).start();
            }

            // Aceptar conexiones de clientes
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());
                new Thread(new ServidorAtiende(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Clase interna para manejar clientes
    static class ServidorAtiende implements Runnable {
        private final Socket cliente;

        public ServidorAtiende(Socket cliente) {
            this.cliente = cliente;
        }

        @Override
        public void run() {
            try (InputStream entrada = cliente.getInputStream();
                 BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
                 OutputStream salida = cliente.getOutputStream();
                 PrintWriter escritor = new PrintWriter(salida, true)) {

                String mensaje = lector.readLine();
                if (mensaje.startsWith("POLL@")) {
                    // Si es una consulta de respuesta, la devolvemos
                    String taskId = mensaje.split("@")[1];
                    String resultado = taskResults.getOrDefault(taskId, "NoExisteRespuesta");
                    escritor.println(resultado);
                } else {
                    // Agregar el comando a la cola para su ejecución
                    String[] parts = mensaje.split("@#@");
                    if (parts.length == 2) {
                        String usuario = parts[0];
                        String comando = parts[1];
                        String taskId = usuario + "_" + System.currentTimeMillis();
                        taskResults.put(taskId, "Pendiente");
                        añadirCola(taskId, comando);
                        escritor.println(taskId);
                    } else {
                        escritor.println("Formato incorrecto. Usa: usuario@#@Comando");
                    }
                }
                cliente.close();
            } catch (IOException e) {
                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
            }
        }
    }

    // Clase interna para ejecutar los comandos
    static class HiloEjecutorComandos implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    // Extraer comando de la cola
                    String[] commandData = SacarCola();
                    if (commandData != null) {
                        String taskId = commandData[0];
                        String comando = commandData[1];
                        System.out.println("Ejecutando comando: " + comando);

                        // Ejecutar comando en el sistema
                        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", comando);
                        builder.redirectErrorStream(true);
                        Process proceso = builder.start();

                        BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                        StringBuilder resultado = new StringBuilder();
                        String linea;
                        while ((linea = salida.readLine()) != null) {
                            resultado.append(linea).append(System.lineSeparator());
                        }
                        proceso.waitFor();

                        // Firmar respuesta y almacenarla
                        String firmado = signResponse(resultado.toString());
                        taskResults.put(taskId, resultado.toString() + "@firma@" + firmado);
                        System.out.println("Comando ejecutado con éxito. ID: " + taskId);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   

    // Método para firmar la respuesta del comando
    private static String signResponse(String response) {
        try {
            String privateKeyContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\samee\\eclipse-workspace\\Communication_Exercise_ChatGPT\\src\\communication\\private_key_pkcs8.pem")));

            privateKeyContent = privateKeyContent
                    .replaceAll("\\r\\n", "")
                    .replaceAll("\\n", "")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .trim();

            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyContent);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(response.getBytes());

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(hash);

            byte[] digitalSignature = signature.sign();
            return Base64.getEncoder().encodeToString(digitalSignature);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    // Método sincronizado para añadir un comando a la cola
    public static synchronized void añadirCola(String taskId, String comando) {
        commandQueue.add(new String[]{taskId, comando});
        
        Servidor.class.notify(); // Notificamos a los hilos en espera
    }

    

    // Método sincronizado para extraer un comando de la cola
    public static synchronized String[] SacarCola() throws InterruptedException {
        while (commandQueue.isEmpty()) {
            Servidor.class.wait(); // Esperamos hasta que haya comandos en la cola
        }
        return commandQueue.poll(); // Saca el primer elemento de la cola
    }
}
