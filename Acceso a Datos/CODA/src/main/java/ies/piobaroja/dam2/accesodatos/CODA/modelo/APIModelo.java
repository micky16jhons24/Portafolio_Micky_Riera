package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;





public class APIModelo {
    private static APIModelo instancia = new APIModelo();
    private Tienda tienda;

    
    
    /* CONSTRUCTOR */
    private APIModelo() {
        tienda = new Tienda();  // Asegúrate de que Tienda esté bien inicializada.
    }

    /* MÉTODO ESTÁTICO */
    public static APIModelo getInstance() {
    	 if (instancia == null) {
    		 instancia = new APIModelo();
         }
         return instancia;  // No es necesario verificar si es null, ya que es inicializada arriba.
    }

    /* Método para dar de alta un cliente */
    public void altaCliente(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        ClienteUsuario cliente = new ClienteUsuario(dni, nombre, apellido, telefono, correo, codigoPostal);
        this.tienda.alta(cliente);
    }

    /* Método para dar de baja un cliente por DNI */
    public boolean bajaCliente(String dni) {
        return this.tienda.baja(dni);
    }

    /* Método para consultar un cliente por DNI */
    public ClienteUsuario consultaClientePorDNI(String dni) {
        return this.tienda.consultaPorDNI(dni);  // Devolvemos directamente el cliente, no es necesario devolver un arreglo.
    }

    /* Método para modificar los datos de un cliente */
    public boolean modificarCliente(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        ClienteUsuario cliente = tienda.consultaPorDNI(dni);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            cliente.setCodigoPostal(codigoPostal);
            return true;  // Se modificó con éxito.
        }
        return false;  // Cliente no encontrado.
    }

    /* Método para obtener todos los clientes */
    public List<ClienteUsuario> obtenerClientes() {
        return tienda.getClientes();  // Devuelve la lista de clientes de la tienda.
    }

    /* Métodos de exportación e importación */

    public void exportarXML(String nombreFichero) throws IOException, JAXBException {
    	 JAXBContext context = JAXBContext.newInstance(APIModelo.class); // Cambia TuClaseModelo por el modelo adecuado
         Marshaller marshaller = context.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

         // Asegúrate de que la carpeta "xml" existe
         File file = new File("fichero/xml/" + nombreFichero);
         file.getParentFile().mkdirs(); // Crea la carpeta si no existe

         marshaller.marshal(this, file);  // Asegúrate de que este método esté correctamente implementado en la clase Tienda.
    }

    public void importarXML(String nombreFichero) throws IOException, JAXBException {
        tienda.importXML(nombreFichero);  // Verificar la implementación en Tienda.
    }

    public void exportarJSON(String nombreFichero) throws IOException {
        tienda.exportaJSON(nombreFichero);  // Verifica que el nombre del método en Tienda esté correcto.
    }

    public void importarJSON(String nombreFichero) throws IOException {
        tienda.importJSON(nombreFichero);  // Asegúrate de que este método esté correctamente implementado.
    }

    public void exportarObj(String nombreFichero) throws IOException {
        tienda.exportarOBJ(nombreFichero);  // Verificar que este método esté implementado correctamente.
    }

    public void importarObj(String nombreFichero) throws IOException, ClassNotFoundException {
        tienda.importarOBJ(nombreFichero);  // Manejar la excepción adecuadamente.
    }

    public void exportarCSV(String nombreFichero) throws IOException {
        tienda.exportarCSV(nombreFichero);  // Asegurarse de que el método exista y funcione en Tienda.
    }

    public void importarCSV(String nombreFichero) throws IOException {
        tienda.importarCSV(nombreFichero);  // Verificar implementación en Tienda.
    }
}
