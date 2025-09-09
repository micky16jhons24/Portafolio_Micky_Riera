package PracticaHilosGPT;

//Ejercicio: Hilo con Parámetros
//
//Crea una clase que implemente Runnable y que reciba un parámetro (por ejemplo, un nombre) en su constructor. El hilo debe imprimir el nombre que recibió como parámetro al ejecutarse.
//
//En el método main, crea y lanza varios hilos, pasando diferentes nombres a cada uno de ellos, y observa cómo se ejecutan de forma concurrente.

public class Practica3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		
		
		Thread hilo1=new Thread(new nombre("Micky"));
		hilo1.start();
		
		Thread hilo2=new Thread(new nombre("Lucia"));
		hilo2.start();
		
		Thread hilo3=new Thread(new nombre("Andres"));
		hilo3.start();
		
		Thread hilo4=new Thread(new nombre("MAMA"));
		hilo4.start();
	}
	


}

class nombre implements Runnable{
	
	public String nombre;
	
	
	public nombre(String nombre) {
		this.nombre=nombre;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			System.out.println("hola mi nombre es " + nombre + " desde el hilo");
		
		
		
		
		
		
	}
	
}
