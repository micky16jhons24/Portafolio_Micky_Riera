package RetosDeProgramacion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ContadorDePalabras {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);

	        System.out.println("Introduce un texto (escribe 'fin' para terminar): ");
	        String texto = scanner.nextLine();

	        // Convertir el texto a minúsculas y eliminar puntuación
	        texto = texto.toLowerCase().replaceAll("[^a-záéíóúñü\\s]", "");

	        // Separar el texto en palabras
	        String[] palabras = texto.split("\\s+");

	        // Lista para almacenar las palabras únicas
	        ArrayList<String> listaPalabras = new ArrayList<>();
	        ArrayList<Integer> contadorPalabras = new ArrayList<>();

	        // Contar la frecuencia de cada palabra
	        for (String palabra : palabras) {
	            int index = listaPalabras.indexOf(palabra);

	            if (index == -1) {
	                // Si la palabra no está en la lista, agregarla con contador 1
	                listaPalabras.add(palabra);
	                contadorPalabras.add(1);
	            } else {
	                // Si ya está en la lista, incrementar el contador
	                contadorPalabras.set(index, contadorPalabras.get(index) + 1);
	            }
	        }

	        // Mostrar el recuento final de cada palabra
	        System.out.println("Recuento de palabras: ");
	        for (int i = 0; i < listaPalabras.size(); i++) {
	            System.out.println(listaPalabras.get(i) + ": " + contadorPalabras.get(i));
	        }

	}

}
