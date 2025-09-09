package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class AltaCliente {
    APIModelo api = APIModelo.getInstance();

    public void altaCliente( String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        if ( nombre == null || apellido == null || telefono == null || correo == null || codigoPostal == null) {
            throw new IllegalArgumentException("Todos los campos deben ser proporcionados.");
        }
        System.out.println("Alta Cliente");
        api.altaCliente( nombre, apellido, telefono, correo, codigoPostal);
    }
}
