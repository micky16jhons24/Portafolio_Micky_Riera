package PracticaHilosGPT;

//Contador concurrente
//
//Crea un programa que tenga dos
//Uno de los hilos cuenta de 1 a 10 y el otro cuenta de 10 a 1.
//Imprime cada número desde cada hilo y usasleep()Pensilvania

public class Practica2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		contarNumMas hilo=new contarNumMas();
		Thread hilo1 =new Thread(hilo);
		hilo1.start();
		
		contarNumMenos hiloSeg=new contarNumMenos();
		Thread hilo2 =new Thread(hiloSeg);
		hilo2.start();
	}
	
	
	
static	class contarNumMas implements Runnable{
		
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for(int i=0; i<=10;i++) {
				System.out.println("primer hilo ascendente" + i);
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
static	class contarNumMenos implements Runnable{
		
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for(int i=10; i>=1;i--) {
				System.out.println("primer hilo descendente" + i);
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
