package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class ProductoAltaControlador {
    APIModelo api = APIModelo.getInstance();

    public void altaProducto(String nombre, String talla, Double precioVenta, boolean disponibilidad) {
        if (nombre == null || talla == null || precioVenta == null) {
            throw new IllegalArgumentException("Nombre, talla y precio de venta deben ser proporcionados.");
        }
        System.out.println("Alta Producto");
        api.altaProducto(nombre, talla, precioVenta, disponibilidad);
    }
}
