package AntesExamen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Hugo1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Ejercicio 1: Leer desde un archivo con InputStream
		//Nivel: Fácil
		//
		//Descripción: Leer el contenido de un archivo de texto y mostrarlo en la consola utilizando InputStream.
		//Instrucciones:
		//Crea un archivo de texto simple (por ejemplo, archivo.txt).
		//Usa FileInputStream para leer el contenido del archivo byte por byte y mostrarlo en la consola.
		//Pistas:
		//
		//Usa un bucle para leer los bytes hasta que el método read() devuelva -1 (que indica el fin del archivo).
		
		String ruta= "C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba1.txt";
		
		try {
			FileInputStream fil=new FileInputStream(ruta);
			int line;
			while((line=fil.read())!= -1) {
				System.out.print((char)line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
