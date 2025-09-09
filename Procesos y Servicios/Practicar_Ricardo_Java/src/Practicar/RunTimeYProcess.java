package Practicar;

public class RunTimeYProcess {
	public static void main(String[] args) {
		
		try {
			/*es la clase del sistema que te permite acceder 
				al entorno de ejecuion de la apliacion java 
				incluyendo la capacidad de ejecutar comandos del S.O */
			
			//aqui vamos a ejecutar el runtime para ejecutar un ping
			//creamos instancia para poder ejecutar el comando
			Runtime rutime= Runtime.getRuntime();
			
			
			//esta funcion es para ejecutar  el comadno entero 
			Process p= rutime.exec("ping www.google.com");
			
			//ahi que crear un estado de saliday le pasamos el waitfor
			int estadoSalida = p.waitFor(); //espera a que el proceso termine 
			System.out.println("El proceso termino con estado de salida: " + estadoSalida);
			
			
			
			
		}catch(Exception e) {
			System.out.println("error");
		}
		
		
	}
}
