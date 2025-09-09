package RetosDeProgramacion;

public class Semaforonuevo {
	    public static void main(String[] args) {
	        final int numvehiculos = 4;
	        final int numpeatones = 4;

	        Thread[] vehiculo = new Thread[numvehiculos];
	        Thread[] peaton = new Thread[numpeatones];

	        semaforo s = new semaforo();

	        // Crear y arrancar los hilos de vehículos
	        for (int i = 0; i < vehiculo.length; i++) {
	            vehiculo[i] = new Thread(new Vehiculo(s, i));  // Cambié hilo1 a Vehiculo
	        }

	        // Iniciar los hilos de vehículos
	        for (Thread v : vehiculo) {
	            v.start();
	        }

	        // Crear y arrancar los hilos de peatones
	        for (int i = 0; i < peaton.length; i++) {
	            peaton[i] = new Thread(new Peaton(s, i));  // Cambié hilo2 a Peaton
	        }

	        // Iniciar los hilos de peatones
	        for (Thread v : peaton) {
	            v.start();
	        }
	    }
	}

	// Renombré la clase de hilo1 a Vehiculo
	class Vehiculo implements Runnable {

	    private semaforo s;
	    private int id;

	    public Vehiculo(semaforo s, int id) {
	        this.s = s;
	        this.id = id;
	    }

	    @Override
	    public void run() {
	        try {
	            s.verde();  // Los vehículos esperan a que el semáforo esté verde
	            System.out.println("Vehículo " + id + " está cruzando...");
	            Thread.sleep(3000);  // Simulamos que el vehículo pasa
	            System.out.println("Vehículo " + id + " terminó de cruzar.");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

	// Renombré la clase de hilo2 a Peaton
	class Peaton implements Runnable {

	    private semaforo s;
	    private int id;

	    public Peaton(semaforo s, int id) {
	        this.s = s;
	        this.id = id;
	    }

	    @Override
	    public void run() {
	        try {
	            s.rojo();  // Los peatones esperan a que el semáforo esté rojo
	            System.out.println("Peatón " + id + " está cruzando...");
	            Thread.sleep(3000);  // Simulamos que el peatón pasa
	            System.out.println("Peatón " + id + " terminó de cruzar.");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

	class semaforo {

	    private boolean color = true;  // true = verde para vehículos, false = rojo para vehículos
	    private int vehiculosPendientes = 0;  // Contador de vehículos que deben pasar
	    private int peatonesPendientes = 0;   // Contador de peatones que deben pasar
	    private final int MAX_VEHICULOS = 4;  // Número total de vehículos
	    private final int MAX_PEATONES = 4;   // Número total de peatones

	    // Cuando los vehículos pasan, el semáforo debe estar verde para ellos
	    public synchronized void verde() throws InterruptedException {
	        while (color == false || peatonesPendientes > 0) {  // Esperar si el semáforo no es verde o los peatones están cruzando
	            wait();
	        }
	        System.out.println("Semáforo en verde para VEHÍCULOS.");
	        
	        // Permitir que todos los vehículos crucen
	        while (vehiculosPendientes < MAX_VEHICULOS) {
	            vehiculosPendientes++;  // Aumentar el contador de vehículos que están cruzando
	            notifyAll();  // Notificar a todos los vehículos
	            wait();  // Los vehículos deben esperar a que todos crucen
	        }

	        // Una vez que todos los vehículos pasen, cambiamos el semáforo
	        cambioDeColor();
	    }

	    // Cuando los peatones pasan, el semáforo debe estar rojo para ellos
	    public synchronized void rojo() throws InterruptedException {
	        while (color == true || vehiculosPendientes > 0) {  // Esperar si el semáforo no es rojo o los vehículos están cruzando
	            wait();
	        }
	        System.out.println("Semáforo en verde para PEATONES.");
	        
	        // Permitir que todos los peatones crucen
	        while (peatonesPendientes < MAX_PEATONES) {
	            peatonesPendientes++;  // Aumentar el contador de peatones que están cruzando
	            notifyAll();  // Notificar a todos los peatones
	            wait();  // Los peatones deben esperar a que todos crucen
	        }

	        // Una vez que todos los peatones pasen, cambiamos el semáforo
	        cambioDeColor();
	    }

	    // Cambiar el color del semáforo
	    public synchronized void cambioDeColor() {
	        color = !color;  // Cambia el estado del semáforo
	        if (color) {
	            System.out.println("Semáforo cambiado. Ahora pueden pasar: VEHÍCULOS");
	        } else {
	            System.out.println("Semáforo cambiado. Ahora pueden pasar: PEATONES");
	        }
	        notifyAll();  // Notificar a todos los hilos que están esperando
	    }
	}


//class semaforo {
//    
//    boolean color = true;  // True = verde para vehículos, false = rojo para vehículos
//    
//    public synchronized void verde() throws InterruptedException {
//        while (color == false) {  // Los vehículos esperan a que el semáforo esté verde
//            wait();
//        }
//        System.out.println("En verde para los peatones");
//        System.out.println("En rojo para los coches");
//        Thread.sleep(1000);
//        cambioDeColor();  // Cambio de color después de que los vehículos pasen
//    }
//    
//    public synchronized void rojo() throws InterruptedException {
//        while (color == true) {  // Los peatones esperan a que el semáforo esté rojo
//            wait();
//        }
//        System.out.println("En rojo para los peatones");
//        System.out.println("En verde para los coches");
//        Thread.sleep(1000);
//        cambioDeColor();  // Cambio de color después de que los peatones pasen
//    }
//    
//    public synchronized void cambioDeColor() {
//        color = !color;  // Cambia el estado del semáforo
//        System.out.println("Cambio de color");
//        notify();  // Despierta a todos los hilos que están esperando
//    }
//}
