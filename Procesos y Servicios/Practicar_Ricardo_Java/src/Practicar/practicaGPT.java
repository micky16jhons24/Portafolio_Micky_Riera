package Practicar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class practicaGPT {
	public static void main(String[] args) {
		ProcessBuilder pb=new ProcessBuilder( "ping" , "google.com");	
		try {
			Process pr=pb.start();
			BufferedReader reader=new BufferedReader(new InputStreamReader (pr.getInputStream()));
			String linea;
			while((linea = reader.readLine())!= null) {
				System.out.println(linea);
			}
		
			pr.waitFor();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
