package Enero2025Febrero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHilos {

    public static void main(String[] args) {
        int puerto = 23456; // Puerto en el que el servidor escuchará
        escribir write = new escribir(); // Crear una instancia de escribir que será compartida entre los hilos

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Mantener el servidor escuchando para múltiples conexiones
            while (true) {
                // Aceptar conexiones entrantes
                Socket cliente = servidor.accept();

                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                // Iniciar un hilo para manejar al cliente
                Thread hiloCliente = new Thread(new hilos(cliente, write));

                hiloCliente.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

// Clase que maneja a un cliente en un hilo independiente
class hilos implements Runnable {
    Socket cliente;
    escribir es;

    public hilos(Socket cliente, escribir es) {
        this.cliente = cliente;
        this.es = es;
    }

    @Override
    public void run() {
        try (
            // Leer mensajes enviados por el cliente
            InputStream entrada = cliente.getInputStream();
            BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
            // Enviar mensajes al cliente
            OutputStream salida = cliente.getOutputStream();
            PrintWriter escritor = new PrintWriter(salida, true)
        ) {
            // Leer un mensaje del cliente
            String mensaje = lector.readLine();
            if (mensaje != null) {
                es.escribirnotepad(mensaje); // Escribir en el archivo
                // Enviar una respuesta al cliente
                escritor.println("Hola desde el servidor. Recibí: " + mensaje);
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                cliente.close(); // Cerrar la conexión con el cliente
            } catch (IOException e) {
                System.err.println("Error al cerrar el cliente: " + e.getMessage());
            }
        }
    }
}

// Clase que controla la escritura en un archivo.txt
class escribir {

    public synchronized void escribirnotepad(String texto) throws IOException {
        String fichero = "EJ.txt";
        // Abrir el archivo en modo append para agregar líneas
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero, true))) {
            escritor.write(texto);
            escritor.newLine(); // Agregar una nueva línea
            System.out.println("Escrito: " + texto);
        }
    }
}
