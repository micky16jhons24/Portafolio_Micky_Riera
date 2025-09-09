package hilos;

public class Practica1HilosEasy {
	//array de los hils dormidos
	public static int[] hilosAcabados= new int [4];
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread j[]=new Thread[4];
		
		//creo un objeto hilo de la clase run 
		//y para arrancar uso el Thread
		for(int i=0;i<4;i++) {
		j[i] =(new Thread(new Hilo(i,i)));
		j[i].start();
		}
		for(int i=0;i<4;i++) {
			j[i].join();
			
		}
		System.out.println("ya esta" );
	}
	
	
	
}

 class Hilo implements Runnable{
	 int numhilo;
	
	int segundos;
	public Hilo(int segundos, int numhilo){
		this.segundos=segundos;
		this.numhilo=numhilo;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(segundos*1000);
			Practica1HilosEasy.hilosAcabados[numhilo]=9;
			System.out.printf("Hilo %d ha acabado despues de %d segundos\n" , numhilo,segundos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}