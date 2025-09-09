package ies.piobaroja.dam2.accesodatos.CODA.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.VentaProducto;

public class TiendaDAO {
  

	
	
	private String uri = "mongodb://localhost:27017";

	private String dbName="Tienda";
	
	private MongoDatabase db;
	private Gson gson = new GsonBuilder().create();

	
	
	public TiendaDAO() {
		MongoClient mongoCliente = MongoClients.create(uri);
		db = mongoCliente.getDatabase(dbName);
		
	}





   private int countClienteUsuario() {
	   MongoCollection<Document> collectionClienteUsuario = db.getCollection(ClienteUsuario.class.getSimpleName());
	   return (int) collectionClienteUsuario.estimatedDocumentCount();
   }
	
	public void altaCliente(ClienteUsuario cliente) {
	   MongoCollection<Document> collectionClienteUsuario = db.getCollection(cliente.getClass().getSimpleName());
	   
	   cliente.setDni(countClienteUsuario());
	   
	   Document documento;
	   
	   Gson gson=new Gson();
	   String json=gson.toJson(cliente);
	   System.out.println(json);
	   
	   documento = Document.parse(json);
	   
	   collectionClienteUsuario.insertOne(documento);
	   
	   
	}
   
   


   public ClienteUsuario consultaClienteDNI(int dni) {
	   MongoCollection<Document> collectionClienteUsuario = db.getCollection(ClienteUsuario.class.getSimpleName());
	   
	   Document filtro= new Document("dni", dni);
	   
	   Document resultado=collectionClienteUsuario.find(filtro).first();
	   
	   Gson gson=new Gson();
	   
	   ClienteUsuario c = gson.fromJson(resultado.toJson(), ClienteUsuario.class);
	   
	   System.out.println(resultado);

	   return c;

	}


    public boolean modificacion(ClienteUsuario cliente) {
 	   MongoCollection<Document> collectionClienteUsuario = db.getCollection(ClienteUsuario.class.getSimpleName());
	   Document filtro= new Document("dni", cliente.getDni());
	   Gson gson=new Gson();
	   String json = gson.toJson(cliente);
	  
	   Document Documentocambio = Document.parse(json);
	  
	   collectionClienteUsuario.updateOne(filtro, new Document("$set",Documentocambio));
		return true;
        
    }
    
    public boolean bajaCliente(int dni) {
  	   MongoCollection<Document> collectionClienteUsuario = db.getCollection(ClienteUsuario.class.getSimpleName());
	   Document filtro= new Document("dni", dni);
	   collectionClienteUsuario.deleteOne(filtro);
    	
		return false;
        
    }


 // alquiler Producto
 	private int countAlquilerProducto() {
 		MongoCollection<Document> collectionAlquilerProducto = db.getCollection(AlquilerProducto.class.getSimpleName());
 		return (int) collectionAlquilerProducto.estimatedDocumentCount();
 	}
 	
 	public void altaProductoAlquiler(AlquilerProducto alquilerProducto) {
 	    MongoCollection<Document> collectionAlquilerProducto = db.getCollection(alquilerProducto.getClass().getSimpleName());
 	    alquilerProducto.setId(countAlquilerProducto());
 	    
 	    // Utilizamos Gson para serializar el objeto completo
 	    Gson gson = new Gson();
 	    String json = gson.toJson(alquilerProducto);
 	    Document documento = Document.parse(json);
 	    
 	    collectionAlquilerProducto.insertOne(documento);
 	}


 	public AlquilerProducto consultaProductoAlquiler(int id) {
 		MongoCollection<Document> collectionAlquilerProducto = db.getCollection(AlquilerProducto.class.getSimpleName());

 		Document filtro = new Document("id", id);

 		Document resultado = collectionAlquilerProducto.find(filtro).first();

 		Gson gson = new Gson();

 		AlquilerProducto aq = gson.fromJson(resultado.toJson(), AlquilerProducto.class);

 		System.out.println(resultado);

 		return aq;

 	}

 	public boolean modificarProductoAlquiler(AlquilerProducto alquilerProducto) {
 	    MongoCollection<Document> collectionAlquilerProducto = db.getCollection(AlquilerProducto.class.getSimpleName());

 	    // 🔍 Verificar si el producto existe antes de modificarlo
 	    Document filtro = new Document("_id", alquilerProducto.getId()); // Buscar por "id"
 	    Document resultado = collectionAlquilerProducto.find(filtro).first();

 	    if (resultado == null) {
 	        System.out.println("❌ ERROR: No se encontró el producto con ID: " + alquilerProducto.getId());
 	        return false;
 	    } else {
 	        System.out.println("✅ Producto encontrado antes de modificar: " + resultado.toJson());
 	    }

 	    // 🔄 Corrección del formato de los datos a actualizar
 	    Document updateFields = new Document()
 	        .append("nombre", alquilerProducto.getNombre())
 	        .append("talla", alquilerProducto.getTalla()) 
 	        .append("precioVenta", alquilerProducto.getPrecioVenta())
 	        .append("disponibilidad", alquilerProducto.isDisponibilidad())
 	        .append("Fecha Alquiler", alquilerProducto.getFechaAlquiler().toString()) // Guardar fecha correctamente
 	        .append("telefono", alquilerProducto.getTelefono())
 	        .append("duracionDias", alquilerProducto.getDuracionDias());

 	    // 🔍 Mostrar qué datos se intentan actualizar
 	    System.out.println("🔄 Intentando modificar el producto con: " + updateFields.toJson());

 	    // 🔄 Ejecutar la actualización en MongoDB
 	    long modifiedCount = collectionAlquilerProducto.updateOne(
 	        filtro, new Document("$set", updateFields)
 	    ).getModifiedCount();

 	    if (modifiedCount > 0) {
 	        System.out.println("✅ Producto actualizado correctamente en la base de datos.");
 	        return true;
 	    } else {
 	        System.out.println("⚠️ ATENCIÓN: MongoDB no modificó nada. Forzando actualización...");

 	        // 🔄 FORZAR ACTUALIZACIÓN CON REEMPLAZO
 	        resultado.putAll(updateFields);
 	        collectionAlquilerProducto.replaceOne(filtro, resultado);
 	        
 	        return true;
 	    }
 	}



 	public boolean bajaProductoAlquiler(int id) {
 		MongoCollection<Document> collectionAlquilerProducto = db.getCollection(AlquilerProducto.class.getSimpleName());
 		Document filtro = new Document("id", id);
 		collectionAlquilerProducto.deleteOne(filtro);

 		return false;
 	}

 	
//Producto
    private int countProducto() {
        MongoCollection<Document> collectionProducto = db.getCollection(Producto.class.getSimpleName());
        return (int) collectionProducto.estimatedDocumentCount();
    }

    public void altaProducto(Producto producto) {
        MongoCollection<Document> collectionProducto = db.getCollection(Producto.class.getSimpleName());

        producto.setId(countProducto());
        
        Gson gson = new Gson();
        String json = gson.toJson(producto);
        System.out.println(json);

        Document documento = Document.parse(json);

        collectionProducto.insertOne(documento);
    }

    public boolean bajaProducto(int id) {
        MongoCollection<Document> collectionProducto = db.getCollection(Producto.class.getSimpleName());
        Document filtro = new Document("id", id);
        collectionProducto.deleteOne(filtro);
        return true;
    }

    public Producto consultaProducto(int id) {
        MongoCollection<Document> collectionProducto = db.getCollection(Producto.class.getSimpleName());
        Document filtro = new Document("id", id);
        Document resultado = collectionProducto.find(filtro).first();

        if (resultado != null) {
            Gson gson = new Gson();
            return gson.fromJson(resultado.toJson(), Producto.class);
        }
        return null;
    }

    public boolean modificarProducto(Producto productoModificado) {
        MongoCollection<Document> collectionProducto = db.getCollection(Producto.class.getSimpleName());
        Document filtro = new Document("id", productoModificado.getId());
        Gson gson = new Gson();
        String json = gson.toJson(productoModificado);
        Document cambios = Document.parse(json);

        collectionProducto.updateOne(filtro, new Document("$set", cambios));
        return true;
    }
    
    public List<Producto> consultarProductosPorDisponibilidad(boolean disponibilidad) {
        MongoCollection<Document> collection = db.getCollection(Producto.class.getSimpleName());
        MongoCursor<Document> cursor = collection.find(Filters.eq("disponibilidad", disponibilidad)).iterator();
        List<Producto> productos = new ArrayList<>();
        while (cursor.hasNext()) {
            productos.add(gson.fromJson(cursor.next().toJson(), Producto.class));
        }
        return productos;
    }

    //asignar
    public boolean asignarProductoACliente(ClienteUsuario cliente, Producto producto) {
        MongoCollection<Document> collectionProducto = db.getCollection(Producto.class.getSimpleName());
        Document filtro = new Document("id", producto.getId());
        Document update = new Document("$set", new Document("cliente.dni", cliente.getDni()));
        collectionProducto.updateOne(filtro, update);
        return true;
    }

    //Realizar Venta
    public boolean realizarVenta(int cantidadVendida, Date fechaVenta, int idProducto, int idCliente) {
        MongoCollection<Document> collectionVenta = db.getCollection(VentaProducto.class.getSimpleName());
        VentaProducto venta = new VentaProducto(fechaVenta, cantidadVendida, consultaClienteDNI(idCliente), consultaProducto(idProducto));
        venta.setId((long) collectionVenta.estimatedDocumentCount());
        Document documento = Document.parse(gson.toJson(venta));
        collectionVenta.insertOne(documento);
        return true;
    }

    public List<ClienteUsuario> obtenerTodosLosClientes() {
        MongoCollection<Document> collection = db.getCollection(ClienteUsuario.class.getSimpleName());
        MongoCursor<Document> cursor = collection.find().iterator();
        List<ClienteUsuario> clientes = new ArrayList<>();
        while (cursor.hasNext()) {
            clientes.add(gson.fromJson(cursor.next().toJson(), ClienteUsuario.class));
        }
        return clientes;
    }
    
//consulta 1
    public long contarClientesQuery() {
        MongoCollection<Document> collection = db.getCollection(ClienteUsuario.class.getSimpleName());
        return collection.countDocuments();
    }
//consulta 2
    public long contarVentasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        MongoCollection<Document> collection = db.getCollection(VentaProducto.class.getSimpleName());
        return collection.countDocuments(Filters.and(
            Filters.gte("fechaVenta", Date.valueOf(fechaInicio)),
            Filters.lte("fechaVenta", Date.valueOf(fechaFin))
        ));
    }

    //consulta 3
    public long ClienteConMasDeUnaCompra() {
        MongoCollection<Document> collection = db.getCollection(VentaProducto.class.getSimpleName());

        // Obtiene todos los DNI distintos de los clientes que han realizado compras
        List<Integer> dniClientes = collection.distinct("cliente.dni", Integer.class).into(new ArrayList<>());

        // Filtra los clientes con más de una compra
        long count = 0;
        for (Integer dni : dniClientes) {
            long numCompras = collection.countDocuments(Filters.eq("cliente.dni", dni));
            if (numCompras > 1) {
                count++;
            }
        }
        
        return count;
    }


}





