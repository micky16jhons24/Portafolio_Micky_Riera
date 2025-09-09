package PracticaHilosGPT;

public class ParesImpares {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		hilo hiloo=new hilo();
		Thread h1=new Thread(hiloo);
		h1.start();
		h1.join();
	}

}

class hilo implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int contpar=0;
		int contimpar=1;
		
		while(contpar < 21 && contimpar < 20) {
			if(contpar %2 == 0) {
			contpar+=2;
			System.out.println("par" + contpar);
		}else {
			contimpar+=2;
			System.out.println("impar" + contimpar);
		}
		}
		
		
	}
	
}
