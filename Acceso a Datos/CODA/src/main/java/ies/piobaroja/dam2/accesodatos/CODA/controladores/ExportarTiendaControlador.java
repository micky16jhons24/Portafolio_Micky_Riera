package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.io.FileNotFoundException;
import java.io.IOException;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import jakarta.xml.bind.JAXBException;

public class ExportarTiendaControlador {
	APIModelo api = APIModelo.getInstance();

   
    // Método para exportar a XML
    public void exportarXML(String nombreFichero) throws  IOException {
        try {
			api.exportarXML(nombreFichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Llama al método de exportación a XML del API
    }

    // Método para exportar a JSON
    public void exportarJSON(String nombreFichero) throws IOException {
        api.exportarJSON(nombreFichero); // Llama al método de exportación a JSON del API
    }

    // Método para exportar a Object
    public void exportarObj(String nombreFichero) throws FileNotFoundException, IOException {
        api.exportarObj(nombreFichero); // Llama al método de exportación a Object del API
    }

    // Método para exportar a CSV
    public void exportarCSV(String nombreFichero) throws IOException {
        api.exportarCSV(nombreFichero); // Llama al método de exportación a CSV del API
    }
}
