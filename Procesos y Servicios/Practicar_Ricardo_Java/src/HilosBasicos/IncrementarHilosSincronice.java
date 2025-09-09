package HilosBasicos;

public class IncrementarHilosSincronice {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		HiloID hilo=new HiloID();
		
		Thread hI=new Thread(()-> hilo.incrementar());
		Thread hD=new Thread(() -> hilo.decrementar());

		hI.start();
		hD.start();
		
		hI.join();
		hD.join();
	}

}
class HiloID {
	private int contComun=0;
	
	public  synchronized void   incrementar() {
		
		for(int i=0; i<10;i++) {
			contComun++;
			System.out.println("incrementando numeros = " + contComun);
		}
	}

	public  synchronized void   decrementar() {
		
		for(int i=10; i>0;i--) {
			contComun--;
			System.out.println("decrementar numeros = " + contComun);
		}
	}
	
	 // Obtener el valor actual
    public int getValor() {
        return contComun;
    }
	
}

