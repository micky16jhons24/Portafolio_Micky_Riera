package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JaviMarica {

	public static void main(String[] args) {
		
		ProcessBuilder pb = new ProcessBuilder("sort");
		pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
		
		System.out.println("Control z para terminar. ");
		
		try {
			Process p = pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			
			String linea;
			while ((linea = br.readLine())!= null) {
				bw.write(linea);
				bw.newLine();
				
			}
			
			bw.close();
			BufferedReader br2= new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String ordenado;
			while ((ordenado = br2.readLine()) != null) {
				System.out.println(ordenado);
			}
			p.waitFor();

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
