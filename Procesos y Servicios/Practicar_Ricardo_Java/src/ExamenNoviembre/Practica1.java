package ExamenNoviembre;

public class Practica1 {
//	1. Ejercicio Básico: Hola Mundo con Hilos
//	Objetivo: Crear un hilo simple que imprima "Hola, Mundo" en la consola.
//	Instrucciones:
//	Crea una clase que extienda Thread o implemente Runnable.
//	En el método run, imprime "Hola, Mundo".
//	Inicia el hilo usando el método start
	public static void main(String[] args) throws InterruptedException {
		hilo Hilo=new hilo();
	Thread h1=new Thread(Hilo);
	h1.start();
	h1.join();
	
	}
	
	
}

class hilo implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hola mundo");

	}
	
}
