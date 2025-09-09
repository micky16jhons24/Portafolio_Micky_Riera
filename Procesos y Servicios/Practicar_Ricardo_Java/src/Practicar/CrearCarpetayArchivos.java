package Practicar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrearCarpetayArchivos {

	
	public static void escribirArchivo(String archivo) {
		
		
		File archivoW = new File(archivo);
		
		String mensaje= "Hola a todos y bienvenidos";
		String archivoE= archivoW.getAbsolutePath();
		
		
		try {
			FileWriter escribir = new FileWriter(archivo);
			
			for(int i = 0; i < mensaje.length();i++){
				escribir.write(mensaje.charAt(i));
			}
			escribir.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("nose a enconbtrado el archivo o esta protegido");
		}
		
		
		
	}
	
	
	
	
	
	public static void crearCarpeta(String carpeta) {
		
		
		//1 paso -> convertir el String en un FILE
		
		File carpetaC = new File(carpeta);
		
		//crear carpeta
		carpetaC.mkdir();

		
	}
	
	public static void crearArchivo(String archivo) {
		
		
		//1 paso ->
		File archivoC= new File(archivo);
		
		
		try {
			// se crea el archivo 
			archivoC.createNewFile();
			
		}catch(Exception e) {
			System.out.println("no se a podido crear el archivo");
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String carpeta = "YoutubeHack";
		String archivo = "YoutubeHack/inicio.txt";
		
		
	}

}
