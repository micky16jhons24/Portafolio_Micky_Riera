package ies.piobaroja.dam2.accesodatos.CODA;

import javax.swing.JFrame;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.AltaCliente;
import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.AltaClienteVista;

@Configuration
public class TiendaConfig {

	
	@Bean
	public VentanaCODA ventana() {
		VentanaCODA ventana = new VentanaCODA();
        ventana.setSize(600, 400);
        ventana.setLocationRelativeTo(null); // Centrar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        ventana.setVisible(true); // Mostrar la ventana	}
		return ventana;
}
	@Bean
	public AltaClienteVista altacliente() {
		return new  AltaClienteVista();
	}
	
	@Bean
	public AltaCliente altaCliente() {
		return new  AltaCliente();
	}
	
	@Bean
	public TiendaDAO dao() {
		return new TiendaDAO();
	}
}
