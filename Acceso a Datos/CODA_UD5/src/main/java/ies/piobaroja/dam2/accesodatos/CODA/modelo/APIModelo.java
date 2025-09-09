package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class APIModelo {
    private static APIModelo instancia = new APIModelo();
    private Tienda tienda;
    private TiendaDAO tiendaDAO;
    private List<Producto> productos;

    private APIModelo() {
        tienda = new Tienda();  
        try {
			tiendaDAO = new TiendaDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        this.productos = new ArrayList<>(); // Asegúrate de inicializar la lista en el constructor
    }

    public static APIModelo getInstance() {
        if (instancia == null) {
            instancia = new APIModelo();
        }
        return instancia;
    }    

    public void altaCliente(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        ClienteUsuario cliente = new ClienteUsuario(dni, nombre, apellido, telefono, correo, codigoPostal);
        try {
			this.tienda.alta(cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public boolean bajaCliente(String dni) {
        return this.tienda.baja(dni);
    }

    public ClienteUsuario consultaClientePorDNI(String dni) {
        return this.tienda.consultaPorDNI(dni);
    }

    public boolean modificarCliente(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
       ClienteUsuario cliente=new ClienteUsuario(dni, nombre, apellido,telefono, correo,codigoPostal);
       return tienda.modificacion(cliente);
    }

    // Métodos para AlquilerProducto
    public void altaProductoAlquiler(String nombre, String talla, Double precioVenta, boolean disponibilidad,
    		LocalDate fechaAlquiler, int telefono1, int duracionDias) {
        AlquilerProducto producto = new AlquilerProducto(nombre, talla, precioVenta, disponibilidad, fechaAlquiler, telefono1, duracionDias);
        tienda.altaProductoAlquiler(producto);
        tienda.registrarAlquiler(producto); 
    }
    
    public boolean modificarProductoAlquiler(int id, String nombre, String talla, Double precioVenta,
                                              boolean disponibilidad, LocalDate fechaAlquiler, int telefono, int duracionDias) {
        AlquilerProducto producto = tienda.consultaProductoAlquiler(id);

        if (producto != null) {
            producto.setNombre(nombre);
            producto.setTalla(talla);
            producto.setPrecioVenta(precioVenta);
            producto.setDisponibilidad(disponibilidad);
            producto.setFechaAlquiler(fechaAlquiler);
            producto.setTelefono(telefono); // Asegúrate de que 'telefono' sea un String
            producto.setDuracionDias(duracionDias);
            tienda.modificarProductoAlquiler(producto);
            return true;
        }
        return false;
    }

    public boolean bajaProductoAlquiler(int id) {
        return tienda.bajaProductoAlquiler(id);
    }

    public AlquilerProducto consultarProductoAlquiler(int id) {
        return tienda.consultaProductoAlquiler(id);
    }

    // Métodos para Producto en general
    public void altaProducto(String nombre, String talla, Double precioVenta, boolean disponible) {
        Producto producto = new Producto(nombre, talla, precioVenta, disponible);
        tienda.altaProducto(producto);
    }

    public boolean bajaProducto(int id) {
        return tienda.bajaProducto(id);
    }

    public Producto consultaProducto(int id) {
        return tienda.consultarProducto(id);
    }

    public boolean modificacionProducto(int id, String nombre, String talla, Double precioVenta, boolean disponibilidad) {
        Producto producto = tienda.consultarProducto(id);
        if (producto != null) {
            producto.setNombre(nombre);
            producto.setTalla(talla);
            producto.setPrecioVenta(precioVenta);
            producto.setDisponibilidad(disponibilidad);
            tienda.modificarProducto(producto);
            return true;
        }
        return false;  
    }

    public void realizarVentaProducto(int id, int cantidadVendida) {
        Producto producto = tienda.consultarProducto(id); 

        if (producto != null && producto.isDisponibilidad() && cantidadVendida > 0) {
            producto.setDisponibilidad(false); 
            tiendaDAO.modificarProducto(producto);
            tienda.realizarVenta(producto); 
            System.out.println("Venta realizada con éxito.");
        } else {
            System.out.println("Producto no disponible para la venta o cantidad solicitada no válida.");
        }
    }

    public List<ClienteUsuario> obtenerClientes(String dni) {
        return List.of(tienda.consultaPorDNI(dni)); 
    }
    
    public List<Producto> obtenerProducto(int id) {
        return List.of(tienda.consultarProducto(id)); 
    }

    // Método para obtener la lista de productos
    public List<Producto> getProductos() {
        System.out.println("getProductos() llamado");
        return this.productos;
    }
    public boolean asignarProductoACliente(int idProducto, String dniCliente) {
        // Consultar producto por ID
        Producto producto = tienda.consultarProducto(idProducto);
        
        // Consultar cliente por DNI
        ClienteUsuario cliente = tienda.consultaPorDNI(dniCliente);

        // Verificar si el producto y el cliente existen, y si el producto está disponible
        if (producto != null && cliente != null && producto.isDisponibilidad()) {
            // Asignar producto al cliente
            producto.setDisponibilidad(false); // Marcar el producto como no disponible
            producto.setCliente(cliente); // Relacionar cliente con producto

            // Persistir cambios en la base de datos
            tienda.modificarProducto(producto); 
            tienda.modificacion(cliente);

            System.out.println("Producto asignado correctamente al cliente.");
            return true;
        } else {
            System.out.println("No se pudo asignar el producto. Verifique que el producto y cliente existen, y que el producto está disponible.");
            return false;
        }
    }
}


   