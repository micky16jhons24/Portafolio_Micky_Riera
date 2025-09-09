package AntesExamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practica4GPT {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
//		Ejercicio 4: Ejecutar un comando del sistema con ProcessBuilder
//		Nivel: Intermedio
//
//		Descripción: Ejecuta un comando del sistema utilizando ProcessBuilder y muestra la salida en la consola.
//		Instrucciones:
//		Usa ProcessBuilder para ejecutar un comando simple, como ping o ls (en Linux/macOS) o dir (en Windows).
//		Captura la salida del proceso utilizando el InputStream del proceso y muéstrala en la consola.
//		Pistas:
//
//		Utiliza process.getInputStream() para leer la salida del comando.
		//nosotros tenemos el proceso creado pero no incializado
		ProcessBuilder pb=new ProcessBuilder("ping", "google.com");
		Process pr=pb.start();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line;
		while((line = br.readLine())!= null) {
			System.out.println("codigo" + line);
		}
		pr.waitFor();
		pr.destroy();
		
	}

}
