package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;

public class AltaCliente {
    APIModelo api = APIModelo.getInstance();

    // Cambia la firma del método para que acepte más parámetros
    public void altaCliente(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        System.out.println("Alta Cliente");
        api.altaCliente(dni, nombre, apellido, telefono, correo, codigoPostal);
    }
}
