package HilosBasicos;

public class Imprimir {
public static void main(String[] args) {
	
	Hilos hilo=new Hilos();
	Thread h1=new Thread(hilo);
	
	h1.start();
	
}
}

class Hilos implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 6; i++) {
			System.out.println("Hola mundo " + i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
