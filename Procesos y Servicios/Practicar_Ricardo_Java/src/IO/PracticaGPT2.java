package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PracticaGPT2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String ruta= "C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\IO\\Prueba.txt";
		String contenido="AProvado";
		
		try {
			FileOutputStream forr=new  FileOutputStream(ruta);
			byte[] tranformar=contenido.getBytes();
			forr.write(tranformar);
			System.out.println("realizado con exito");
			forr.flush();
			forr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
