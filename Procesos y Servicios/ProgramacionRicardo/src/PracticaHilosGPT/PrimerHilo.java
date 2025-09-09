package PracticaHilosGPT;

//Primer Hilo Simple: "Hola Mundo Multihilo"
//
//Crea una clase que extienda de Thready otraRunnable.
//En ambas clases, haz que el hilo imprima "Hola Mundo
//Lanza ambos hilos desde el método main.

public class PrimerHilo {

	public static void main(String[] args) {
		
		hilo Hilos=new hilo();
		
		Thread hilo = new Thread(Hilos);
		hilo.start();
		
		
	
		
	
	
	}
	
	
static	class  hilo implements Runnable{
		
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			System.out.println("hola mundo desde el hilo ");
			
		}
		
	}
}
