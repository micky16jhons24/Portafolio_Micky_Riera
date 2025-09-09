package Navidad2024Ejercicios;

import java.util.ArrayList;

public class ProductoConsumidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FILA c=new FILA();
		Thread h1=new Thread(new consumidor(c));
		Thread h2=new Thread(new producto(c));
		
		h1.start();
		h2.start();
		

	}

}


class consumidor implements Runnable{
	private FILA c;
	public consumidor(FILA c) {
		this.c=c;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<5;i++) {
			try {
				int num=c.imprimirCola();
				
				System.out.println("imprimiendo " + num);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class producto implements Runnable{

	private FILA c;
	
	public producto(FILA c) {
		this.c=c;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i=0; i<5;i++) {
			try {
				c.annadirCola(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}


class FILA{
	
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
