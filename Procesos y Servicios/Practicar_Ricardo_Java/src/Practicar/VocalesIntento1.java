package Practicar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class VocalesIntento1 {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		int letraleida; // aqui se mete un cracter para ir conprovando
		int cont=0;
		
		char vocal= args[0].charAt(0); 
		char vocal_m = Character.toLowerCase(vocal);// esto para hacer la letra pequeña 
		char vocal_M = Character.toUpperCase(vocal);
		
		
		String ruta = "C:\\Users\\Usuario\\eclipse-workspace\\Practicar_Ricardo_Java\\src\\Practicar\\Vocales.txt";
		
		try {
			FileInputStream fis = new  FileInputStream(ruta);
			while((letraleida = fis.read())!= -1) {
				if(letraleida == vocal_m | letraleida == vocal_M) {
					cont++;
				}
			}
			System.out.println(cont);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
