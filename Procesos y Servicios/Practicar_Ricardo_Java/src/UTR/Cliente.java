package UTR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;


public class Cliente {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la IP del servidor:");
        String host = sc.nextLine();
        int puerto = 12345;
        String taskId = null;

        try {
            Socket socket = new Socket(host, puerto);
            OutputStream salida = socket.getOutputStream();
            PrintWriter escritor = new PrintWriter(salida, true);
            InputStream entrada = socket.getInputStream();
            BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));

            System.out.println("Introduce tu nombre de usuario:");
            String usuario = sc.nextLine();
            System.out.println("Introduce el comando a ejecutar:");
            String comando = sc.nextLine();

            escritor.println(usuario + "@#@" + comando);
            taskId = lector.readLine();
            System.out.println(taskId);
            socket.close();

            while (true) {
                Thread.sleep(1000);
                socket = new Socket(host, puerto);
                escritor = new PrintWriter(socket.getOutputStream(), true);
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                escritor.println("POLL@" + taskId);
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea).append(System.lineSeparator());
                }
                socket.close();

                if (!respuesta.toString().contains("Pendiente")) {
                    String[] parts = respuesta.toString().split("@firma@");
                    String output = parts[0];
                    String signature = parts[1];

                    if (verifySignature(output, signature)) {
                        System.out.println("Respuesta verificada: " + output);
                    } else {
                        System.out.println("¡Error! La firma no coincide.");
                    }
                    break;
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean verifySignature(String data, String signedHash) {
        try {

            String publicKeyContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\samee\\eclipse-workspace\\Communication_Exercise_ChatGPT\\src\\communication\\public_key.pem")));
            publicKeyContent = publicKeyContent
                    .replaceAll("\\r\\n", "")
                    .replaceAll("\\n", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .trim();
            
            // Decode the public key
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyContent);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // Hash the data using SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            
            // Clean up the signed hash
            signedHash = signedHash
                    .replaceAll("\\s+", "")
                    .replaceAll("=+$", "");

            System.out.println("Cleaned Signed Hash: " + signedHash);

            int paddingNeeded = (4 - (signedHash.length() % 4)) % 4;
            signedHash += "=".repeat(paddingNeeded);
            
            System.out.println("Padded Signed Hash: " + signedHash);

            byte[] digitalSignature = Base64.getDecoder().decode(signedHash);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(hash);

            boolean isVerified = signature.verify(digitalSignature);
            System.out.println("Signature Verification Result: " + isVerified);
            return isVerified;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
