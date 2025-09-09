package IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practicar1New {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		String ruta= "C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\IO\\Prueba.txt";
		
		FileInputStream fir=new FileInputStream(ruta);
		int line;
		while((line = fir.read())!= -1) {
			System.out.println((char)line);
		}
		
		fir.close();
	}

}
