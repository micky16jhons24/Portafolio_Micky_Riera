package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Practica5GPT {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProcessBuilder pb=new ProcessBuilder("sort");
		Process pr=pb.start();
		
		
		//vamos a escribir dentro 
		OutputStream escribir=pr.getOutputStream();
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(escribir));
		bw.write("esto se va escribir dentro CODA");
		bw.flush();
		bw.close();
		
		
		InputStream leer=pr.getInputStream();
		BufferedReader mostrar=new BufferedReader(new InputStreamReader(leer));
		String line;
		while((line=mostrar.readLine())!= null) {
			System.out.println(line);
		}
		
		int exitCode=pr.waitFor();
		System.out.println("Proceso terminado: " + exitCode );
	}

}
