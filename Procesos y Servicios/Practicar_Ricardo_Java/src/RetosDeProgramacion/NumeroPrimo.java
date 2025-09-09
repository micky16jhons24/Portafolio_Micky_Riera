package RetosDeProgramacion;

public class NumeroPrimo {

    public static void main(String[] args) {
        // Imprimir si los números del 1 al 100 son primos
        for (int i = 1; i <= 100; i++) {
            if (esPrimo(i)) {
                System.out.println(i + " es un número primo");
            } else {
                System.out.println(i + " no es un número primo");
            }
        }
    }

    private static boolean esPrimo(int num) {
        // Si el número es menor o igual a 1, no es primo
        if (num <= 1) {
            return false;
        }

        // Comprobamos si el número es divisible por algún número entre 2 y la raíz cuadrada de 'num'
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;  // Si 'num' es divisible por 'i', no es primo
            }
        }

        // Si no es divisible por ningún número, es primo
        return true;
    }
}
