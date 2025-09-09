package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class BajaCliente {

    APIModelo api = APIModelo.getInstance();

    public boolean baja(String dni) {
        return api.bajaCliente(dni);
    }
}