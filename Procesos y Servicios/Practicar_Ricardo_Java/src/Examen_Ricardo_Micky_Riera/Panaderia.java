package Examen_Ricardo_Micky_Riera;

import java.util.Random;

public class Panaderia {

	static String[] clientes = new String [3];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HornoPanaderia pan= new HornoPanaderia();
		int numeroCliente = 5;
		for(int i=0; i< numeroCliente;i++) {
			Thread clienteHilo=new Thread(new Cliente(i , pan));
			clienteHilo.start();
		}
		
		
	}

}


class HornoPanaderia {
	
	
	static String[] clientes = new  String[10];
	static int [] numpanes = new int [10]; 
	int cont=0;
	
	public synchronized void hornear(int Cliente, int cantPanes) {
		System.out.println("Cliente " + Cliente + " esta horneando " + cantPanes + " panes " );
		
		try {
			Thread.sleep(cantPanes * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Cliente " + Cliente + " ha terminado de hornear " );
	}
	
	public int getHornoPanaderia() {
		return cont;
	}
	
}


class Cliente implements Runnable{

	private final int clientePan;
	private final HornoPanaderia horno;
	
	public Cliente(int clientePan ,HornoPanaderia horno ) {
		this.clientePan=clientePan;
		this.horno=horno;
		
	}
	
	@Override
	public void run() {
		Random numeroAleatorio = new Random();
		int canridadpane= numeroAleatorio.nextInt(10)+1;
		System.out.println("Cliente "  + clientePan + " quiere hornear " + canridadpane + " panes");
		horno.hornear(clientePan, canridadpane);
	}
	
}


