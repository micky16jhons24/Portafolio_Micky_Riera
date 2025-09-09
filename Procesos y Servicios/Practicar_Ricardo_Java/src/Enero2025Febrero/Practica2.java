package Enero2025Febrero;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;

public class Practica2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
			 //lo que hace esta linea es tomar como ip un string que es una cadena y lo convierte en un objeto de la clase InetAddres
			 InetAddress direccion = InetAddress.getByName("172.20.10.6"); 

	            // Aqui tomamos el objeto direccion de InetAddress y con esto encontramos la interfaz de red asociada a esa direccion IP
	            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(direccion);
	            // aqui sacamos el nombre de la interfaz
	            System.out.println("Nombre de la Interfaz: " + networkInterface.getName());
	            // Aqui saco si sporta multicast 	            
	            System.out.println("Soportar  Multicast: " + networkInterface.supportsMulticast());
	            //aqui saco cuanto es el tamaño maximo de los paquetes
	            System.out.println("Tamaño maximo del paquete : " + networkInterface.getMTU());
	            //Saco todas las direcciones IP alcanzables
	            System.out.println("direcciones IP alcanzables: ");
	            for(InterfaceAddress ifaceAddress : networkInterface.getInterfaceAddresses()) {
	            	System.out.println("- " + ifaceAddress.getAddress().getHostAddress());
	            }
	            } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}