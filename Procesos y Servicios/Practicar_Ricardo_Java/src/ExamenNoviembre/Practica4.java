package ExamenNoviembre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practica4 {

	
//	Objetivo: Crear un programa en Java que ejecute un comando del sistema operativo usando ProcessBuilder y lea su salida en paralelo usando hilos.
//	Requisitos:
//	Utiliza ProcessBuilder para ejecutar el comando ping (o cualquier otro comando simple).
//	Crea dos hilos:
//	Uno para leer la salida estándar (Output Stream) del proceso.
//	Otro para leer la salida de error (Error Stream) del proceso.
//	Imprime la salida del comando en la consola.
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		ProcessBuilder pb=new ProcessBuilder("ping"  , "google.com");
		Process pr=pb.start();
		
		BufferedReader outputReader=new  BufferedReader(new InputStreamReader(pr.getInputStream()));
		BufferedReader errorReader=new  BufferedReader(new InputStreamReader(pr.getErrorStream()));

		HiloOutput hilooutput=new HiloOutput(outputReader);
		HiloError hiloerror=new HiloError(errorReader);

		Thread h1Output =new Thread(hilooutput);
		Thread h1Error =new Thread(hiloerror);

		
		h1Output.start();
		h1Error.start();
		
		h1Output.join();
		h1Error.join();
		
		
		int condigoSalida = pr.waitFor();
        System.out.println("Proceso terminado con código: " + condigoSalida);

		
	}

}

class HiloOutput implements Runnable{
	
	private BufferedReader reader;
	
	public HiloOutput(BufferedReader reader) {
		this.reader=reader;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
		try {
			while((line = reader.readLine())!= null) {
				System.out.println("salida Output= " + line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



class HiloError implements Runnable{

	private BufferedReader reader;
	
	public HiloError(BufferedReader reader) {
		this.reader=reader;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
		try {
			while((line = reader.readLine())!= null) {
				System.out.println("salida Error= " + line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
