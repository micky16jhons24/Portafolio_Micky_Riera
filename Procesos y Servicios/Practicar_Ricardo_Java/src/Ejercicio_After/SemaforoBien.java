package Ejercicio_After;

public class SemaforoBien {
    public static void main(String[] args) throws InterruptedException {
        final int vehiculosnum = 4;
        final int peatonessnum = 4;

        Semaforos semaforo = new Semaforos();

        Thread[] vehiculos = new Thread[vehiculosnum];
        Thread[] peatones = new Thread[peatonessnum];

        for (int i = 0; i < vehiculos.length; i++) {
            vehiculos[i] = new Thread(new Coches(i + 1, semaforo));
        }

        for (int i = 0; i < peatones.length; i++) {
            peatones[i] = new Thread(new Peaton(i + 1, semaforo));
        }

        for (Thread vehiculo : vehiculos) {
            vehiculo.start();
        }

        for (Thread peaton : peatones) {
            peaton.start();
        }

        for (Thread vehiculo : vehiculos) {
            try {
                vehiculo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        semaforo.cambiaElColor();
        Thread.sleep(3000);

        for (Thread peaton : peatones) {
            try {
                peaton.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        semaforo.cambiaElColor();
        Thread.sleep(3000);
    }
}

class Peaton implements Runnable {
    private int id;
    private Semaforos semaforo;

    public Peaton(int id, Semaforos semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.esperandoPeatones();
            System.out.println("Persona " + id + " está cruzando...");
            Thread.sleep((long) (Math.random() * 3000) + 1000);
            System.out.println("Persona " + id + " terminó de cruzar.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Coches implements Runnable {
    private int id;
    private Semaforos semaforo;

    public Coches(int id, Semaforos semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            synchronized(cont);
        	semaforo.esperandocoches();
            
            System.out.println("Vehículo " + id + " está cruzando...");
            Thread.sleep((long) (Math.random() * 3000) + 1000);
            System.out.println("Vehículo " + id + " terminó de cruzar.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Semaforos {
    private boolean color = true;
    private int cont =0;
    

    public synchronized void esperandocoches() throws InterruptedException {
        while (!color) {
            wait();
        }
    }

    public synchronized void esperandoPeatones() throws InterruptedException {
        while (color) {
            wait();
        }
    }

    public synchronized void cambiaElColor() {
        color = !color;
        notifyAll();

       if(!color) {
    	   System.out.println("pueden pasar peatones");

       }else{
    	 System.out.println("pueden pasar vehiculos");  

       }
    }
}
