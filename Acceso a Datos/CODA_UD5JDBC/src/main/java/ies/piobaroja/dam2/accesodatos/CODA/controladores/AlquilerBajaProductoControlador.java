package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.AlquilerBajaProducto;

public class AlquilerBajaProductoControlador {
	APIModelo api = APIModelo.getInstance();

  
	public boolean bajaProductoAlquiler(int id) {
		// TODO Auto-generated method stub
		 return api.bajaProductoAlquiler(id);
	}
}
