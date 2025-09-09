package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ies.piobaroja.dam2.accesodatos.CODA.fichero.Fichero;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;




@XmlRootElement(name = "estudio") 
@XmlType(name = "", propOrder = {"clientes"}) 

public class Tienda  implements Serializable{
	private static final long serialVersionUID = 1L;

    private ArrayList<ClienteUsuario> clientes;

    public Tienda() {
        clientes = new ArrayList<>();
    }

    public void alta(ClienteUsuario cliente) {
        clientes.add(cliente);
    }

    public boolean baja(String dni) {
        Iterator<ClienteUsuario> it = clientes.iterator();
        while (it.hasNext()) {
            ClienteUsuario cliente = it.next();
            if (cliente.getDni().equals(dni)) {
                it.remove();  // Eliminamos el cliente
                return true;
            }
        }
        return false;  // Si no se encontró el cliente, retornamos false
    }

    public ClienteUsuario consultaPorDNI(String dni) {
        for (ClienteUsuario cliente : clientes) {  // Recorre la lista de clientes
            if (cliente.getDni().equals(dni)) {  // Compara el DNI
                return cliente;  // Retorna el cliente si coincide el DNI
            }
        }
        return null;  // Si no encuentra el cliente, retorna null
    }
    

    public void modificacion(ClienteUsuario clienteModificado) {
        for (ClienteUsuario cliente : clientes) {
            if (cliente.getCodigo() == clienteModificado.getCodigo()) {
                cliente.setNombre(clienteModificado.getNombre());
                cliente.setApellido(clienteModificado.getApellido());
                cliente.setTelefono(clienteModificado.getTelefono());
                cliente.setCorreo(clienteModificado.getCorreo());
                cliente.setCodigoPostal(clienteModificado.getCodigoPostal());
                break;
            }
        }
    }
    
    
    @XmlElement(name = "clientes")  // Anotaci�n JAXB para serializar la lista de clientes
	public ArrayList<ClienteUsuario> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<ClienteUsuario> clientes) {
		this.clientes = clientes;
	}
	
	
	 
    /*-----------------------------------XML---------------------------------------------*/
    
	public void ExportarXML(String nombreFichero) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Tienda.class); // Cambia según sea necesario
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, new File(nombreFichero));	   }
	
	
	public void importXML(String nombreFichero) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Tienda.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Tienda tienda = (Tienda) unmarshaller.unmarshal(new File(nombreFichero));
        this.clientes = tienda.getClientes(); // Asigna la lista de clientes	   }
	}
    
    /*------------------------------------OBJ--------------------------------------------*/

    
    public void exportarOBJ(String nombreFichero) throws IOException {
    	Fichero.ExportOBJ(nombreFichero, this);
    }
    public void importarOBJ (String nombreFichero) throws IOException, ClassNotFoundException {
    	this.clientes = Fichero.importOBJ(nombreFichero).clientes;
     }
    
    /*-------------------------------------JSON-------------------------------------------*/

    public void exportaJSON(String nombreFichero) throws IOException {
    	 Fichero.exportarJSON(nombreFichero, this);
     }
    public void importJSON(String nombreFichero) throws IOException {
    	this.clientes = Fichero.importarJSON(nombreFichero).clientes;
    }
    
    /*---------------------------------CSV-----------------------------------------------*/

    
    public void exportarCSV(String nombreFichero) throws IOException {
    	Fichero.exportarCSV(nombreFichero, this);
    }
    
    public void importarCSV(String nombreFichero) throws IOException {
    	this.clientes = Fichero.importCSV(nombreFichero).clientes;
    }
	
    
    
    
    
    
    public Iterator<ClienteUsuario> iterator() {
        return this.clientes.iterator();
     }
	
    
    
    public void addCliente(ClienteUsuario cliente) {
		// TODO Auto-generated method stub
		
	}
    
    
    
}