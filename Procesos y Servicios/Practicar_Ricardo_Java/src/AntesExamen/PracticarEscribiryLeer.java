package AntesExamen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PracticarEscribiryLeer {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String ruta="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba1.txt";
		String ruta2="C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\AntesExamen\\Prueba2.txt";
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta2)));
		String line;
		while((line = br.readLine())!= null) {
			bw.write(line);
			bw.flush();
		}
		bw.close();
	

	
	}

}
