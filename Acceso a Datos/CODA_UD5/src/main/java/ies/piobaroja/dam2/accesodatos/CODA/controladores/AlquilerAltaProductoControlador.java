package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.sql.Date;
import java.time.LocalDate;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class AlquilerAltaProductoControlador {
    APIModelo api = APIModelo.getInstance();

    public void AltaProductoAlquilado(String nombre, String talla, Double precioVenta, boolean disponibilidad,
            LocalDate fechaAlquiler, int telefono, int duracionDias) {
        System.out.println("Alta producto Alquilado");
        api.altaProductoAlquiler(nombre, talla, precioVenta, disponibilidad, fechaAlquiler, telefono, duracionDias);
    }
}
