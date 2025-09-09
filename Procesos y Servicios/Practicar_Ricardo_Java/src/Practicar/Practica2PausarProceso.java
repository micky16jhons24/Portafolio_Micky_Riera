package Practicar;

import java.io.IOException;

public class Practica2PausarProceso {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int cont=0;
		ProcessBuilder pb= new ProcessBuilder("cmd.exe", "/c","ping /t 5 /nobreak");
		try {
			Process p=pb.start();
			while(cont<5) {
				cont++;
				System.out.println(cont + "segundos");
				Thread.sleep(1000);
	
				
			}
			p.waitFor();
			if(cont == 5 ) {
				System.out.println("Programa finalizado");
				p.destroy();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
