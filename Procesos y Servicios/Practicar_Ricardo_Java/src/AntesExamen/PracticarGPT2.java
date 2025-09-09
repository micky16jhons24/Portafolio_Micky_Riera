package AntesExamen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PracticarGPT2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		Ejercicio 2: Escribir en un archivo con OutputStream
//		Nivel: Fácil
//
//		Descripción: Escribe una frase en un archivo utilizando OutputStream.
//		Instrucciones:
//		Crea un archivo y escribe el contenido de un String en él utilizando FileOutputStream.
//		Pistas:
//
//		Usa String.getBytes() para convertir el contenido en bytes.
		
		//metemops dentro de una cedan de texto la ruta del archivo
		String ruta="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba1.txt";
		//Escribimos una cadena de texto para meterlo dentro del archivo 
		String escribir="Alex es gay";
		
		try {
			//entramos dentro del archivo y estamos esperadnoa escribir 
			FileOutputStream fol= new FileOutputStream(ruta);
			//escribimos dentro del archivo 
			fol.write(escribir.getBytes());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
