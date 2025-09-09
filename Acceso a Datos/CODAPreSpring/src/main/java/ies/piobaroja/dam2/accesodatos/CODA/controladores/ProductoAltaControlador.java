package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;

public class ProductoAltaControlador {
    private TiendaDAO dao;

    public void altaProducto(String nombre, String talla, Double precioVenta, boolean disponibilidad) {
        if (nombre == null || talla == null || precioVenta == null) {
            throw new IllegalArgumentException("Nombre, talla y precio de venta deben ser proporcionados.");
        }
        System.out.println("Alta Producto");
        Producto p=new Producto(nombre, talla, precioVenta, disponibilidad);
        dao.altaProducto(p);
    }
}
