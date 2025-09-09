package Ejercicio_After;

public class Semaforo {
//	private final static int peatonesNum=5;
//	private final static int vehiculosNum=5;
//
//	
//	private final static int[] peatones= new int[peatonesNum];
//	private final static int[] vehiculo= new int[vehiculosNum];


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		trafico tra=new trafico();
		
		
//		for (int i = 0; i < peatones.length; i++) {
			Thread h1=new Thread(()->{
			try {
				tra.Peaton();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
			h1.start();	

//		}
		
//		for (int i = 0; i < vehiculo.length; i++) {
			Thread h2=new Thread(()->{
			try {
				tra.vehiculos();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		h2.start();
		
		}
	
	//h1.join
	//h2.join
		
		
	}

//}



class  trafico{
	private int cont=0;
	
	public synchronized void Peaton() throws InterruptedException {
		while(true) {
		
			//if(cont % 2=0)
		while(cont % 2!=0) {
			wait();
		}
			System.out.println("Semaforo en verde para peatones");
			for(int i=0;i<5;i++) {
				System.out.println("Peaton " + i + " Cruzando....");
				Thread.sleep(1500);
				System.out.println("Peaton " + i + " termino de cruzar....");
				Thread.sleep(1000);

			}
			cont ++;
			notifyAll();
			System.out.println("Semaforo ha cambiado. Ahora pueden pasar :Vehiculos");
		}
		
		
	}
	
	public synchronized void vehiculos() throws InterruptedException {
		while(true) {
			
			//if(cont % 2!=0)
			while(cont % 2==0) {
			wait();
			}
			System.out.println("Semaforo en verde para Vheiculos");
			for(int i=0;i<5;i++) {
				System.out.println("Vehiculos " + i + " Cruzando....");
				Thread.sleep(1500);
				System.out.println("Vehiculos " + i + " termino de cruzar....");
				Thread.sleep(1000);

			}
			cont++;
			notifyAll();
			System.out.println("Semaforo ha cambiado. Ahora pueden pasar :Peatones");
		}
		
	}
}




