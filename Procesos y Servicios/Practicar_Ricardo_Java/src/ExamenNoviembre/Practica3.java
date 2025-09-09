package ExamenNoviembre;

public class Practica3 {
	
//	Objetivo: Crear un programa que incremente una variable compartida entre dos hilos de manera segura.
//	Instrucciones:
//	Crea una clase con una variable contador.
//	Implementa métodos sincronizados para incrementar el contador.


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Contador cont=new Contador();

		HiloIncrementa hilo1=new HiloIncrementa(cont);
		
		Thread h1=new Thread(hilo1);
		Thread h2=new Thread(hilo1);
		
		h1.start();
		h2.start();
		
		h1.join();
		h2.join();
		
		
		System.out.println("Valor final del contador: " + cont.getContador());
	}

}


class HiloIncrementa implements Runnable{
	
	public Contador cont;
	
	public HiloIncrementa(Contador cont) {
		this.cont=cont;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<100; i++) {
			cont.incrementar();
		}
	}
	
}

class Contador{
	private int cont=0;
	
	public synchronized void incrementar() {
		cont++;
	}
	
	public int getContador() {
		return cont;
	}
}


