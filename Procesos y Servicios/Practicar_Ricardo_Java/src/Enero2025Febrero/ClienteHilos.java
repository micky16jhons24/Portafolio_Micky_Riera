package Enero2025Febrero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHilos {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 String host = "172.16.91.146";
	        int puerto = 23456;
	        
	        Scanner sc = new Scanner(System.in);
	        
	        try (Socket socket = new Socket(host, puerto)) {

	            System.out.println("Conectado al servidor.");

	            String mensaje = "";
	            OutputStream salida = socket.getOutputStream();
	            PrintWriter escritor = new PrintWriter(salida, true);
	            InputStream entrada = socket.getInputStream();
	            BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));

	            while (!mensaje.equals("salir")) {
	                System.out.println("Dime un mensaje");
	                mensaje = sc.nextLine();

	                escritor.println(mensaje);

	                String respuesta = lector.readLine();
	                System.out.println("Respuesta del servidor : " + respuesta);
	            }
	            

				 } catch (IOException e) {
				 System.err.println("Error en el cliente: " + e.getMessage());
				 }
				 }
}


	

