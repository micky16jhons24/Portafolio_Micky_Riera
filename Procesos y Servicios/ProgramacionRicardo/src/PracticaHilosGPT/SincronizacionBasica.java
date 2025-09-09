package PracticaHilosGPT;

public class SincronizacionBasica {
	private static int contador=0;
	
	public static synchronized  void incrementar() {
		contador++;
	}
	
	public static void main(String[] args) throws InterruptedException {
		hiloSuma hilo=new hiloSuma();
		Thread h1=new Thread(hilo);
		Thread h2=new Thread(hilo);

		h1.start();
		h2.start();
		
		h1.join();
		h2.join();
		
		System.out.println("valor final del contaod: " + contador);
	
	}
}


class hiloSuma implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i =0; i <1000;i++) {
			SincronizacionBasica.incrementar();
		}
	}
	
}