package RetosDeProgramacion;

public class FIBONACCI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numero=50;
		int[] fibonacci = new int[numero];  // Crear un array para almacenar los términos

        // Los dos primeros términos de la secuencia
        fibonacci[0] = 0;  
        fibonacci[1] = 1;

		
		for(int i =0; i <=numero; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];  // El siguiente número es la suma de los dos anteriores
		}
		
		 System.out.println("Los primeros " + numero + " números de la sucesión de Fibonacci son:");
	        for (int i = 0; i < numero; i++) {
	            System.out.println(fibonacci[i]);
	        }
		
	}

}

