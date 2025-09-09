package PracticaHilosGPT;

public class Hola2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		hilos hilo1=new hilos();
		Thread h1=new Thread(hilo1);
		h1.start();
		h1.join();
	}

}

   class hilos implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hola mundo");
	}
	
}


