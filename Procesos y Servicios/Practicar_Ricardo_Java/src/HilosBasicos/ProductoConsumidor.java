package HilosBasicos;

import java.util.LinkedList;
import java.util.Queue;

public class ProductoConsumidor {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Consumidor consumo=new Consumidor();
		
		//producto
		Thread productor=new Thread(()->{
			for(int i=0; i<5;i++) {
				try {
					consumo.producir(i);
					System.out.println("Producido = " + i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		Thread consumidor=new Thread(()-> {
			for(int i=0; i<5; i++) {
				try {
					int valor=consumo.consumidor();
					System.out.println("Consumidor: " + valor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		productor.start();
		consumidor.start();
		
		productor.join();
		consumidor.join();
	}

}


class Consumidor{
	private Queue<Integer> lista=new LinkedList<>();
	private int capacidad=5;
	
	
	public synchronized void producir(int valor) throws InterruptedException {
		while(lista.size() == capacidad) {
			wait();
		}
		
		lista.add(valor);
	}
	
	public synchronized int consumidor() throws InterruptedException {
		while(lista.isEmpty()) {
			wait();
		}
		
		int valor = lista.poll();
		System.out.println("Consumido: " + valor );
		notifyAll();
		return valor;
	}
	

}