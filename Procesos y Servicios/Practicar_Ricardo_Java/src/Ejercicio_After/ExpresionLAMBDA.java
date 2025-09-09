package Ejercicio_After;

public class ExpresionLAMBDA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ejecutor claseAnonimaejecutor =new Ejecutor (){

			@Override
			public String ejecutador(String parametro) {
				// TODO Auto-generated method stub
				System.out.println("hola desde una clase anonima" + parametro);
				return parametro.toUpperCase();
			}
			
		};
		
		Ejecutor lambdaEjecutor = (String parametro)->{
			System.out.println("hola desde nuestra lambda " + parametro);
			
			return parametro.toLowerCase();
		};
		
		String resultadoAnonima=claseAnonimaejecutor.ejecutador(" clase anonima");
		String resultadoLambda=lambdaEjecutor.ejecutador("lambda"); 
		
		System.out.println(resultadoAnonima);
		System.out.println(resultadoLambda);

	}

}

interface Ejecutor{
	String ejecutador(String parametro);
	
	default void metodoDefecto() {
		
	}
	
	static void metodoEstatico() {
		
	}
}
