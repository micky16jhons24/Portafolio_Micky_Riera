package RetosDeProgramacion;

import java.util.Scanner;

public class InvertirCadena {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("escribe una palabra y se te devolvera revertida");
		String palabre=sc.next();
		
		String palabraIvertida = invertirCadenas(palabre);
		
		System.out.println(palabraIvertida);
	}
	
	public static String  invertirCadenas(String cadena) {
		
		String  invertida="";
		
		for(int i = cadena.length() -1 ; i>= 0; i-- ) {
			invertida +=cadena.charAt(i); 
		}
		return invertida;
			
	}

}
