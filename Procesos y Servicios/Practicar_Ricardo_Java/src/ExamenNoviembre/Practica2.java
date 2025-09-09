package ExamenNoviembre;

public class Practica2 {
	
	
//	Objetivo: Crear un programa que ejecute dos hilos. Uno cuenta hacia arriba (1, 2, 3, ...), y otro cuenta hacia abajo (10, 9, 8, ...).
//	Instrucciones:
//	Crea dos clases que implementen Runnable.
//	Implementa los métodos run para contar hacia arriba y hacia abajo.
//	Ejecuta ambos hilos y observa el comportamiento

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		HiloAscendente hilo1=new HiloAscendente();
		
		HiloDescendente hilo2=new HiloDescendente();
		
		Thread h1=new Thread(hilo1);
		Thread h2=new Thread(hilo2);
		
		h1.start();
		h2.start();
		
		h1.join();
		h2.join();
	}

}

class HiloAscendente implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<11;i++) {
			System.out.println("ascendente = " + i);
		}
	}
	
}
class HiloDescendente implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=10;i>0;i--) {
			System.out.println("descendente = " + i);
		}
	}
	
}
