package Enero2025Febrero;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BarcoCliente {

    public static void main(String[] args) {
        String host = "172.16.105.199"; // IP del servidor
        int puerto = 3456;

        char[][] tableroMIO = new char[5][5];
        char[][] tableroSUYO = new char[5][5];

        inicializarTablero(tableroMIO);
        inicializarTablero(tableroSUYO);

        try (Socket socket = new Socket(host, puerto);
             PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado al servidor.");
            System.out.println("Tu tablero:");
            imprimirTablero(tableroMIO);

            // Colocar barcos manualmente
            colocarBarcosManualmente(sc, tableroMIO);
            System.out.println("Tus barcos han sido colocados.");

            String mensaje = "";

            while (!mensaje.equals("salir")) {
                // Cliente ataca al servidor
            	System.out.println("Introduce las coordenadas para atacar (formato: fila.col): ");
            	mensaje = sc.nextLine().replace(".", ","); // Convertir de "1.2" a "1,2"


                if (mensaje.equals("salir")) {
                    escritor.println("salir");
                    System.out.println("Te desconectaste del servidor.");
                    break;
                }

                // Enviar el ataque al servidor
                escritor.println(mensaje.replace(".", ",")); // Asegurar el formato correcto

                // Leer respuesta del servidor
                String respuesta = lector.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);

                // Procesar la respuesta y actualizar el tablero del oponente
                RespuestaServidor(respuesta, tableroSUYO);

                // *El servidor ahora ataca al cliente*
                String ataqueServidor = lector.readLine();
                if (ataqueServidor.startsWith("Ataque:")) {
                    String[] coords = ataqueServidor.split(":")[1].split(",");
                    int fila = Integer.parseInt(coords[0]);
                    int columna = Integer.parseInt(coords[1]);

                    // Si hay un barco en la posición atacada, marcar impacto
                    if (tableroMIO[fila][columna] == 'B') {
                        tableroMIO[fila][columna] = 'X';
                        System.out.println("¡El servidor ha impactado tu barco en " + fila + "," + columna + "!");
                    } else {
                        tableroMIO[fila][columna] = 'O';
                        System.out.println("¡El servidor falló su ataque en " + fila + "," + columna + "!");
                    }
                }

                // Mostrar tableros después de cada turno
                System.out.println("MI tablero:");
                imprimirTablero(tableroMIO);
                System.out.println("Tablero del oponente:");
                imprimirTablero(tableroSUYO);
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }

    public static void inicializarTablero(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '~';
            }
        }
    }

    public static void imprimirTablero(char[][] tablero) {
        for (char[] fila : tablero) {
            for (char celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    public static void RespuestaServidor(String respuesta, char[][] tablero) {
        try {
            System.out.println("Depuración - Respuesta del servidor: " + respuesta); // Agregar impresión para depuración

            // Asegurar que la respuesta está en el formato correcto
            if (!respuesta.matches("\\d+,\\d+,(Impacto|Agua|Hundido)")) {
                System.err.println("Error: Respuesta del servidor con formato incorrecto -> " + respuesta);
                return;
            }

            String[] partes = respuesta.split(",");
            int fila = Integer.parseInt(partes[0]);
            int col = Integer.parseInt(partes[1]);
            String resultado = partes[2];

            if (resultado.equalsIgnoreCase("Impacto") || resultado.equalsIgnoreCase("Hundido")) {
                tablero[fila][col] = 'X'; // Marcar impacto en el tablero del oponente
            } else if (resultado.equalsIgnoreCase("Agua")) {
                tablero[fila][col] = 'O'; // Marcar agua (fallo)
            } else {
                System.err.println("Resultado desconocido: " + resultado);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar la respuesta del servidor: " + e.getMessage());
        }
    }


    public static void colocarBarcosManualmente(Scanner sc, char[][] tableroMIO) {
        System.out.println("Escribe las coordenadas para colocar tus barcos.");
        System.out.println("Formato de coordenadas: fila.col (ejemplo: 1.1)");

        // Colocar los barcos de tamaño 2
        for (int i = 0; i < 2; i++) {
            System.out.println("Introduce las coordenadas para el barco de tamaño 2:");
            String[] coordenadasBarco = sc.nextLine().split(" ");
            while (coordenadasBarco.length != 2 || !coordenadasValidas(tableroMIO, coordenadasBarco)) {
                System.out.println("Error: Debes introducir 2 coordenadas válidas para el barco de tamaño 2:");
                coordenadasBarco = sc.nextLine().split(" ");
            }
            colocarBarco(tableroMIO, coordenadasBarco);
            imprimirTablero(tableroMIO);
        }

        // Colocar los barcos de tamaño 1
        for (int i = 0; i < 2; i++) {
            System.out.println("Introduce la coordenada para el barco de tamaño 1:");
            String[] coordenadasBarco = { sc.nextLine() };
            while (!coordenadasValidas(tableroMIO, coordenadasBarco)) {
                System.out.println("Error: Debes introducir una coordenada válida para el barco de tamaño 1:");
                coordenadasBarco[0] = sc.nextLine();
            }
            colocarBarco(tableroMIO, coordenadasBarco);
            imprimirTablero(tableroMIO);
        }
    }

    public static void colocarBarco(char[][] tablero, String[] coordenadas) {
        for (String coordenada : coordenadas) {
            try {
                String[] partes = coordenada.split("\\.");
                int fila = Integer.parseInt(partes[0]) - 1;
                int col = Integer.parseInt(partes[1]) - 1;

                if (fila < 0 || fila >= tablero.length || col < 0 || col >= tablero[fila].length) {
                    System.out.println("Coordenada inválida: " + coordenada);
                    continue;
                }

                if (tablero[fila][col] == '~') {
                    tablero[fila][col] = 'B';
                } else {
                    System.out.println("Ya hay un barco en la posición: " + coordenada);
                }
            } catch (Exception e) {
                System.err.println("Error al procesar la coordenada: " + coordenada);
            }
        }
    }

    public static boolean coordenadasValidas(char[][] tablero, String[] coordenadas) {
        for (String coordenada : coordenadas) {
            try {
                if (!coordenada.contains(".")) {
                    System.out.println("Error: Usa el formato correcto (fila.col)");
                    return false;
                }

                String[] partes = coordenada.split("\\.");
                if (partes.length != 2) {
                    System.out.println("Error: Formato incorrecto, usa fila.col");
                    return false;
                }

                int fila = Integer.parseInt(partes[0]) - 1;
                int col = Integer.parseInt(partes[1]) - 1;

                if (fila < 0 || fila >= tablero.length || col < 0 || col >= tablero[fila].length) {
                    System.out.println("Error: Coordenada fuera de los límites");
                    return false;
                }

                if (tablero[fila][col] != '~') {
                    System.out.println("Error: Ya hay un barco en esta posición");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa solo números en el formato fila.col");
                return false;
            }
        }
        return true;
    }

}