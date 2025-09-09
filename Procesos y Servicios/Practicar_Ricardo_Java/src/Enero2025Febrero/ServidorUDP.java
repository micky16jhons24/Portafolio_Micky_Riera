package Enero2025Febrero;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorUDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 try {
	            // Crear un objeto Scanner para capturar la entrada del usuario
	            Scanner scanner = new Scanner(System.in);

	            // Solicitar al usuario el puerto en el que el servidor escuchará
	            System.out.print("Escribe el puerto para el servidor: ");
	            int puerto = scanner.nextInt();
	            scanner.nextLine(); // Limpiar el buffer del scanner

	            // Solicitar al usuario el nombre del servidor
	            System.out.print("Escribe el nombre del servidor: ");
	            String nombreServidor = scanner.nextLine();

	            // Crear el socket UDP que escuchará en el puerto especificado
	            DatagramSocket socket = new DatagramSocket(puerto);
	            // Crear un buffer para recibir datos de los clientes
	            byte[] buffer = new byte[1024];

	            // El servidor permanecerá ejecutándose y escuchando mensajes indefinidamente
	            while (true) {
	                // Crear un paquete para recibir un datagrama desde cualquier cliente
	                DatagramPacket recibido = new DatagramPacket(buffer, buffer.length);
	                socket.receive(recibido);  // Bloquea el hilo hasta que reciba un mensaje

	                // Convertir los bytes del paquete recibido a un String
	                String mensaje = new String(recibido.getData(), 0, recibido.getLength());

	                // Usar una expresión regular para verificar y extraer el nombre del cliente
	                Pattern pattern = Pattern.compile("@hola#(.+)@");
	                Matcher matcher = pattern.matcher(mensaje);
	                
	                // Si el mensaje tiene el formato esperado
	                if (matcher.find()) {
	                    // Extraer el nombre del cliente
	                    String nombreCliente = matcher.group(1);

	                    // Crear el mensaje de respuesta con el nombre del servidor
	                    String respuesta = "@hola#" + nombreServidor + "@";
	                    // Convertir el mensaje de respuesta a bytes
	                    byte[] respuestaBytes = respuesta.getBytes();

	                    // Crear el paquete UDP con la respuesta, usando la dirección y puerto del cliente
	                    DatagramPacket respuestaPacket = new DatagramPacket(
	                        respuestaBytes,
	                        respuestaBytes.length,
	                        recibido.getAddress(),  // Dirección del cliente que envió el mensaje
	                        recibido.getPort()      // Puerto del cliente
	                    );

	                    // Enviar la respuesta al cliente
	                    socket.send(respuestaPacket);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


