package Practicar;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileInputStreamOutputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//FileInputStream --> sirve para crear el archivo que lo hemos pasado por parametro 
		//y para escribir dentro del archivo creado los daots que nosotros le vamos a pasar que van a ser byts
		
		
		//FileOutputStream --> Esto lee los bits que esta dentro del archivo
		
		
		String directorio= "Java/Practica/etc....";
		
		try {
			//le pasamos el directorio donde queremos que tenga el archivo
			FileOutputStream fos=new FileOutputStream(directorio);
			FileInputStream fis=new FileInputStream(directorio);
			
			
			//escribimos lo que queremos pasar y se tiene que pasar por byts
			String texto="supera tus limites";
			
			//Para pasarlo por byts hay que convertirlo a byts el texto
			//Llamamos a la clase byts
			byte codigos[] = texto.getBytes();
			
			//escribimos dentro del archivo
			fos.write(codigos);
			//siempre hay que cerrar
			fos.close();
			
			
			//como leemos lo que hay dentro 
			// hay que crear una variable que cuente los byts
			int bytLeido;
			
			//abro un while y lo que decimos es que el byte que le estoy pasando lo 
			//lee el fis.read que es el inputStream
			//y lo que decimos que mientras el byt sea diferente a -1 va imprimir cada byt
			
			while((bytLeido = fis.read())!= -1) {
				System.out.println(bytLeido + " ");
			}
			fis.close();
			
			
			
			
			
		}catch(Exception e) {
			System.out.println("no se a encontrado archivo");
		}
		
	}

}
