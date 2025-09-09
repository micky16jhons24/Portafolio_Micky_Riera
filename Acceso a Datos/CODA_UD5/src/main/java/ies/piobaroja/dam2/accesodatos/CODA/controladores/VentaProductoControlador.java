package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.VentaProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Tienda;
import java.sql.Date;

public class VentaProductoControlador {
    private Tienda tienda;

    public VentaProductoControlador(Tienda tienda) {
        this.tienda = tienda;
    }

    public void realizarVentaProducto(String nombre, String talla, Double precioVenta, int cantidadVendida, ClienteUsuario cliente) {
        if (cantidadVendida <= 0) {
            throw new IllegalArgumentException("La cantidad vendida debe ser mayor que cero.");
        }
        VentaProducto ventaProducto = new VentaProducto();
        tienda.altaProducto(ventaProducto);
        System.out.println("Venta registrada: " + ventaProducto);
    }
    public VentaProducto consultarVenta(int id) {
        return (VentaProducto) tienda.consultarProducto(id);
    }

    public boolean modificarVenta(int id, String nombre, String talla, Double precioVenta, Date fechaVenta, int cantidadVendida) {
        VentaProducto ventaProducto = (VentaProducto) tienda.consultarProducto(id);
        if (ventaProducto != null) {
            ventaProducto.setNombre(nombre);
            ventaProducto.setTalla(talla);
            ventaProducto.setPrecioVenta(precioVenta);
            ventaProducto.setFechaVenta(fechaVenta);
            ventaProducto.setCantidadVendida(cantidadVendida);
            tienda.modificarProducto(ventaProducto);
            return true;
        }
        return false;
    }
}

