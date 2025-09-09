package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class AsignarProductoControlador {
    private APIModelo api = APIModelo.getInstance();

    public void asignarProducto(int dniCliente, int idProducto) {
        api.asignarProductoACliente(dniCliente, idProducto);
    }
}
