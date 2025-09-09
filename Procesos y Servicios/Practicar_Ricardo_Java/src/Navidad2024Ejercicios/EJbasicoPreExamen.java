package Navidad2024Ejercicios;

public class EJbasicoPreExamen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sin si=new sin();
		
		Thread hilopar= new Thread(new par(si));
		
		Thread hiloimpar= new Thread(new impar(si));
		
		
		hilopar.start();
		hiloimpar.start();
		
		
		

	}
	}
	
	
	
	class par implements Runnable{
		
		private sin si;
		
		public par(sin si) {
			this.si=si;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				si.contadorPar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	class impar implements Runnable{
		private sin si;
		
		public impar(sin si) {
			this.si=si;
		}
		@Override
		public void run() {
			try {
				si.contadorImpar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		}
	
	class sin {
		static int cont=0;
		
		public synchronized void contadorPar() throws InterruptedException {
			while(cont<10) {
				if(cont %2==0) {
			
				System.out.println("NP " + cont);
				cont++;
				notify();
			}else {
				wait();
			}
			}
		}
		public synchronized void contadorImpar() throws InterruptedException {
			
			while(cont  < 10) {
				if(!(cont %2==0)) {
				System.out.println("NI " + cont);
				cont++;
				notify();
			}else {
				wait();
			}
				
			}
			
			
		}
		
		
	}
		
	
	
	
	


