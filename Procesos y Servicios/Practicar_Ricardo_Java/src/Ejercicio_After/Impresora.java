package Ejercicio_After;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Impresora {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int numDocumentos = random.nextInt(10) + 1;


        capacidad c = new capacidad();

        for (int i = 0; i < numDocumentos; i++) {
            String nombreDocumento = "Documento- " + (i + 1);
            Thread hiloEnvio = new Thread(new hiloEnvio(nombreDocumento, c));
            hiloEnvio.start();
        }
        
        
        
        
       Thread hiloImprimido = new Thread(new hiloImprimiendo(c));
       hiloImprimido.start();

       
    }
}

class hiloEnvio implements Runnable {
    private String nombre;
    private capacidad c;

    public hiloEnvio(String nombre, capacidad c) {
        this.nombre = nombre;
        this.c = c;
    }

    @Override
    public void run() {
        try {
            System.out.println("Se están enviando documentos: " + nombre + " .......");
            Thread.sleep((long) (Math.random() * 4000) + 1000);
            c.ponerCola(nombre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class hiloImprimiendo implements Runnable {
    private capacidad c;

    public hiloImprimiendo( capacidad c) {
        this.c = c;
    }

    @Override
    public void run() {
        try {
        	while (true) {
        		String documento = c.imprimirCola();
                System.out.println("Se está imprimiendo el documento: " + documento + " este proceso puede durar entre 0,5 y 5 segundos");
                Thread.sleep((long) (Math.random() * 2500) + 5000);
                System.err.println("Ya se ha impreso el documento: " + documento);
            }   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class capacidad {
    private int capacidadMaxima = 10;
    private final Queue<String> cola=new LinkedList<>();  //cola de impresion   
    
    

    public synchronized void ponerCola(String documento) throws InterruptedException {
        while  (cola.size() == capacidadMaxima)  {
            System.out.println("cola llena. Espera que que halla un espacio ");
        	wait();
        }
        
        cola.add(documento);
        System.out.println("docuemnto añadido a la cola " + documento);
        notifyAll();
        
        
    }
    
    
    
    public synchronized String imprimirCola() throws InterruptedException {
    	while(cola.isEmpty()) {
    		System.out.println("cola Vacia. Esperando documentos");
    		wait();
    	}
    	
    	String documento = cola.poll(); //saca  el documento de la cola
    	notifyAll();
    	return documento;
    }

  
    
   
}
