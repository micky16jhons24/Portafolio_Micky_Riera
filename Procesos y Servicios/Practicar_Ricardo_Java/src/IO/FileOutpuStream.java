package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutpuStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fos=new FileOutputStream("datos.txt");
			fos.write(44); //escribe el caracter 44 de la tabla asci dentro del archivo 
			fos.write(null); //esto escribiria todos los byts de una 
			fos.flush(); // esto envia todos los datos de una 
			fos.close(); // cierra el proceso 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
