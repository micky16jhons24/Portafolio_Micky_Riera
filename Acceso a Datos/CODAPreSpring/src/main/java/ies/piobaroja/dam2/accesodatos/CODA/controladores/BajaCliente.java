package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class BajaCliente {

    private TiendaDAO dao;

    public boolean baja(int dni) {
        
    	ClienteUsuario usuario= dao.consultaClienteDNI(dni);
    	
        if (usuario != null) {
            dao.bajaCliente(dni); 
            return true;
        }
        return false; 
    }
}