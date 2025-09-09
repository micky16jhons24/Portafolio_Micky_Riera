package Navidad2024Ejercicios;

import java.util.Random;

public class HotelesReserva {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numpersonas=666;
		Thread[] clientes=new Thread[numpersonas];
		Habitaciones habitaciones=new Habitaciones();
		
		for(int i=0; i<clientes.length;i++ ) {
			clientes[i]= new Thread(new personas(i, habitaciones)); 
		}
		
		
		for(Thread cliente : clientes) {
			cliente.start();
		}

	}

}


class personas implements Runnable{
	
	int id;
	Habitaciones habitacion;
	public personas(int id, Habitaciones habitacion) {
		this.id=id;
		this.habitacion=habitacion;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("cliente " + id + " intentando reservar una habitacion");
		habitacion.libre(id);
		try {
			Random ran= new Random();
			Thread.sleep(1000 + ran.nextInt(4000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		habitacion.DuracionHabitacion(id);
		
	}
	
}


class Habitaciones {
	 static int reservas=0;
	public synchronized void libre(int id) {
		while(reservas == 5) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			System.out.println("habitacion resrvada por el cliente " + id );
			reservas++;
		if (reservas == 5) {
			System.out.println("habitaciones ocupadas");
		}
	}
	public synchronized void DuracionHabitacion(int id) {
			System.out.println("habitacion disponible de nuevo, el cliente " + id +" ha dejado la habitacion");
			reservas--;
			notifyAll();
		}
	}
	
	

