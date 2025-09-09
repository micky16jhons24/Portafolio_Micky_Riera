package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.AlquilerModificacionProducto;

public class AlquilerModificacionProductoControlador {
    private TiendaDAO dao;
    


    public AlquilerProducto consultarDatosProductoalquilado(int id) {
        return  dao.consultaProductoAlquiler(id);
    }

    public boolean modificarProductoAlquiler(int id, String nombre, String talla, Double precioVenta,
                                           boolean disponibilidad, LocalDate fechaAlquiler, int telefono, int duracionDias) {
    	AlquilerProducto ap=dao.consultaProductoAlquiler(id);
    	
    	
    	if(ap != null) {
    		ap.setNombre(nombre);
    		ap.setTalla(talla);
    		ap.setPrecioVenta(precioVenta);
    		ap.setDisponibilidad(disponibilidad);
    		ap.setFechaAlquiler(fechaAlquiler);
    		ap.setTelefono(telefono);
    		ap.setDuracionDias(duracionDias);
    		return dao.modificarProductoAlquiler(ap);
    	}
    	return false;    	
    }

}

