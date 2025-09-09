package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

public class VentaProductoControlador {

    private TiendaDAO dao;


    public boolean realizarVentaProducto(int productoId, int clienteDni, Date fechaVenta, int cantidad) {
        try {
            return dao.realizarVenta(cantidad, fechaVenta, productoId, clienteDni);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ClienteUsuario> obtenerClientes() {
        return dao.obtenerTodosLosClientes();
    }
}
