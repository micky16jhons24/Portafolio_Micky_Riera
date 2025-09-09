package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class QueryContarClientesControlador {
    private TiendaDAO tiendaDAO;

    public QueryContarClientesControlador() {
        tiendaDAO = new TiendaDAO();
    }

    public long contarClientes() {
        return tiendaDAO.contarClientesQuery();
    }
}
