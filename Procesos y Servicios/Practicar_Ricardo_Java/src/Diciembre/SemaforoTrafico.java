package Diciembre;

public class SemaforoTrafico {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		final int numPeatones=4;
		final int numvehiculos=4;
		
		Semaforo semaforo=new Semaforo();

		
		Thread[] peatones=new Thread [numPeatones];
		Thread[] vehiculos=new Thread [numvehiculos];
		
		for(int i=0; i<peatones.length;i++) {
			peatones[i]=new Thread(new Peaton(i +1 , semaforo));
			
		}
		
		for(int i=0; i<vehiculos.length;i++) {
			vehiculos[i]=new Thread(new Vehiculos(i +1 , semaforo));
			
		}
		
		
		for(Thread peaton : peatones) {
			peaton.start();
		}
		
		for(Thread vehiculo : vehiculos) {
			vehiculo.start();
		}
		
		for(Thread peaton : peatones) {
			try {
				peaton.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Thread vehiculo : vehiculos) {
			try {
				vehiculo.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}


class Vehiculos implements Runnable{
	
	public int id;
	public  Semaforo semaforo;

	
	public Vehiculos(int id, Semaforo semaforo) {
		this.id=id;
		this.semaforo=semaforo;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			semaforo.pasaVehiculos(id);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


class Peaton implements Runnable{

	public int id;
	public  Semaforo semaforo;
	
	public Peaton(int id , Semaforo semaforo) {
		this.id=id;
		this.semaforo=semaforo;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			semaforo.pasaPeaton(id);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}


class Semaforo{
	boolean color=true;
	
	public synchronized void pasaPeaton(int id) throws InterruptedException {
		
		while(color) {
			wait();
		}
		System.out.println("Peaton "+id+"cruzando" );
		Thread.sleep((long) (Math.random()*300+100));
		System.out.println("Peaton "+id+"terminos de CRUZAR" );
		cambioColor();
	}
	
	public synchronized void pasaVehiculos(int id) throws InterruptedException {
		while(!color) {
			wait();
		}
		System.out.println("Vehiculo "+id+"cruzando" );
		Thread.sleep((long) (Math.random()*300+100));
		System.out.println("Vehiculo "+id+"terminos de CRUZAR" );
		cambioColor();
		
		
		
	}
	
	public synchronized void cambioColor() {
		color=!color;
		notifyAll();
		
		if(!color) {
			System.out.println("Semáforo cambiado. Ahora pueden pasar: PEATONES");
			System.out.println("Esta en verde para los peatones");

		}else {
			System.out.println("Semáforo cambiado. Ahora pueden pasar: vehiculos");
			System.out.println("Esta en verde para los vehiculos");
		}
	}
	
	
}