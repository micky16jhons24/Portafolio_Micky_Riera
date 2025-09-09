package PracticaHilosGPT;

public class ContadorSimple {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		contador cont=new contador();
		
		hilo h1=new hilo(cont);
		hilo h2=new hilo(cont);
		
		Thread hilo1= new Thread(h1);
		Thread hilo2= new Thread(h2);
		
		hilo1.start();
		hilo1.join();
		hilo2.start();
		hilo2.join();

//		int cont=0;
//		if(cont % 2 ==0 ) {
//			hilo1.start();
//			cont++;
//		}else {
//			hilo2.start();
//			cont++;
//		}
//		hilo1.join();
//		hilo2.join();

	}

}
class hilo implements Runnable{
	private final contador cont;
	
	public hilo(contador cont) {
		this.cont=cont;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++) {
			cont.incrementar();
		}
	}
	
}
class contador{
	private int cont=0;
	
	public synchronized void incrementar() {
		cont++;
	}
	
	public int getcontador() {
		return cont;
		
	}
}
