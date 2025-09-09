package hilos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class numeroOculto {

    // Número que deben adivinar los hilos, inicializado por el hilo principal
    private static int numAdivinar;
    // Indica si el número ha sido adivinado
    private static AtomicBoolean encontrado = new AtomicBoolean(false);

    public static void main(String[] args) {
        Random numAleatorio = new Random();
        
        // Generar el número a adivinar entre 0 y 100
        numAdivinar = numAleatorio.nextInt(101);
        System.out.println("Número a adivinar: " + numAdivinar);
        
        // Iniciar 10 hilos
        for (int i = 0; i < 10; i++) {
            new Thread(new Hilo(i)).start();
        }
    }

    // Método que comprueba si el intento de adivinanza es correcto
    public static int propongoNumero(int num) {
        // Si ya se encontró el número, devolver -1 para indicar que el juego terminó
        if (encontrado.get()) {
            return -1;
        }
        // Si el número propuesto es correcto, marcar el juego como terminado y devolver 1
        if (num == numAdivinar) {
            encontrado.set(true);
            return 1;
        }
        // Si el número es incorrecto y el juego sigue activo, devolver 0
        return 0;
    }

    // Clase que representa cada hilo adivinador
    static class Hilo implements Runnable {
        int numHilo;

        public Hilo(int numHilo) {
            this.numHilo = numHilo;
        }

        @Override
        public void run() {
            Random rando = new Random();
            
            // Mientras el número no se haya encontrado, intentar adivinar
            while (!encontrado.get()) {
                int intento = rando.nextInt(101); // Genera un intento aleatorio
                System.out.println("Hilo " + numHilo + " intenta con el número: " + intento);
                
                int resultado = propongoNumero(intento);
                
                if (resultado == -1) {
                    System.out.println("Hilo " + numHilo + " se detiene, el juego ya ha terminado.");
                    return; // Finalizar el hilo si el juego terminó
                } else if (resultado == 1) {
                    System.out.println("¡Hilo " + numHilo + " ha adivinado el número " + numAdivinar + "!");
                    return; // Finalizar el hilo si adivinó el número
                } else {
                    System.out.println("Hilo " + numHilo + " no adivinó, sigue intentando.");
                }
                
                // Pausa para facilitar la lectura de la salida
                try {
                    Thread.sleep(100); // Pausa de 100 milisegundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
