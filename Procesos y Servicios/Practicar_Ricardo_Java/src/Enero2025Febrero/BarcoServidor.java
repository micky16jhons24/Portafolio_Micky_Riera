package Enero2025Febrero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BarcoServidor {

	 public static void main(String[] args) {
	        try {
	            // Solicitar al usuario el puerto para el servidor
	            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
	            System.out.print("Escribe el puerto para el servidor: ");
	            int puerto = Integer.parseInt(consola.readLine());

	            // Crear un ServerSocket en el puerto especificado
	            ServerSocket servidor = new ServerSocket(puerto);
	            System.out.println("Servidor TCP iniciado en el puerto " + puerto);

	            // Mantener el servidor ejecutándose indefinidamente
	            while (true) {
	                System.out.println("Esperando conexión de un cliente...");
	                Socket cliente = servidor.accept(); // Esperar conexión de cliente
	                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

	                // Crear un hilo para manejar la conexión con el cliente
	                new Thread(() -> manejarCliente(cliente)).start();
	            }
	        } catch (IOException e) {
	            System.err.println("Error en el servidor: " + e.getMessage());
	        }
	    }

	    private static void manejarCliente(Socket cliente) {
	        try (BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
	             PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true)) {

	            String mensaje;
	            while ((mensaje = lector.readLine()) != null) {
	                // Salir si el cliente envía "salir"
	                if (mensaje.equalsIgnoreCase("salir")) {
	                    System.out.println("Cliente desconectado.");
	                    break;
	                }

	                // Procesar el mensaje del cliente (formato: "fila,col")
	                String[] partes = mensaje.split(",");
	                if (partes.length == 2) {
	                    try {
	                        int fila = Integer.parseInt(partes[0]);
	                        int col = Integer.parseInt(partes[1]);

	                        // Simular un impacto o fallo aleatorio
	                        Random random = new Random();
	                        String resultado = random.nextBoolean() ? "Impacto" : "Agua";

	                        // Responder al cliente
	                        String respuesta = fila + "," + col + "," + resultado;
	                        escritor.println(respuesta);
	                        System.out.println("Respuesta enviada al cliente: " + respuesta);
	                    } catch (NumberFormatException e) {
	                        escritor.println("Error: coordenadas no válidas.");
	                    }
	                } else {
	                    escritor.println("Error: formato de mensaje incorrecto.");
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error al manejar al cliente: " + e.getMessage());
	        }
	    }
}
