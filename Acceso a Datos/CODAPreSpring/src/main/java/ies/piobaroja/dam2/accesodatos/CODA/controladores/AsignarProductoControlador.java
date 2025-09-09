package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;

public class AsignarProductoControlador {
    private TiendaDAO dao;

    public void asignarProducto(int dniCliente, int idProducto) {
    	
    	ClienteUsuario cliente=dao.consultaClienteDNI(dniCliente);
    	
    	Producto producto=dao.consultaProducto(idProducto);
    	
    	dao.asignarProductoACliente(cliente, producto);
    	
    	
    }
}
