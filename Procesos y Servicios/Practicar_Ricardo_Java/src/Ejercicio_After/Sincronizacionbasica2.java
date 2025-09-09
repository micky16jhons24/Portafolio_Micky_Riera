package Ejercicio_After;

public class Sincronizacionbasica2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Contador cont=new Contador();
		Thread hilo1=new Thread(() -> {
			
			for(int i =0; i<10000;i++) {
				cont.sumar();
				
			}
		});
		Thread hilo2=new Thread(() -> {
			
			for(int i =0; i<10000;i++) {
				cont.sumar();
				
			}
		});
		
		hilo1.start();
		hilo2.start();
		
		
		hilo1.join();
		hilo2.join();
		
		System.out.println("El contador vale " + cont.getContador());
	}

}


class Contador{
	private int cont=0;
	
	
	public synchronized  void sumar() {
		cont ++;
	}
	
	public int getContador() {
		return cont;
	}
	
}
