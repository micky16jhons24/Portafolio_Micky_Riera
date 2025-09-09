package Enero2025Febrero;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Practica1 {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		 // Enumero todas las interfaces de red disponibles en el sistema
	    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

	    System.out.println("Interfaces de red disponibles:");
	    while (interfaces.hasMoreElements()) {
	        NetworkInterface ni = interfaces.nextElement();
	        System.out.println("- " + ni.getName() + " (" + ni.getDisplayName() + ")");
	    }
		
		//esto es para obtener la MAC
		NetworkInterface ni= NetworkInterface.getByName("wireless_32768"); // en windows esEthernet, Wi-Fi o tambien eth0  o wlan0 en linux
		// en mi caso uso wireless_32768  por que estoy conectado desde el movil al ordenador 
		byte[] mac = ni.getHardwareAddress();
		
		if(mac!=null) {
			StringBuilder macdireccion= new StringBuilder();
			
			for(int i=0; i<mac.length;i++) {
				macdireccion.append(String.format("%02X%s", mac[i] , (i< mac.length -1)? ":" : ""));
				//%02x esto esp3cifica como formatear el primer valor mac[i]
				//%X esto indica que el valor debe ser representado en formato eaxdecimal(letas A-F y nuemros 0-9)
				//02 esto asegura que el valor heaxedicimal tenga al menos dos digitos, agregando ceros si es necesario, 

			}
			
			System.err.println("Direcion mac : " + macdireccion.toString());

		}else {
			System.out.println(" no se encontro la mac");
		}
		
		
		
		//esot es para saber si esta UP, significa si esta lista para recibir datos o no
		boolean estaUP= ni.isUp();
		System.out.println(estaUP ? "UP" : "DOWN");
		
		//esto es para saber i esta en loopback
		System.out.println(ni.isLoopback() ? "si " : "no");
		
		
        //Con esto  Obtengo las direcciones IP asociadas a la interfaz (IPv4 y IPv6)
		 System.out.println("Direcciones IP asociadas:");
         Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
         while (inetAddresses.hasMoreElements()) {
             InetAddress inetAddress = inetAddresses.nextElement();
             System.out.println(" - " + inetAddress.getHostAddress());
         }
         
         //¿Qué pasa si desconectas la wifi y quitas el cable de red?
         //no pasa nada sigue buscando interfaces incluso da una direccion IP
         
         //¿Qué interfaz queda?
         //queda la intefaz eth0-32778 , ppp_32778, loopback_0,wireless_0-32770 y mas... 
         
	}
	
	
	

}
