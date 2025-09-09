package Navidad2024Ejercicios;

import java.util.ArrayList;

public class EJ2Poductoconsumidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		cola c=new cola();
		
		Thread G=new Thread(new hiloGenera(c));
		Thread I=new Thread(new hiloImprime(c));
		
		
		G.start();
		I.start();
	}

}



class hiloGenera implements Runnable{
	
	private cola cola1;
	
	public hiloGenera(cola cola1) {
		this.cola1=cola1;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=1; i < 6; i++) {
			try {
				cola1.annadirCola(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}


class hiloImprime implements Runnable{
	
	private cola cola1;
	
	public hiloImprime(cola cola1) {
		this.cola1=cola1;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
        for (int i = 1; i <= 5; i++) { // Consume exactamente 5 elementos
		try {
			int num = cola1.imprimirCola();
			System.out.println("se esta imprimiendo" + num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
	}
	
}



class cola{
	
	private int capacidadMax=5;
private final ArrayList<Integer> cola=new ArrayList<>();

public synchronized void annadirCola (int num) throws InterruptedException {
	while(cola.size() == capacidadMax) {
		System.out.println("cola llena ");
		wait();
	}
	cola.add(num);
	System.out.println("numero añadido" + num);
	notifyAll();
	
}


public synchronized  int imprimirCola() throws InterruptedException {
	
	while(cola.isEmpty()) {
		System.out.println("cola vacia");
		wait();
	}
    
	int num=cola.remove(0);
	return num;	
	
}
}