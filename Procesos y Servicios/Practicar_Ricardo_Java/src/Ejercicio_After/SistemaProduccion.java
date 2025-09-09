package Ejercicio_After;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SistemaProduccion {

	 public static void main(String[] args) {
	        final int NUMERO_PRODUCTOS = 5; // Defino el número de productos a producir
	        final int NUMERO_TRABAJADORES = 3; // Defino el número de trabajadores

	        // Crea una instancia de la línea de producción con la capacidad de productos especificada
	        LineaDeProduccion linea = new LineaDeProduccion(NUMERO_PRODUCTOS);

	        // Agregar productos a la línea de producción
	        for (int i = 1; i <= NUMERO_PRODUCTOS; i++) {
	            linea.agregarProducto(new Producto(i)); // Crea un nuevo producto y lo agrega a la línea de producción
	        }

	        // Crear y arrancar los trabajadores
	        Trabajador[] trabajadores = new Trabajador[NUMERO_TRABAJADORES]; // Crea un arreglo para almacenar los trabajadores
	        for (int i = 0; i < NUMERO_TRABAJADORES; i++) {
	            trabajadores[i] = new Trabajador(linea, i + 1); // Inicializa un trabajador y lo asocia con la línea de producción
	            trabajadores[i].start(); // Arranca el hilo del trabajador
	        }

	        // Esperar a que todos los trabajadores terminen
	        for (Trabajador trabajador : trabajadores) {
	            try {
	                trabajador.join(); // Espera a que el hilo del trabajador termine su ejecución
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt(); // Interrumpe el hilo actual si ocurre una excepción
	                System.err.println("Hilo principal interrumpido."); // Mensaje de error
	            }
	        }

	        System.out.println("Todos los productos han sido ensamblados."); // Mensaje final cuando todos los trabajadores terminan
	    }

}



class Trabajador extends Thread {
    private final LineaDeProduccion linea; // Referencia a la línea de producción
    private final int id; // ID del trabajador

    // Constructor que inicializa la línea de producción y el ID del trabajador
    public Trabajador(LineaDeProduccion linea, int id) {
        this.linea = linea;
        this.id = id;
    }

    // Método que ejecuta el hilo del trabajador
    @Override
    public void run() {
        Random random = new Random(); // Crea un objeto Random para generar números aleatorios
        while (true) {
            Producto producto = linea.tomarProducto(); // Intenta tomar un producto de la línea de producción
            if (producto == null) {
                break; // Si no hay productos disponibles, termina el bucle
            }
            System.out.println("Trabajador " + id + " ensamblando producto " + producto.getId());
            try {
                Thread.sleep(random.nextInt(1000) + 500); // Simula un tiempo de ensamblaje aleatorio entre 500 y 1500 ms
            } catch (InterruptedException e) {
            }
            System.out.println("Trabajador " + id + " terminó de ensamblar producto " + producto.getId());
        }
    }
}


class LineaDeProduccion {
    private final Queue<Producto> productos; // Cola que representa la línea de producción
    private final int capacidad; // Capacidad máxima de la línea de producción

    // Constructor que inicializa la línea de producción con la capacidad especificada
    public LineaDeProduccion(int capacidad) {
        this.productos = new LinkedList<>(); // Inicializa la cola de productos
        this.capacidad = capacidad; // Asigna la capacidad máxima
    }

    // Método sincronizado para agregar un producto a la línea de producción
    public synchronized void agregarProducto(Producto producto) {
        while (productos.size() == capacidad) {
            try {
                System.out.println("Línea de producción llena. Esperando espacio...");
                wait(); // Espera hasta que haya espacio en la línea de producción
            } catch (InterruptedException e) {
            }
        }
        productos.add(producto); // Agrega el producto a la cola
        System.out.println("Producto " + producto.getId() + " agregado a la línea de producción.");
        notifyAll(); // Notifica a todos los hilos que hay un producto disponible
    }

    // Método sincronizado para tomar un producto de la línea de producción
    public synchronized Producto tomarProducto() {
        while (productos.isEmpty()) {
            try {
                System.out.println("No hay productos disponibles. Esperando...");
                wait(); // Espera hasta que haya productos disponibles
            } catch (InterruptedException e) {
            }
        }
        Producto producto = productos.poll(); // Toma el producto de la cabeza de la cola
        System.out.println("Producto " + producto.getId() + " retirado de la línea de producción.");
        notifyAll(); // Notifica a todos los hilos que hay espacio disponible
        return producto; // Devuelve el producto tomado
    }
}


class Producto {
    private final int id; // ID del producto

    // Constructor que inicializa el producto con un ID específico
    public Producto(int id) {
        this.id = id;
    }

    // Método para obtener el ID del producto
    public int getId() {
        return id;
    }
}
