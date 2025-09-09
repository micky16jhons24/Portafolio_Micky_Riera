package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

import java.sql.Date;
import java.util.List;


public class VentaProductoControlador {

    private TiendaDAO tiendaDAO;

    public VentaProductoControlador() {
        tiendaDAO = new TiendaDAO();
    }

    public boolean realizarVentaProducto(int productoId, int clienteDni, Date fechaVenta, int cantidad) {
        try {
            return tiendaDAO.realizarVenta(cantidad, fechaVenta, productoId, clienteDni);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ClienteUsuario> obtenerClientes() {
        return tiendaDAO.obtenerTodosLosClientes();
    }
}
