package Enero2025Febrero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EjemploURLConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Crear una URL
			@SuppressWarnings("deprecation")
			URL url = new URL("http://www.example.com");
			// Obtener la conexión a la URL
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000); // Tiempo de espera para conectar
			connection.setReadTimeout(5000); // Tiempo de espera para leer
			// Leer el contenido de la URL usando try-with-resources
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}
		} catch (MalformedURLException e) {
			System.err.println("URL inválida: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error de entrada/salida: " + e.getMessage());
		}
	}

}
