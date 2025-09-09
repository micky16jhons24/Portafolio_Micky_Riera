package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ConsultarCliente {
    private TiendaDAO dao;

    public ClienteUsuario consultaPorDNI(int dni) {
        return dao.consultaClienteDNI(dni);
    }
}
