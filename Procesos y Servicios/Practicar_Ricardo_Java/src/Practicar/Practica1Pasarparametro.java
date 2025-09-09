package Practicar;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practica1Pasarparametro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//pasamos como parametro un comando que entra terminal
		// despues pasamos el nombre de la ruta del directorio
		ProcessBuilder pb=new ProcessBuilder("cmd.exe", "/c", "dir", "C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java");
		try {
			
			//iniciamos el proceso 
			Process ps=pb.start();
			
			//leemso linea a linea loq ue hay dentor del proceso
			BufferedReader leerdentro= new BufferedReader(new InputStreamReader(ps.getInputStream()) );
			
			
			//aqui muestra line a linea lo que hay dentro 
			String mostrar;
			int contadorfichero=0;
			int contadorArchivos=0;
			while((mostrar = leerdentro.readLine() )!= null) {
				//aqui mostramos loq ue hay dentro linea linea
				System.out.println(mostrar);
				
			}
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void directorio(String directorio1) {
		File directorio=new File("C:/Practicar_Ricardo_Java");
		directorio.isDirectory();
	}

}
