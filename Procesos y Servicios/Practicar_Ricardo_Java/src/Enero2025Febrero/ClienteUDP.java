package Enero2025Febrero;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteUDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            // Crear un objeto Scanner para capturar datos
	            Scanner scanner = new Scanner(System.in);

	            // Obtener la dirección del servidor (localhost en este caso)
	            InetAddress direccionServidor = InetAddress.getByName("localhost");
	            System.out.println("Usando dirección del servidor: " + direccionServidor.getHostAddress());

	            // Solicitar el puerto del servidor
	            System.out.print("Escribe el puerto del servidor: ");
	            int puerto = scanner.nextInt();
	            scanner.nextLine(); // Limpiar el buffer del scanner

	            // Solicitar el nombre del usuario
	            System.out.print("Escribe tu nombre: ");
	            String nombre = scanner.nextLine();

	            // Crear el socket UDP para enviar y recibir mensajes
	            DatagramSocket socket = new DatagramSocket();

	            // Establecer un tiempo de espera de 1 segundo (1000 ms) para la respuesta
	            socket.setSoTimeout(1000);

	            // Formatear el mensaje que se enviará al servidor
	            String mensaje = "@hola#" + nombre + "@";
	            byte[] mensajeBytes = mensaje.getBytes();

	            // Crear un paquete UDP con el mensaje, la dirección del servidor y el puerto
	            DatagramPacket packet = new DatagramPacket(mensajeBytes, mensajeBytes.length, direccionServidor, puerto);

	            // Enviar el paquete al servidor
	            socket.send(packet);

	            // Crear un buffer para recibir la respuesta del servidor
	            byte[] buffer = new byte[1024];
	            DatagramPacket respuestaPacket = new DatagramPacket(buffer, buffer.length);

	            try {
	                // Esperar la respuesta del servidor
	                socket.receive(respuestaPacket);

	                // Extraer la respuesta del paquete recibido
	                String respuesta = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());

	                // Usar una expresión regular para validar el formato de la respuesta
	                Pattern pattern = Pattern.compile("@hola#(.+)@");
	                Matcher matcher = pattern.matcher(respuesta);

	                // Si la respuesta tiene el formato esperado, extraer el nombre del servidor
	                if (matcher.find()) {
	                    String nombreServidor = matcher.group(1);
	                    System.out.println("Recibida respuesta de " + nombreServidor);
	                }
	            } catch (SocketTimeoutException e) {
	                System.out.println("No se recibió respuesta del servidor.");
	            }

	            socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


