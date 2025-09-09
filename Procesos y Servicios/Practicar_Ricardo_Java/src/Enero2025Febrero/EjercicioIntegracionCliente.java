package Enero2025Febrero;

import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class EjercicioIntegracionCliente {
    private static PublicKey clavePublica; // Clave pública del servidor para verificar la firma
    private static final String DELIMITADOR = "||FIRMA:"; // Separador entre el resultado y la firma

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Scanner sc = new Scanner(System.in);
        String ipServidor = "127.0.0.1"; // Dirección IP del servidor
        int puerto = 12345; // Puerto donde el servidor está escuchando

        //Conectar al servidor para recibir la clave pública
        System.out.println("Conectando al servidor para recibir la clave pública...");
        Socket socket = new Socket(ipServidor, puerto);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        // Solicitar la clave pública
        dos.writeUTF("CLAVE_PUBLICA");
        String clavePublicaStr = dis.readUTF();
        
        // Decodificar la clave pública recibida en Base64
        byte[] claveBytes = Base64.getDecoder().decode(clavePublicaStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(claveBytes);
        clavePublica = keyFactory.generatePublic(keySpec);
        
        socket.close();
        System.out.println("Clave pública recibida con éxito.");

        // Solicitar al usuario su nombre y el comando a ejecutar
        System.out.println("Dame el nombre del cliente:");
        String nombre = sc.nextLine();
        System.out.println("Dame el comando a ejecutar:");
        String comando = sc.nextLine();

        // Enviar el comando al servidor
        System.out.println("Enviando comando al servidor...");
        socket = new Socket(ipServidor, puerto);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());

        // Enviar el mensaje con el formato "nombre@#@comando"
        dos.writeUTF(nombre + "@#@" + comando);
        System.out.println("Comando enviado. Esperando confirmación...");

        // Recibir la confirmación del servidor
        System.out.println("=== Respuesta del Servidor ===");
        System.out.println(dis.readUTF()); // Mensaje de confirmación
        socket.close();

        // Hacer polling: consultar cada 5 segundos hasta obtener el resultado firmado
        String resultado = "";
        while (true) {
            try {
                Thread.sleep(5000); // Espera 5 segundos antes de preguntar de nuevo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Solicitar el resultado al servidor
            socket = new Socket(ipServidor, puerto);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            dos.writeUTF("CHECK@" + nombre);//envia el mensaje con formto
            resultado = dis.readUTF();
            socket.close();

            System.out.println("Respuesta recibida del servidor: " + resultado);

            // Si el resultado ya no es "PENDIENTE", salir del bucle
            if (!resultado.equals("PENDIENTE")) {
                break;
            }
        }

        // Mostrar el resultado recibido
        System.out.println("=== Resultado del Servidor ===");

        // Verificar que el mensaje contiene el delimitador "||FIRMA:"
        if (!resultado.contains(DELIMITADOR)) {
            System.out.println("[ERROR] El mensaje recibido no contiene la firma esperada.");
            System.out.println("Mensaje recibido completo: " + resultado); // 🔍 Depuración adicional
            return;
        }

        // Separar el resultado y la firma usando el delimitador
        String[] partes = resultado.split("\\|\\|FIRMA:");
        if (partes.length < 2) {
            System.out.println("[ERROR] No se pudo separar correctamente la firma del resultado.");
            System.out.println("Mensaje recibido completo: " + resultado); // 🔍 Depuración adicional
            return;
        }

        String salidaBase64 = partes[0].trim(); // Parte del resultado en Base64
        String firmaBase64 = partes[1].trim(); // Parte de la firma en Base64

        // Depuración: imprimir las partes separadas
        System.out.println("Parte Base64 (resultado): " + salidaBase64);
        System.out.println("Parte Base64 (firma): " + firmaBase64);

        // Decodificar la salida original desde Base64
        try {
            byte[] salidaBytes = Base64.getDecoder().decode(salidaBase64);
            String salidaComando = new String(salidaBytes);
            System.out.println("=== Salida del Comando ===");
            System.out.println(salidaComando);

            //Verificar la firma
            if (verificarFirma(salidaComando, firmaBase64)) {
                System.out.println("[✔] Firma verificada con éxito.");
            } else {
                System.out.println("[✘] La firma no es válida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Fallo al decodificar Base64. Verifica que la respuesta del servidor esté correctamente formateada.");
            e.printStackTrace();
        }
    }

    /**
     * Verifica la firma digital utilizando la clave pública del servidor. 
       mensaje Mensaje original a verificar
       firma Firma en Base64 que se compara con la generada a partir del mensaje
       true si la firma es válida, false si no lo es
     */
    private static boolean verificarFirma(String mensaje, String firma) {
        try {
            // Inicializar la verificación de la firma con el algoritmo SHA256 + RSA
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(clavePublica);
            sig.update(mensaje.getBytes()); // Aplicar la firma sobre los datos del mensaje

            // Decodificar la firma desde Base64 y verificar su validez
            byte[] firmaBytes = Base64.getDecoder().decode(firma);
            return sig.verify(firmaBytes);
        } catch (Exception e) {
            System.out.println("[ERROR] Ocurrió un problema al verificar la firma: " + e.getMessage());
            return false;
        }
    }
}
