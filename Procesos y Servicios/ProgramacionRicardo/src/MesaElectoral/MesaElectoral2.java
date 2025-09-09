package MesaElectoral;

import java.util.Random;

public class MesaElectoral2 {
	private static int totalVotosPP = 0;
    private static int totalVotosPSOE = 0;

    public static void main(String[] args) throws InterruptedException {
        int numMesas = 5; // Número de mesas electorales
        int intervalo = 20000; // 20 segundos

        // Lanzar un subproceso por cada mesa electoral
        for (int i = 1; i <= numMesas; i++) {
            int r = (int) (Math.random() * 9) + 1; // Número aleatorio de mesa electoral (1-9)
            int n = i; // Número de proceso (n)

            // Crear un hilo para simular el subproceso de la mesa electoral
            Thread hiloMesa = new Thread(new SimuladorMesaElectoral(r, n));
            hiloMesa.start(); // Iniciar el hilo

            // Esperar 20 segundos antes de lanzar el siguiente subproceso
            Thread.sleep(intervalo);
        }
    }

    // Clase interna que representa una mesa electoral (subproceso)
    static class SimuladorMesaElectoral implements Runnable {
        private int r;
        private int n;

        public SimuladorMesaElectoral(int r, int n) {
            this.r = r;
            this.n = n;
        }

        @Override
        public void run() {
            try {
                // Cálculo de votos
                int votosPP = (r + n) % 9;
                int votosPSOE = Math.abs((r - n) % 9);

                // Calculo el tiempo de espera (random entre 1 y r % 5 + 1)
                Random random = new Random();
                int espera = random.nextInt(r % 5 + 1) + 1;

                // Simulo la espera
                Thread.sleep(espera * 1000);

                // Sincronizar para evitar problemas con los hilos concurrentes
                synchronized (MesaElectoral2.class) {
                    // Sumar los resultados a los totales
                    totalVotosPP += votosPP;
                    totalVotosPSOE += votosPSOE;

                    // Mostrar los resultados acumulados
                    System.out.println("Mesa " + n + " - Votos PP: " + votosPP + ", Votos PSOE: " + votosPSOE);
                    System.out.println("Suma actual de votos PP: " + totalVotosPP);
                    System.out.println("Suma actual de votos PSOE: " + totalVotosPSOE);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	}


