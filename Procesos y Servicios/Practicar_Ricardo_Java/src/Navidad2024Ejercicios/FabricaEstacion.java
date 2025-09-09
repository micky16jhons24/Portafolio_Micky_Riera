package Navidad2024Ejercicios;

import java.util.LinkedList;
import java.util.Queue;

public class FabricaEstacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Estacion estacion = new Estacion();

        // Crear hilos para cada trabajador
        Thread trabajador1 = new Thread(new trabajadores(estacion, 1, "Trabajo Inicial"));
        Thread trabajador2 = new Thread(new trabajadores(estacion, 2, "Trabajo Intermedio"));
        Thread trabajador3 = new Thread(new trabajadores(estacion, 3, "Trabajo Final"));

        // Iniciar los hilos
        trabajador1.start();
        trabajador2.start();
        trabajador3.start();

}

class trabajadores implements Runnable{

	 private final Estacion estacion;
	    private final int etapa;
	    private final String descripcion;

	    public trabajadores(Estacion estacion, int etapa, String descripcion) {
	        this.estacion = estacion;
	        this.etapa = etapa;
	        this.descripcion = descripcion;
	    }

	    @Override
	    public void run() {
	        estacion.realizarTrabajo(etapa, descripcion);
	    }
}


class Estacion{
	 private int etapaActual = 1;

	    public synchronized void realizarTrabajo(int etapa, String descripcion) {
	        while (etapa != etapaActual) {
	            try {
	                wait(); // Esperar hasta que sea la etapa correspondiente
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	                System.err.println("Trabajador interrumpido en la etapa: " + etapa);
	            }
	        }

	        // Realizar la tarea asignada
	        System.out.println("Trabajador " + etapa + " realiza: " + descripcion);

	        // Simular tiempo de trabajo
	        try {
	            Thread.sleep(1000); // Simula trabajo durante 1 segundo
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            System.err.println("Error en la simulación de trabajo.");
	        }

	        // Pasar a la siguiente etapa
	        etapaActual++;
	        notifyAll(); // Notificar a los otros hilos que pueden continuar
	    }
}
}

