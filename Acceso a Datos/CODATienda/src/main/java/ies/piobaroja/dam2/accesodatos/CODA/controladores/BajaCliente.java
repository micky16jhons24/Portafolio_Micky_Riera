package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class BajaCliente {

    APIModelo api = APIModelo.getInstance();

    public boolean baja(int dni) {
        
        ClienteUsuario cliente = api.consultaClientePorDNI(dni); 
        if (cliente != null) {
            api.bajaCliente(dni); 
            return true;
        }
        return false; 
    }
}