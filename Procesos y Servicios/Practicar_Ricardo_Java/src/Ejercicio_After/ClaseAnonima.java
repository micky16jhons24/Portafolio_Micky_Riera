package Ejercicio_After;

public class ClaseAnonima {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		new Vehiculo() {
			private int numeroPasajero;
			
			
			public void conducir() {
				System.out.println("estoy conduciendo");
			}
		
			
		}.conducir();;
	}

}


class Vehiculo{
	
}
