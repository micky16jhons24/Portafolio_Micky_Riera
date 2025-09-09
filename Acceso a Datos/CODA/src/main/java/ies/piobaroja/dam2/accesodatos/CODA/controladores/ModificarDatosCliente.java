package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ModificarDatosCliente {
    private APIModelo api;

    public ModificarDatosCliente() {
        api = APIModelo.getInstance();
    }

    public ClienteUsuario consultaPorDNI(String dni) {
        // Usa el método de consulta que ya tienes
        return new ConsultarCliente().consultaPorDNI(dni);
    }

    public boolean modificarCliente(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        for (ClienteUsuario cliente : api.obtenerClientes()) {
            if (cliente.getDni().equals(dni)) {
                // Modifica los atributos del cliente
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                cliente.setCorreo(correo);
                cliente.setCodigoPostal(codigoPostal);
                return true; // Modificación exitosa
            }
        }
        return false; // Cliente no encontrado
    }
}
