package Ejercicio_After;

public class sistemaDeProducccion {
	
	
	public static void main(String[] args) {
	int numProductos=5;
	int numTrabajdores=5;
	
		 LineaDeProduccion linea = new LineaDeProduccion(numProductos);
    for (int i = 1; i <= numProductos; i++) {
//        linea.agregarProducto(new Producto(i)); // Crea un nuevo producto y lo agrega a la línea de producción
    }
    
 // Crear y inicializar los trabajadores uno a uno 
    Trabajador[] trabajadores = new Trabajador[numTrabajdores]; // Crea un arreglo para almacenar los trabajadores
    for (int i = 0; i < numTrabajdores; i++) {
//        trabajadores[i] = new Trabajador(); // Inicializa un trabajador y lo asocia con la línea de producción
        trabajadores[i].start(); // Arranca el hilo del trabajador
    }

    // Esperar a que todos los trabajadores terminen
//    for (Trabajador trabajador : trabajadores) {
//        try {
//            trabajador.join(); // Espera a que el hilo del trabajador termine su ejecución
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Interrumpe el hilo actual si ocurre una excepción
//            System.err.println("Hilo principal interrumpido."); // Mensaje de error
//        }
//    }
	
   
}

class Trabajador implements Runnable{
	private int idTrbajador;
	
	public Trabajador() {
		this.idTrbajador=idTrbajador;
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void join() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 System.out.println("Trabajador " + idTrbajador + " ensamblando");
//         try {
////             Thread.sleep(Math.random()*1000); // Simula un tiempo de ensamblaje aleatorio entre 500 y 1500 ms
////         } catch (InterruptedException e) {
//         }
         System.out.println("Trabajador " + idTrbajador + " terminó de ensamblar producto ");
	}
	
	
}


class LineaProduccion {
	private int capacidad;
	
	public LineaProduccion(int capacidad) {
		this.capacidad=capacidad;
	}
	
	public synchronized void agregarProducto(Producto producto) throws InterruptedException {
        System.out.println("Línea de producción llena. Esperando espacio...");
        wait(); // Espera hasta que haya espacio en la línea de producción
        System.out.println("Producto " + producto.getId() + " agregado a la línea de producción.");
    }

    // Método sincronizado para tomar un producto de la línea de producción
    public synchronized Producto tomarProducto(Producto producto) throws InterruptedException {
        System.out.println("No hay productos disponibles. Esperando...");
        wait(); // Espera hasta que haya productos disponibles
        System.out.println("Producto " + producto.getId() + " retirado de la línea de producción.");
        return producto; // Devuelve el producto tomado
    }
}


class Producto{
	private int id;
	
	public Producto(int id) {
		this.id=id;
	}
	
	public int getId() {
        return id;
    }
	
}
}