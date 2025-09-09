package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Practicar2New {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		String ruta ="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\IO\\Prueba.txt";
		String escrito="Fumando";
		
		OutputStream ofile1=new FileOutputStream(ruta);
		byte[] convertir = escrito.getBytes();
		
		ofile1.write(convertir);
		System.out.println("archivo escrito bien");
	}

}
