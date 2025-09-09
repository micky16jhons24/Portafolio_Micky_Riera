package Sincronizar;

public class EJ1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		contador cont = new contador();
		
		hilo hilo1=new hilo(cont);
		hilo hilo2=new hilo(cont);
		hilo hilo3=new hilo(cont);

		
		Thread h1=new Thread(hilo1);
		Thread h2=new Thread(hilo2);
		Thread h3=new Thread(hilo3);

		h1.start();
		h2.start();
		h3.start();

		try {
			h1.join();
			h2.join();
			h3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("vlor final del contador " + cont.getcontador());
	}
	
	

}

class contador  {
	
	private int cont=0;
	
	public synchronized void  incrementar() {
		 cont++;
		 System.out.println(Thread.currentThread().getName() + " incremento a: " + cont);
	}
	public int getcontador() {
		return cont;
	}
}


class hilo implements Runnable{
	
	private contador conta;
	
	public hilo(contador conta) {
		this.conta=conta;	
		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i=0; i < 10; i++) {
			conta.incrementar();
		}
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

