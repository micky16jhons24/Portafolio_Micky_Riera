package Practicar;

public class ProcessBuilderPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//ProcessBuilder te permite configurar el proceso y sus propiedades antes de ejecutarlo 
		
		
		try {
			
			ProcessBuilder pb=new ProcessBuilder("ping", "www.google.com");
			
			Process p=pb.start();
			
			

			//ahi que crear un estado de saliday le pasamos el waitfor
			int estadoSalida = p.waitFor(); //espera a que el proceso termine 
			System.out.println("El proceso termino con estado de salida: " + estadoSalida);
			
			
			
			
			
		}catch(Exception e) {
			System.out.println("error");
		}
		
		
		//getInputStream()= Permite leer la salida del proceso(lo que normalmente verias en la terminal)
		//getErrorStream()= Permite leeer los errores del proceso
		//getOutputStream()= Permmite escribir en la entrada del proceso 
		//waitFor()=espera a que el proceso termine
		
		
	}

}
