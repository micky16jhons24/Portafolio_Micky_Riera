package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.io.IOException;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.APIModelo;
import jakarta.xml.bind.JAXBException;


public class ImportaraTiendaControlador {
	  APIModelo api = APIModelo.getInstance();

	   public void importXML(String nombreFichero) throws  IOException {
	     try {
			api.importarXML(nombreFichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }

	   public void importarJSON(String nombreFichero) throws IOException{
		api.importarJSON(nombreFichero);
	   }

	   public void importOBJ(String nombreFichero) throws  IOException, ClassNotFoundException {
	     api.importarObj(nombreFichero);
	   }

	   public void importCSV(String nombreFichero) throws IOException {
	      api.importarCSV(nombreFichero);
	   }
}
