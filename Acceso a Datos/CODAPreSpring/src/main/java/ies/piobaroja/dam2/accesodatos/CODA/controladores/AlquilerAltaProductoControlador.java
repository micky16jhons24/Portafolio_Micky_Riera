package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.sql.Date;
import java.time.LocalDate;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;

public class AlquilerAltaProductoControlador {
    private TiendaDAO dao;

    public void AltaProductoAlquilado(String nombre, String talla, Double precioVenta, boolean disponibilidad,
            LocalDate fechaAlquiler, int telefono, int duracionDias) {
        System.out.println("Alta producto Alquilado");
        AlquilerProducto alta = new AlquilerProducto(nombre,talla,precioVenta,disponibilidad,fechaAlquiler, telefono,duracionDias);
        dao.altaProductoAlquiler(alta);
    }
}
