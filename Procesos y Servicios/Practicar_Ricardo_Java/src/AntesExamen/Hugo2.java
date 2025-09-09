package AntesExamen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Hugo2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	//	Ejercicio 2: Escribir en un archivo con OutputStream
//		Nivel: Fácil
//
//		Descripción: Escribe una frase en un archivo utilizando OutputStream.
//		Instrucciones:
//		Crea un archivo y escribe el contenido de un String en él utilizando FileOutputStream.
//		Pistas:
//
//		Usa String.getBytes() para convertir el contenido en bytes.
		
		
		String ruta= "C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba1.txt";
		//esto es lo que vamos a escribir
		String escribirDentro="Alex y hugo son gays";
		
		try {
			
			//esat dentro del archvio y esperando a escribir 
			FileOutputStream fol=new FileOutputStream(ruta);
			//escribir
			fol.write(escribirDentro.getBytes());
			
			fol.flush();//esto lo que hace es enviar todos los datos de golpe 
			fol.close();
			System.out.println("echo con exito");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
