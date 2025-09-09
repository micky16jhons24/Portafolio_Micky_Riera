package IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeeryEscribir {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String ruta="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\IO\\Prueba.txt";
		String ruta1="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\IO\\Prueba1.txt";

		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
		FileOutputStream escribir=new FileOutputStream(ruta1);
		
		
		String line;
		
		while((line= br.readLine())!= null) {
			byte[] pasar = (line + System.lineSeparator()).getBytes(); // Agregamos salto de línea
            escribir.write(pasar); // Escribimos cada línea en el archivo de destino
			
		}
		System.out.println("Copia Realizado");
		
		
		
		
	
		
	}

}
