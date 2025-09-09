package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;

public class ProductoModificarControlador {
    private TiendaDAO dao;
    
    
    public Producto consultarDatosProducto(int id) {
        return  dao.consultaProducto(id);
    }
    
    public boolean modificarProducto(int id, String nombre, String talla, Double precioVenta, boolean disponibilidad) {
       
    	Producto p = dao.consultaProducto(id);
    	
    	if(p != null) {
    		p.setNombre(nombre);
    		p.setTalla(talla);
    		p.setPrecioVenta(precioVenta);
    		p.setDisponibilidad(disponibilidad);
    		return dao.modificarProducto(p);
    	}
    	return false;
    	
    }
}
