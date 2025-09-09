package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.AlquilerModificacionProducto;

public class AlquilerModificacionProductoControlador {
     APIModelo api = APIModelo.getInstance(); // Asegúrate de que este parámetro se asigne a la variable de instancia
    

    // Métodos para manejar la lógica del alquiler

    public AlquilerProducto consultarDatosProductoalquilado(int id) {
        return  api.consultarProductoAlquiler(id);
    }

    public boolean modificarProductoAlquiler(int id, String nombre, String talla, Double precioVenta,
                                           boolean disponibilidad, LocalDate fechaAlquiler, int telefono, int duracionDias) {
    	boolean resultado=api.modificarProductoAlquiler(id, nombre, talla, precioVenta, disponibilidad, fechaAlquiler, telefono, duracionDias);
    	return resultado;
    	
    }

}

