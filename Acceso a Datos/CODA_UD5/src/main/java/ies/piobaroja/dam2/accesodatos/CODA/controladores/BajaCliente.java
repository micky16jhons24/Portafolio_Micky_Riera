package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class BajaCliente {

    APIModelo api = APIModelo.getInstance();

    public boolean baja(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI no puede ser nulo o vacío.");
        }
        
        ClienteUsuario cliente = api.consultaClientePorDNI(dni); // Usa el método correcto para consultar
        if (cliente != null) {
            api.bajaCliente(dni); // Asumiendo que este método borra el cliente
            return true;
        }
        return false; // Si el cliente no se encontró
    }
}