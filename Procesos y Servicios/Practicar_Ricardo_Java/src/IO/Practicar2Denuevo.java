package IO;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practicar2Denuevo {
	public static void main(String[] args) throws IOException, InterruptedException {
		String ruta="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\IO\\Prueba.txt";
		String escrito="Smoke WEED every day!!!! BITCH fsdfsfssedrtfgysedtfgysegysedftgysdfgysdfgsxdcfzxcvsedrf e rtse drtf edr tfd rtf drtf f";
		
		
		FileOutputStream archivoEscrito=new FileOutputStream(ruta);
		byte[] convertir = escrito.getBytes();
		archivoEscrito.write(convertir);
		System.out.println("realizado con exito");
		archivoEscrito.flush();
		archivoEscrito.close();
		
		
		
		
		
	}
}
