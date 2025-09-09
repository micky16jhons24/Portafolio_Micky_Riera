package PracticaHilosGPT;

public class ParesImpares2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
        ParesImpares impresor = new ParesImpares();
        Thread hiloPar = new Thread(new hilopar(impresor));
        Thread hiloImpar = new Thread(new hiloImpar(impresor));

        hiloPar.start();
        hiloImpar.join();
	}

}

class hilopar implements Runnable{
	private final ParesImpares impresor;

    public hilopar(ParesImpares impresor) {
        this.impresor = impresor;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i += 2) {
            try {
                impresor.imprimirPar(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
	
}
class hiloImpar implements Runnable{
	private final ParesImpares impresor; 
	public hiloImpar(ParesImpares impresor) {
		// TODO Auto-generated constructor stub
		this.impresor=impresor;
		
	}
	

	@Override
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            try {
                impresor.imprimirImpar(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
	
	
}
class ParesImpares {
    private boolean turnoPar = true;

    public synchronized void imprimirPar(int numero) throws InterruptedException {
        while (!turnoPar) {
            wait();
        }
        System.out.println("Par: " + numero);
        turnoPar = false;
        notifyAll();
    }

    public synchronized void imprimirImpar(int numero) throws InterruptedException {
        while (turnoPar) {
            wait();
        }
        System.out.println("Impar: " + numero);
        turnoPar = true;
        notifyAll();
    }
}
