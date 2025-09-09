package HilosBasicos;

public class HiloSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hilo hilo=new Hilo();
		Thread h1=new Thread(hilo);
		
		h1.start();
	}

}

class Hilo implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<11;i++) {
			System.out.println("numero = " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
