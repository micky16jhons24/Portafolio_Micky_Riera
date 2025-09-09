package AntesExamen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PracticaFlujo {
	public static void main(String [] args) {
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
		
		
		ProcessBuilder pb=new ProcessBuilder("findstr", "hola q tal");
		try {
			Process pr=pb.start();
			
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));
			bw.write("hola q tal1");
			bw.write("que fue perra");
			bw.wait();
			bw.close();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while((line = br.readLine())!= null) {
				System.out.println(line);
			}
			
			pr.waitFor();
			pr.destroy();
			System.out.println("funciona si!!!");
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
