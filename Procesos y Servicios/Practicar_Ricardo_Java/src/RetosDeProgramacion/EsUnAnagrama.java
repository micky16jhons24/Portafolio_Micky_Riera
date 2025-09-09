package RetosDeProgramacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EsUnAnagrama {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("escribe una palabra para saber si son anagramas jugador 1");
		String verificarAnagrma1=sc.next();
		System.out.println("escribe una palabra para saber si son anagramas jugador 2");
		String verificarAnagrma2=sc.next();
		
		anagrama(verificarAnagrma1 , verificarAnagrma2);
		
		
		
	}
	
	
	public static boolean anagrama(String anagrama1 , String anagrama2) {
		


		if(anagrama1.length() != anagrama2.length()) {
			System.out.println("no son anagramas");
			return false;
		}

		
		char[] a1=anagrama1.toCharArray();
		char[] a2=anagrama2.toCharArray();
		
		Arrays.sort(a1);
		Arrays.sort(a2);
		
		
		if(Arrays.equals(a1, a2)) {
			System.out.println("la palabras son anagramas");
			return true;
		}else {
			System.out.println("la palabras no son anagramas");
		}
		return false;
		
	}
	
	
	

}
