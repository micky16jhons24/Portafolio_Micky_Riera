package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class AsignarProductoControlador {

    private APIModelo api;

    public AsignarProductoControlador() {
        this.api = APIModelo.getInstance();
    }

    public boolean asignarProducto(int idProducto, String dniCliente) {
        if (dniCliente == null || dniCliente.trim().isEmpty() || idProducto <= 0) {
            throw new IllegalArgumentException("Datos inválidos.");
        }

        return api.asignarProductoACliente(idProducto, dniCliente);
    }
}
