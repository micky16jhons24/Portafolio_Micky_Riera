package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ObjectOutputStream {

	static void leerObjeto(){
		try {
			
			//leer objeto
			FileInputStream fis=new FileInputStream("objetos.txt");
			ObjectInputStream ois=new ObjectInputStream(fis);
			System.out.println(ois.readBoolean());
			System.out.println(ois.readDouble());
			System.out.println(ois.readLong());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//escribir objeto
			double x=3.5;
			FileOutputStream fos=new FileOutputStream("objetos.txt");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
