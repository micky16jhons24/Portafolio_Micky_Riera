package Practicar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PracticaGPT2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//creamos un proceso 
		ProcessBuilder pb=new ProcessBuilder("sort");
		
		try {
			//inicializamos el proceso para ello necesitamos un Process
			Process pr=pb.start();
			//aqui usamos el OutputStreamWriter dentro de BufferedWriter para enviar texto al proceso sort
			//con esto envez de enviarlo byt a byt lo enviamos linea a linea
			BufferedWriter br=new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));
			//creamos la array que llevara las plabras y ordenarlas
			String[] palabras= {"hola", "q tal", "que haces", "hoy "};
			
			//con esto escribimos cada palabra en el proceso
			for(String palabra : palabras) {
				br.write(palabra);
				br.newLine(); //nueva linea para cada palabra 
			}
			//con esto cierro el flujo de salida para indicar al proceso que hemos terminado de enviar datos
			br.flush();
			br.close();
			
			//esto lee la salida ordenada del prodeceso con BufferedReader
			BufferedReader leer=new BufferedReader(new InputStreamReader(pr.getInputStream()));
			System.out.println("palabra ordenada alfabeticamente: ");
			String linea;
			while((linea = leer.readLine())!= null) {
				System.out.println(linea);
			}
			//esto cierra el flujo de entrada y espera a que el proceso finalice
			leer.close();
			pr.waitFor();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
