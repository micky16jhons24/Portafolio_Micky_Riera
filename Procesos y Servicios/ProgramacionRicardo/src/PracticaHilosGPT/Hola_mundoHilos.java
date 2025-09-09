package PracticaHilosGPT;

public class Hola_mundoHilos {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		hilo1 hilouno=new hilo1();
		hilo2 hilodos=new hilo2();
		
		Thread h1=new Thread(hilouno);
		Thread h2=new Thread(hilodos);
		h1.start();
		h2.start();
		
		h1.join();
		h2.join();
	}
	
	

}

class hilo1 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.err.println("hello");
		
	}
	
	
}
class hilo2 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Mundo!! <3");
		
	}
	
	
}
