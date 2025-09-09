package Navidad2024Ejercicios;

public class Prioridades {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		colocarLienzo col= new colocarLienzo();
		colocarPinturas colP=new colocarPinturas();
		Pintar pin=new Pintar();
		
		Thread colocarlienzo = new Thread(col);
		Thread colocarpintura = new Thread(colP);
		Thread pintura = new Thread(pin);
		
		colocarlienzo.setPriority(Thread.MAX_PRIORITY);
		colocarpintura.setPriority(Thread.NORM_PRIORITY);
		pintura.setPriority(Thread.MIN_PRIORITY);
		
		
		//colocarlienzo.setPriority(Thread.MIN_PRIORITY);
		//colocarpintura.setPriority(Thread.NORM_PRIORITY);
		//pintura.setPriority(Thread.MAX_PRIORITY); 
		
		colocarlienzo.start();
		colocarpintura.start();
		pintura.start();


		
	}

}


class colocarLienzo implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("colocando lienzo");
	}
	
}


class colocarPinturas implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("colocando pinturas");
	}
	
}


class Pintar implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("pintando cuadro");
	}
	
}
