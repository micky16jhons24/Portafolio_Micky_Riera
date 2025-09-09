package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;

public class AlquilerConsultaProductoControlador {
    private APIModelo api;

    public AlquilerConsultaProductoControlador() {
        api = APIModelo.getInstance();
    }

    public AlquilerProducto consultarProducto(int id) {
        return api.consultarProductoAlquiler(id);
    }
}
