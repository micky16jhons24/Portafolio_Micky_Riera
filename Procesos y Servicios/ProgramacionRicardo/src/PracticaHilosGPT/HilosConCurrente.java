package PracticaHilosGPT;

public class HilosConCurrente {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		hiloCurrente hilo1=new hiloCurrente();
		Thread h1=new Thread(hilo1);
		h1.start();
		hiloCurrente2 hilo2=new hiloCurrente2();
		Thread h2=new Thread(hilo2);
		h2.start();
		
		h1.join();
		h2.join();
	}
	

}

class hiloCurrente implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1;i<6;i++) {
			System.out.println("hilo1: "+ i);
		}
	}
	
}
class hiloCurrente2 implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=6;i<11;i++) {
			System.out.println("hilo2: "+ i);
		}
	}
	
}