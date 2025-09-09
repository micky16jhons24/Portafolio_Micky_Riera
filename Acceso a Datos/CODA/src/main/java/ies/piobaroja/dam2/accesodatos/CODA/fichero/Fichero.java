package ies.piobaroja.dam2.accesodatos.CODA.fichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
  



import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Tienda;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;






public class Fichero {
	
	// -----------------Importar Exportar CSV.....................*//
	
	
	public static void exportarCSV(String nombreFichero , Tienda tienda) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("fichero/csv/" + nombreFichero + ".csv")))) {
	        bw.write("nombre,edad,telefono,dni");
	        bw.newLine();
	        
	        for (ClienteUsuario c : tienda.getClientes()) {
	            bw.write(c.getDni() + "," + c.getNombre() + "," +c.getApellido() + "," + c.getTelefono() + "," + c.getCorreo() + "," + c.getCodigoPostal()); 
	            bw.newLine(); 
	        }
	        
	        System.out.println("Archivo CSV creado con �xito.");
	    } catch (IOException e) {
	        System.err.println("Error al exportar el archivo CSV: " + e.getMessage());
	        throw e;  
	    }
	}
	
	
	public static Tienda importCSV (String nombreFichero) throws IOException {
		 Tienda tienda = new Tienda();
		    File archivo = new File("fichero/csv/" + nombreFichero + ".csv");
		    
		    if (!archivo.exists()) {
		        throw new IOException("El archivo no existe: " + archivo.getAbsolutePath());
		    }
		    
		    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
		        String linea;
		        br.readLine(); // Leer la cabecera del CSV
		        while ((linea = br.readLine()) != null) {
		            String[] datos = linea.split(","); 
		            if (datos.length >= 4) { // Asegúrate de que haya al menos 4 elementos
		                // Crear el cliente usando los primeros cuatro datos y asignar valores vacíos para los demás
		                ClienteUsuario cliente = new ClienteUsuario(datos[0], datos[1], datos[2], datos[3], "", "");
		                tienda.alta(cliente); 
		            }
		        }
		        System.out.println("Archivo CSV importado con éxito.");
		    } catch (IOException e) {
		        System.err.println("Error al importar el archivo CSV: " + e.getMessage());
		        throw e;  
		    }
		    
		    return tienda;
	}
	
	// -----------------Importar Exportar OBJ.....................*//
	public static void ExportOBJ(String nombreFichero , Tienda tienda) throws FileNotFoundException, IOException {
		try (FileOutputStream fileOut = new FileOutputStream("fichero/obj/" + nombreFichero + ".obj");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
		        out.writeObject(tienda); 
		        System.out.println("Archivo OBJ creado con �xito.");
		    } catch (IOException e) {
		        System.err.println("Error al exportar el archivo OBJ: " + e.getMessage());
		        throw e;
		    }
	}
	
	
	public static Tienda importOBJ (String nombreFichero) throws IOException {
		Tienda tienda = null;
        File archivo = new File("fichero/obj/" + nombreFichero + ".obj");
        
        if (!archivo.exists()) {
            throw new IOException("El archivo no existe: " + archivo.getAbsolutePath());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        	tienda = (Tienda) ois.readObject();
            System.out.println("Archivo OBJ importado con �xito.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al importar el archivo OBJ: " + e.getMessage());
            throw new IOException("Error al importar OBJ", e);
        }
        
        return tienda;
	}
	
	// -----------------Importar Exportar XML.....................*//
	
	public static void exportarXML(String nombreFichero, Tienda tienda) throws IOException {
        try {
            JAXBContext contextoEstudio = JAXBContext.newInstance(Tienda.class);
            Marshaller marshaller = contextoEstudio.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File archivo = new File("fichero/xml/" + nombreFichero + ".xml");
            marshaller.marshal(tienda, archivo);

            System.out.println("Archivo XML creado con �xito.");
        } catch (Exception e) {
            System.err.println("Error al exportar el archivo XML: " + e.getMessage());
            throw new IOException("Error al exportar XML", e);
        }
    }


	
	
	
	
	public static Tienda  importarXML(String nombreFichero) throws IOException {
		Tienda tienda = null;
        try {
            JAXBContext contextoEstudio = JAXBContext.newInstance(Tienda.class);
            Unmarshaller unmarshaller = contextoEstudio.createUnmarshaller();

            File archivo = new File("fichero/xml/" + nombreFichero + ".xml");
            tienda = (Tienda) unmarshaller.unmarshal(archivo);

            System.out.println("Archivo XML importado con �xito.");
        } catch (Exception e) {
            System.err.println("Error al importar el archivo XML: " + e.getMessage());
            throw new IOException("Error al importar XML", e);
        }
        return tienda;
    }

	
	/* ----------- IMPORTAR/EXPORTAR A JSON -------------------*/ 
	public static void exportarJSON (String nombreFichero, Tienda tienda) throws IOException{
		try {
	        File directorio = new File("fichero/json");
	        if (!directorio.exists()) {
	            directorio.mkdirs();
	        }

	        FileWriter writer = new FileWriter("fichero/json/" + nombreFichero + ".json");
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        gson.toJson(tienda, writer);
	        writer.flush();  
	        writer.close();  

	        System.out.println("Archivo JSON creado con �xito.");
	    } catch (IOException e) {
	        System.err.println("Error al exportar el archivo JSON: " + e.getMessage());
	        throw e;  
	    }
	}
	
	public static Tienda importarJSON (String nombreFichero) throws IOException{
		Gson gson = new Gson();
	    Tienda tienda;
	    
	    File archivo = new File("fichero/json/" + nombreFichero + ".json");
	    
	    if (!archivo.exists()) {
	        throw new IOException("El archivo no existe: " + archivo.getAbsolutePath());
	    }

	    try (JsonReader reader = new JsonReader(new FileReader(archivo))) {
	    	tienda = gson.fromJson(reader, Tienda.class);
	    } catch (Exception e) {
	        throw new IOException("Error al importar el archivo JSON: " + e.getMessage(), e);        
	    }
	    
	    return tienda;
	

}
}
