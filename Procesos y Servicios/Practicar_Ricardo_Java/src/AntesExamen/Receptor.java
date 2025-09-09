package AntesExamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Receptor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mensaje_Recibido= "";
		
		InputStreamReader is=new InputStreamReader(System.in); //nosotros vamos a pedir datos 
		BufferedReader br=new BufferedReader(is);
		
		try {
			//captura lo que hemos escrito en el System.in
			mensaje_Recibido = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			try {
				if(br!=null) {
				
					br.close();
				
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("EL mensaje que ha enviado  emisor es: " + mensaje_Recibido + " Mensaje enviado por el emisor");
		
	}

}
