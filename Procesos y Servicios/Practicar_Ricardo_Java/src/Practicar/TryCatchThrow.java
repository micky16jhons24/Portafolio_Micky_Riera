package Practicar;

public class TryCatchThrow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Opcion 1= ArithmeticException
		//try es intentar
		try {
			int resultado =dividir(10,0);
			
		}//ArithmeticException significa que una operacion sale mal
		catch(ArithmeticException e) {
			System.out.println( "Error:Divison por 0");
		}finally {
			System.out.println("programa finalizado");
		}
		
		
		
		//opcion 2= ArithmeticException
		
		try {
			String cadena = null;
			int longitud = cadena.length(); // genera una NullPointerException
			System.out.println("longitud " + longitud );
		}//NullPointerException se usa cuadno pasamos un valor NULO,
		//Esto es para cuando queremos calcular algo pero tiene valor NULO = no tiene valor
		catch(NullPointerException e) {
			System.out.println( "Error: Objeto null");
		}
		
		
		//opcion 3 = ArrayIndexOutOfBoundsException
		
				try {
					int[] arreglo= {1,2,3};
					int valor = arreglo[3]; // genera una ArrayIndexOutOfBoundsException
					System.out.println("valor " + valor );
					
				}//NullPointerException se usa cuadno nos pasamos de la longitud de la Array 

				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println( "Error: indice fuera de los limites de arreglo");
				}
				
		//Opcion 4 = IllegalArgumentException		
				try {
					int resultado =obtenerValor(-5);
					System.out.println("resul tado " + resultado);
					
				}//IllegalArgumentException significa que tiene que dar un valor que nosotros decimos
				catch(IllegalArgumentException e) {
					System.out.println( "Error:Argumento invalido.");
				}
				
				
				
	}
	
	
	public static int obtenerValor(int valor) {
		if(valor<0) {
			throw new IllegalArgumentException("el valor no puede ser negativo");
		}
		return valor;
	}
	
	
	
	public static int dividir (int numero1 , int numero2) {
		return numero1 / numero2;
		
	}

}
