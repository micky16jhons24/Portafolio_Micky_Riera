package Practicar;

import java.io.File;

public class FIle_Practica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ruta= "java";
		
		
		File archivo= new File(ruta);
		
		System.out.println(archivo.isDirectory());//dice si es un directorio 
		System.out.println(archivo.isFile());//dice si es un fichero 
		System.out.println(archivo.canRead());//dice si se puede leer
		System.out.println(archivo.getAbsolutePath());//imprime la direccion absoluta
		System.out.println(archivo.lastModified()); //imprime la ultima modificacion
		System.out.println(archivo.length());//muestra el tamaño 
		
		
		
		
		
	}
	
	
	
//	public static String[] ficheros(File archivo) {
//		
//		String[]  archivos= archivo.list();
//		return archivos;
//		
//	}
//	
//	
//	public static File[] ficherosX(File archivo) {
//		
//		File[]  archivos= archivo.listFiles();
//		return archivos;
//		
//		for(int i=0; i < archivo.length();i++ ) {
//			if(archivos[i].isFile()) {
//				System.out.println(archivos[i].getName());
//			}
//			
//		}
		
	}

}
