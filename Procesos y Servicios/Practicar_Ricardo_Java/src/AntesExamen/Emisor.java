package AntesExamen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Emisor {

	public static void main(String[] args) {
		Scanner imprimir=new Scanner(System.in);
		
		String mensaje="";
		System.out.println("escribe el mensaje que quieras enviarle al receptor");
		mensaje= imprimir.next();
		
		System.out.println("el mensaje fue escrito correctamente");
		
		//comunicacion con el Programa Receptor
		
		String[] llamar_emisor= {
				"java",
				"Receptor.java"
		};
		
		
		try {
			ProcessBuilder pb=new ProcessBuilder(llamar_emisor);
			pb.directory(new File("C:/Users/Usuario/eclipse-workspace/Practicar_Ricardo_Java/src/AntesExamen"));
			Process iniciar=pb.start();
			System.out.println("El programa funciona");
			
			//Esto envia el mensaje
			BufferedWriter escribir=new BufferedWriter(new OutputStreamWriter(iniciar.getOutputStream()));
			try {
				escribir.write(mensaje);
				escribir.newLine();
				escribir.flush();
				escribir.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			

			//esto recibe el mensaje
			BufferedReader br=new BufferedReader(new InputStreamReader(iniciar.getInputStream()));
			try {
				
				String line="";
				
				line=br.readLine();
				
				while(line != null) {
					System.out.println(line);
					line= br.readLine();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

}
