package AntesExamen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Practica5GPT {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		Ejercicio 5: Ejecutar un proceso y escribir en su entrada
//		Nivel: Intermedio-Avanzado
//
//		Descripción: Ejecuta un proceso que espera una entrada del usuario (por ejemplo, grep en Linux o findstr en Windows) y escribe en su OutputStream para interactuar con el proceso.
//		Instrucciones:
//		Usa ProcessBuilder para ejecutar un comando que espere entrada desde la consola.
//		Usa el OutputStream del proceso para enviar datos al proceso.
//		Muestra la salida del proceso en la consola.
//		Pistas:
//
//		Usa process.getOutputStream() para escribir al proceso.
		
		ProcessBuilder pb=new ProcessBuilder("findstr","hola q tal"); //sort lo que hace es ordenar alfabeticamente
		try {
			Process pr=pb.start();
			//escribimos en la entra de un proceso
			//escribe     //muestra lo que hemos escrito
			OutputStream ou=pr.getOutputStream();
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(ou));
			bw.write("hola q tal1 ");
			bw.write("comete una mierda");
			bw.flush();
			bw.close();
			
			InputStream is=pr.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = br.readLine())!= null) {
				System.err.println(line);
			}
			
			pr.waitFor();
			System.out.println("funciona");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
