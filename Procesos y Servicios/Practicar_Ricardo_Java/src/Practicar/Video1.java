package Practicar;

import java.io.IOException;

public class Video1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println("Ejemplo de lanzar un proceso des de java");
		
		//Instanciar o crear el lanzador de procesos
		ProcessBuilderPrueba pb = new ProcessBuilderPrueba();
		// construir el comando  que va a lanzar builder
		
		pb.command("ping" , "-c" , "3" , "google.com"  );
		
		
		//Esto hace que empieze el proceso 
		Process p = pb.start();
		
		//esto es para obtener el ID del processo 
		
		System.out.println("Id del proceso: " + p.pid());
		
		//cuando termina la variable da un Exit code hay que obtenerlo
		
		int exitCode = p.waitFor();
		
		//DEstruimos el proceso 
		p.destroy();
		
		System.out.println("Proceso finalizado " + exitCode);
		
		
		
	}

}
