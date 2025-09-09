package Ejercicio_After;

public class sincronizacionBasica {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		sincronizacion sin=new sincronizacion();
		
		Thread h1=new Thread(()-> {
			for(int i=0; i<100; i++) {
				
				sin.incrementar();
				System.out.println("hilo1");
			}
		});
		Thread h2=new Thread(()-> {
			for(int i=0; i<100; i++) {
				
				sin.incrementar();
				System.out.println("hilo2");
			}
		});
		
		
		h1.start();
		h2.start();
		
		h1.join();
		h2.join();

	}

}



class sincronizacion{
	int cont = 0;
	
	public synchronized void incrementar() {
		cont++;
		System.out.println(cont);
	}
	
	public int getsincronizacion() {
		return cont;
	}

	
}