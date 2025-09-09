package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStream1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis= new FileInputStream("datos.txt");
			int val ;
			byte[] arr=new byte[4096];
			int cuantos =fis.read(arr);
			System.out.println(cuantos);
			
			
			do{
				val= fis.read();
				if(val!= -1) {
					System.out.println(val);
				}else {
					System.out.println("fin");
				}
				
			}while(val != -1);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
