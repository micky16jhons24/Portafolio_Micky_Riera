package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Practica4GPT {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProcessBuilder pb=new ProcessBuilder("ping" ,"google.com");
		Process pr= pb.start();
		InputStream salida=pr.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(salida));
		String line;
		while((line = br.readLine())!= null) {
			System.out.println(line);
			
		}
		
		int exitcode=pr.waitFor();
		pr.destroy();
		System.out.println("el codigo es: " + exitcode);
		
		System.out.println(pr);
		pr.waitFor();
		pr.destroy();
		
		
		
	}

}
