package RetosDeProgramacion;

public class FIZBUZZ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<=100;i++) {
			System.out.println(i);
			if(i% 5 ==0 && i %3 ==0){
				System.out.println("este numero "+ i +" es multiplo de 3 y 5" +"-------FIZZBUZZ");

			}else if(i %3==0) {
				System.out.println("este numero "+ i +" es multiplo de 3"+"-------FIZZ");

			}else if (i %5==0) {
				System.out.println("este numero "+ i +" es multiplo de 5" +"-------BUZZ");
			}else {
				System.out.println(i);
			}
			
		}
		
		
	}

}
