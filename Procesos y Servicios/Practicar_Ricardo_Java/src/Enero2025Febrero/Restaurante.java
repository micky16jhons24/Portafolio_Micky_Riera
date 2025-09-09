package Enero2025Febrero;

import java.util.ArrayList;
import java.util.Random;



public class Restaurante {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numCamarero=3;
		int numClientes=4;
		int numMesas = 3; 
	    int numPuertas = 2;
		Thread[] clientes=new Thread[numClientes];
		Thread[] Camarero=new Thread[numCamarero];

        Mesa m = new Mesa(numMesas, numPuertas);

		
		
		for(int i=0; i<clientes.length;i++ ) {
            clientes[i] = new Thread(new Cliente(m, i+1, numPuertas));
		}
		
		for(int i=0; i<Camarero.length;i++ ) {
			Camarero[i]= new Thread(new Camarero(m , i )); 
		}
		
		for(Thread cliente : clientes) {
			cliente.start();
		}
		
		for(Thread camarero : Camarero) {
			camarero.start();
		}

		
		
	}

}



class Cliente implements Runnable{
	Mesa m;
	private int id;
	private int numPuertas;

    public Cliente(Mesa m, int id, int numPuertas) {
        this.m = m;
        this.id = id;
        this.numPuertas = numPuertas;
    }
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		 try {
	            Random random = new Random();
	            int puertaElegida = random.nextInt(numPuertas); 
	            System.out.println("Cliente " + id + " llega a la puerta " + puertaElegida);
	            m.esperarEnFila(puertaElegida, id); 
	            m.ocuparMesa(id); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		
	}
	
	
}


class Camarero implements Runnable {
    Mesa m;
    private int id;

    public Camarero(Mesa m, int id) {
        this.m = m;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) { 
            try {
                int cliente = m.atenderCliente(id); 
                m.liberarMesa(id); 
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Mesa {
    private int mesasOcupadas = 0;
    private final int maxMesas;
    private final ArrayList<ArrayList<Integer>> filas; // Lista de listas para las filas de cada puerta

    public Mesa(int maxMesas, int numPuertas) {
        this.maxMesas = maxMesas;
        filas = new ArrayList<>();
        for (int i = 0; i < numPuertas; i++) {
            filas.add(new ArrayList<>()); // Inicializamos una lista para cada puerta
        }
    }

    public synchronized void ocuparMesa(int cliente) throws InterruptedException {
        while (mesasOcupadas == maxMesas) {
            System.out.println("Cliente " + cliente + " espera porque no hay mesas libres.");
            wait(); 
        }
        mesasOcupadas++;
        System.out.println("Cliente " + cliente + " ocupa una mesa. Mesas ocupadas: " + mesasOcupadas);
        notifyAll(); // Notificamos a los camareros que hay mesas ocupadas
    }

    public synchronized void esperarEnFila(int puerta, int cliente) throws InterruptedException {
        filas.get(puerta).add(cliente); // Cliente se pone en la fila de la puerta
        System.out.println("Cliente " + cliente + " está esperando en la fila de la puerta " + puerta);
        notifyAll(); // Notificar a los camareros
        while (filas.get(puerta).get(0) != cliente) { // Esperar hasta que sea su turno
            wait();
        }
        filas.get(puerta).remove(0); // El cliente sale de la fila
        System.out.println("Cliente " + cliente + " es atendido en la puerta " + puerta);
    }

    public synchronized int atenderCliente(int camarero) throws InterruptedException {
        while (todasLasFilasVacias()) { // Esperar si no hay clientes en ninguna fila
            System.out.println("Camarero " + camarero + " espera porque no hay clientes.");
            wait(); // El camarero espera hasta que haya clientes
        }
        for (int i = 0; i < filas.size(); i++) {
            if (!filas.get(i).isEmpty()) { // Encontrar la primera fila con clientes
                int cliente = filas.get(i).get(0); // Obtener al primero de la fila
                System.out.println("Camarero " + camarero + " atiende al cliente " + cliente + " de la puerta " + i);
                filas.get(i).remove(0); // El cliente sale de la fila
                notifyAll(); // Avisar a los clientes que han sido atendidos
                return cliente;
            }
        }
        return -1; 
    }

    public synchronized void liberarMesa(int camarero) throws InterruptedException {
        while (mesasOcupadas == 0) {
            System.out.println("Camarero " + camarero + " espera porque no hay mesas que liberar.");
            wait(); // El camarero espera si no hay mesas ocupadas
        }
        mesasOcupadas--; // Liberar una mesa
        System.out.println("Camarero " + camarero + " libera una mesa. Mesas ocupadas: " + mesasOcupadas);
        notifyAll(); // Notificar que una mesa se ha liberado
    }

    private boolean todasLasFilasVacias() {
        for (ArrayList<Integer> fila : filas) {
            if (!fila.isEmpty()) {
                return false; 
            }
        }
        return true;
    }
}