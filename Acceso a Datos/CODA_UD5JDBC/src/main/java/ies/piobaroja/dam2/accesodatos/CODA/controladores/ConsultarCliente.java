package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ConsultarCliente {
    APIModelo api = APIModelo.getInstance();

    public ClienteUsuario consultaPorDNI(int dni) {
       
        return api.consultaClientePorDNI(dni);
    }
}
