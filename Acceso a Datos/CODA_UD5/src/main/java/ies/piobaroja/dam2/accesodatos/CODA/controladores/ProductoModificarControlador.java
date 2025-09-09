package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;

public class ProductoModificarControlador {
    private APIModelo api = APIModelo.getInstance(); // Asegúrate de que el singleton se inicialice correctamente
    
    
    public Producto consultarDatosProducto(int id) {
        return  api.consultaProducto(id);
    }
    
    public boolean modificarProducto(int id, String nombre, String talla, Double precioVenta, boolean disponibilidad) {
       
    	boolean resultado=api.modificacionProducto(id, nombre, talla, precioVenta, disponibilidad);
    	
        return resultado;
    }
}
