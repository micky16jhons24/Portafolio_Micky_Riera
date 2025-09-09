package ies.piobaroja.dam2.accesodatos.CODA;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.AltaCliente;
import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.AltaClienteVista;

public class Principal {

	public static void main(String[] args) {
		
		contexto();
//        SwingUtilities.invokeLater(() -> {
//            VentanaCODA ventana = new VentanaCODA();
//            ventana.setSize(600, 400);
//            ventana.setLocationRelativeTo(null); // Centrar la ventana
//            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
//            ventana.setVisible(true); // Mostrar la ventana
//        });
    }
    
    
    public static void contexto() {
    	AnnotationConfigApplicationContext appContext= new AnnotationConfigApplicationContext(TiendaConfig) ;
    	VentanaCODA ventana= appContext.getBean(VentanaCODA.class);
    	AltaClienteVista altaclientevista=appContext.getBean(AltaClienteVista.class);
    	AltaCliente altacliente= appContext.getBean(AltaCliente.class);
    	TiendaDAO dao=appContext.getBean(TiendaDAO.class);
    	appContext.close();
    }
}
