package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class ProductoEliminarControlador {
    APIModelo api = APIModelo.getInstance();


   
    public boolean eliminarProducto(int id) {
        return api.bajaProducto(id);
    }
}