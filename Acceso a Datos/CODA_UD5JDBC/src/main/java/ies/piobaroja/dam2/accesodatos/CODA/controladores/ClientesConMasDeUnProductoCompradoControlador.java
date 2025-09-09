package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class ClientesConMasDeUnProductoCompradoControlador {
    private TiendaDAO tiendaDAO;

    public ClientesConMasDeUnProductoCompradoControlador() {
        tiendaDAO = new TiendaDAO();
    }

    public long contarClientesConMasDeUnProductoComprado() {
        return tiendaDAO.ClienteConMasDeUnaCompra();
    }
}
