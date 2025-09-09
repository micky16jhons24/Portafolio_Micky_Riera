package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

import ies.piobaroja.dam2.accesodatos.CODA.vistas.AlquilerBajaProducto;

public class AlquilerBajaProductoControlador {
    private TiendaDAO dao;

  
	public boolean bajaProductoAlquiler(int id) {
		// TODO Auto-generated method stub
		 return dao.bajaProductoAlquiler(id);
	}
}
