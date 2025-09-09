package hilos;

import java.util.Random;

public class HilosPrimerEjercicio {
	
	static int [] posiciones =new int[100];//array de 100 posiciones

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		 int num_hilos=20; //numeor de hilos
		 int cont=5; //esto e para cada hilo llene 5 posciones del array
		 
	
		 for(int i=0; i<num_hilos;i++) {
			 //creo el inicio para saber desde donde empiezo y como tengo que ir de 5 en cinco 
			 //aqui inicio con 0*5=0 y empieza desde la posicion 0
			 int inicio=i*cont;
			 //aqui indico la posicon en la ttiene que finalizar osea 0 +5 = 5
			 int fin=inicio*cont;
			 
			 //asi que empieza por cero y acaba en 5 y asi hacemos que cada hilo ocupe cinco posciones del array
			 
			 //aqui creo el hilo y tengo que pasar los parametros desde donde empieza hasta donde acabe 
			 Thread hilo=new Thread(new EjecutarHilos(inicio,fin) );//creo un objeto de la clase EjecutarHilos y pasolos parametros inicio y final
			 hilo.start();//inicia el hilo
			 hilo.join(1000);//espera q ue termine el hilo antes de continuar y esperamos 1 seg
			
		 }
		 
		 //aqui recorro  y imprimo el array de cada posicion
		 System.out.println("contenido dentro de la array");
		 for(int i=0;i < posiciones.length;i++ ) {
			 System.out.println(posiciones[i] + " ");
		 }
		 
		
	}
	

}
//esta clase define el trabajo de cada hilo
class EjecutarHilos implements Runnable{
		private int inicio;
		private int fin;
		private Random rand=new Random();//esto nos da un numero random 
		
		//Constructor
		public EjecutarHilos(int inicio,int fin ) {
			this.inicio=inicio;
			this.fin=fin;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//recorre desde el  inicio al fin y lena el array 
			for(int i= inicio; i<fin;i++) {
				HilosPrimerEjercicio.posiciones[i]=i+1;
				System.out.println("Hilo posicion " + i + " con el valor" + (i+1));
			}
			
			int dormir = rand.nextInt(4)*1000; //convierto segundos a milisegundos
			//ahora se lo voy a implementar a thread
			try {
				Thread.sleep(dormir);//esto manda a domir  al hilo pero unos segundos entre 0-3(aleatorios)
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}