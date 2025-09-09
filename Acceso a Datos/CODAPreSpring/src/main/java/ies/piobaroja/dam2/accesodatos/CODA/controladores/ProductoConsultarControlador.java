package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.util.List;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto; 

public class ProductoConsultarControlador {
    private TiendaDAO dao;

   

    public Producto consultaPorID(int id) {
        return dao.consultaProducto(id); 
    }

    public List<Producto> consultarPorDisponibilidad(boolean disponibilidad) {
        return dao.consultarProductosPorDisponibilidad(disponibilidad);
    }
}
