package Examen_Ricardo_Micky_Riera;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;

public class MonitorizacionyComunicacion {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir /b /O:D C:\\Users\\Usuario");
		
		ProcessBuilder pb2 = new ProcessBuilder("cmd", "/c", "sort");
		
		Process pr = pb.start();
		
		Process pr2 = pb2.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pr2.getOutputStream()));
		
		String line;
		while ((line = br.readLine()) != null) {
	            bw.write(line);
	            bw.newLine(); 
	        }
		
	        bw.close();
	        
	        pr.waitFor();
	        
	        BufferedReader br2 = new BufferedReader(new InputStreamReader(pr2.getInputStream()));
	        String line2;
	        
	        while ((line2 = br2.readLine()) != null) {
	            System.out.println(line2);
	        }
	        pr2.waitFor();
	    }

}
