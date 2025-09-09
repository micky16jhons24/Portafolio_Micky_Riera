package ies.piobaroja.dam2.accesodatos.CODA.controladores;


import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ModificarDatosCliente {

    
    private TiendaDAO dao;

    // Método para consultar un cliente por DNI
    public ClienteUsuario consulta(int dni) {
        return dao.consultaClienteDNI(dni); // Llamamos al método del DAO
    }

    // Método para modificar datos de un cliente
    public boolean modificar(int dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        ClienteUsuario cliente = dao.consultaClienteDNI(dni);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            cliente.setCodigoPostal(codigoPostal);
            return dao.modificacion(cliente); // Llamamos al método de modificación del DAO
        }
        return false;
    }
}
