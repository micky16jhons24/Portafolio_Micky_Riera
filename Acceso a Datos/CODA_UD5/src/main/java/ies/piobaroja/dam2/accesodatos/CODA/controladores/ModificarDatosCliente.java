package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ModificarDatosCliente {
    private APIModelo modelo;

    public ModificarDatosCliente() {
        modelo = APIModelo.getInstance();
    }

   

    public ClienteUsuario consulta(String dni) {
		return modelo.consultaClientePorDNI(dni);
	}
	

    public boolean modificar(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
    	boolean resultado=modelo.modificarCliente(dni, nombre, apellido, telefono, correo, codigoPostal);
    	return resultado;
	}
}
