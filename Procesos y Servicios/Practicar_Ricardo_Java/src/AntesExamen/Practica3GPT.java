package AntesExamen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practica3GPT {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		Ejercicio 3: Copiar el contenido de un archivo a otro
//		Nivel: Intermedio
//
//		Descripción: Copia el contenido de un archivo a otro utilizando InputStream y OutputStream.
//		Instrucciones:
//		Lee el contenido de un archivo usando FileInputStream.
//		Escribe ese contenido en otro archivo usando FileOutputStream.
//		Pistas:
//
//		Usa un buffer (por ejemplo, un arreglo de bytes de tamaño 1024) para leer y escribir bloques de datos en lugar de hacerlo byte por byte.
		
		
		String ruta="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba1.txt";
		String ruta2="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba2.txt";
		
		//escribir arhcivo
		FileOutputStream fore=new FileOutputStream(ruta2);
		//leer la salida de la ruta 1 y meterlo dentro de la variable br
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
		String line;
		while((line= br.readLine())!= null) {
			

			fore.write(line.getBytes());
			System.out.println("echo");
		}
		
		
		
		
	}

}
