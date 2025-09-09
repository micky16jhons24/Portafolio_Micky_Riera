package Ejercicio_After;

public class ClaseAnonima2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioServicio usuarioServicio=new UsuarioServicio() {
			@Override
			public void crearUsuario() {
				// TODO Auto-generated method stub
				System.out.println("Creando usuario");	
			}
			
		};
		
		usuarioServicio.crearUsuario();
	}

}


interface UsuarioServicio{
	void crearUsuario();
	
}