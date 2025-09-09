package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.util.List;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo; 
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto; 

public class ProductoConsultarControlador {
    private APIModelo api;
    private TiendaDAO tiendaDAO;

    public ProductoConsultarControlador() {
        api = APIModelo.getInstance(); 
        tiendaDAO = new TiendaDAO(); 
    }

    public Producto consultaPorID(int id) {
        return api.consultaProducto(id); 
    }

    public List<Producto> consultarPorDisponibilidad(boolean disponibilidad) {
        return tiendaDAO.consultarProductosPorDisponibilidad(disponibilidad);
    }
}
