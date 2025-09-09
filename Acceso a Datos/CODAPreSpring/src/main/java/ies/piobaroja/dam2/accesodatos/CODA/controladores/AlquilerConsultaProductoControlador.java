package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;

public class AlquilerConsultaProductoControlador {
    private TiendaDAO dao;

   

    public AlquilerProducto consultarProducto(int id) {
        return dao.consultaProductoAlquiler(id);
    }
}
