package Practicar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContarVocalesintento1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		char vocales[]={'a','e', 'i', 'o', 'u'};
		
		Process proc[] = new Process[5];
		
		BufferedReader br []= new BufferedReader[5];
		
		for(int i=0; i<5;i++) {
			ProcessBuilder bp = new ProcessBuilder("cmd", "/c" , "java -jar C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\Practicar\\VocalesIntento1.java" + String.valueOf(vocales[i]));
			//iniciar proceso uno a uno 
			proc[i]= bp.start();
			 br[i]= new BufferedReader(new InputStreamReader(proc[i].getInputStream()));

		}
		
		for(int i=0; i<5;i++) {
			proc[i].waitFor();
		}
		
		for(int i=0; i<5;i++) {
			String resultado = br[i].readLine();
			System.out.println("hay " + resultado+ " " + vocales[i]);
		}
		
		
	}

}
