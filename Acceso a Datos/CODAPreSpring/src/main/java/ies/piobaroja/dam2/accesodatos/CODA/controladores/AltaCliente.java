package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import org.springframework.beans.factory.annotation.Autowired;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class AltaCliente {
    @Autowired
    private TiendaDAO dao;

    public void altaCliente( String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        if ( nombre == null || apellido == null || telefono == null || correo == null || codigoPostal == null) {
            throw new IllegalArgumentException("Todos los campos deben ser proporcionados.");
        }
        System.out.println("Alta Cliente");        
        ClienteUsuario cliente=new ClienteUsuario(nombre, apellido, telefono, correo, codigoPostal);
        dao.altaCliente(cliente);
    }
}
