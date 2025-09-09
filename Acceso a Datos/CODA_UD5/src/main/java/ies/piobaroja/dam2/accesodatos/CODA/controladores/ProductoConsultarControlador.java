package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.sql.SQLException;
import java.util.List;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo; 
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto; 

public class ProductoConsultarControlador {
    private APIModelo api;
    private TiendaDAO tiendaDAO;

    // Constructor
    public ProductoConsultarControlador() throws SQLException {
        api = APIModelo.getInstance(); // Inicializamos el APIModelo
        tiendaDAO = new TiendaDAO(); // Inicializamos TiendaDAO
    }

    // Método para consultar un producto por su ID
    public Producto consultaPorID(int id) {
        return api.consultaProducto(id); // Asegúrate de que este método llame a TiendaDAO
    }

    // Método para consultar productos por disponibilidad
    public List<Producto> consultarPorDisponibilidad(boolean disponibilidad) {
        return tiendaDAO.consultarProductosPorDisponibilidad(disponibilidad);
    }
}
