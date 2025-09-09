package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class ProductoEliminarControlador {
    private TiendaDAO dao;


   
    public boolean eliminarProducto(int id) {
        return dao.bajaProducto(id);
    }
}