package AntesExamen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Practica1GPT {
	
	//
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
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//es una cadena de caracteres 
		String ruta="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba1.txt";
		
		try {
			//pero FileInputStream lee el archivo , ruta nos da la direccion 
			FileInputStream fil=new FileInputStream(ruta);
			//creamos un varaible int para meter codigo dentro de el 
			int line;
			//while=mientras linea se igual a byt se immprimira por pantalla y en el momento que no 
			//haya ningun byt para leer devolvera un -1, entonces parar
			while((line = fil.read())!= -1) {
				// esto imprime por pàntalla , pero solo codigo ascii
				//utilizamos un casteadro un char para castear/cambiar de numero a caracter
				System.out.print((char)line);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
