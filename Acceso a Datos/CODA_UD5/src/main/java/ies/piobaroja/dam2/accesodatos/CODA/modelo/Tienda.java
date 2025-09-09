package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class Tienda implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Producto> productos;

    private ArrayList<ClienteUsuario> clientes;

    private ArrayList<Producto> productosHistorial;
    private TiendaDAO tiendaDAO;

    public Tienda() {
        try {
            productos = new ArrayList<>();
            clientes = new ArrayList<>();
            productosHistorial = new ArrayList<>();
            tiendaDAO = new TiendaDAO();
            System.out.println("Tienda inicializada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al inicializar Tienda: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Métodos de Cliente
    public void alta(ClienteUsuario cliente) throws SQLException {
        tiendaDAO.altaCliente(cliente);
    }

    public boolean baja(String dni) {
        ClienteUsuario cliente = tiendaDAO.consultaClienteDNI(dni);
        if (cliente != null) {
            tiendaDAO.bajaCliente(dni);
            return true;
        }
        return false;
    }

    public ClienteUsuario consultaPorDNI(String dni) {
        return tiendaDAO.consultaClienteDNI(dni);
    }

    public boolean modificacion(ClienteUsuario clienteModificado) {
       boolean resultado= tiendaDAO.modificacion(clienteModificado);
		return resultado;
    }

    public List<ClienteUsuario> getClientes(String dni) {
        return List.of(tiendaDAO.consultaClienteDNI(dni));
    }

    // Métodos para gestionar productos
    public void altaProducto(Producto producto) {
        tiendaDAO.altaProducto(producto);
        productos.add(producto);
    }

    public boolean bajaProducto(int id) {
        Producto producto = tiendaDAO.consultaProducto(id);
        if (producto != null) {
            tiendaDAO.bajaProducto(id);
            return true;
        }
        return false;
    }

    public Producto consultarProducto(int id) {
        return tiendaDAO.consultaProducto(id);
    }

    public boolean modificarProducto(Producto productoModificado) {
        Producto producto = tiendaDAO.consultaProducto(productoModificado.getId());
        if (producto != null) {
            tiendaDAO.modificarProducto(productoModificado);
            return true;
        }
        return false;
    }

    // Métodos para productos de alquiler
    public void altaProductoAlquiler(AlquilerProducto productoAlquiler) {
        tiendaDAO.altaProductoAlquiler(productoAlquiler);
    }

    public boolean bajaProductoAlquiler(int id) {
        return tiendaDAO.bajaProductoAlquiler(id);
    }

    public AlquilerProducto consultaProductoAlquiler(int id) {
        return tiendaDAO.consultaProductoAlquiler(id);
    }

    public boolean modificarProductoAlquiler(AlquilerProducto productoAlquilerModificado) {
        return tiendaDAO.modificarProductoAlquiler(productoAlquilerModificado);
    }

    // Método de venta
    public void realizarVenta(Producto producto) {
        if (producto.isDisponibilidad()) {
            producto.setDisponibilidad(false);
            tiendaDAO.modificarProductoAlquiler(null);
            productosHistorial.add(producto);
            System.out.println("Venta realizada con éxito.");
        } else {
            System.out.println("Producto no disponible para la venta.");
        }
    }

    public void registrarAlquiler(AlquilerProducto producto) {
        if (producto.isDisponibilidad()) {
            producto.setDisponibilidad(false);
            tiendaDAO.modificarProductoAlquiler(producto);
            productosHistorial.add(producto);
            System.out.println("Alquiler registrado con éxito.");
        } else {
            System.out.println("Producto no disponible para el alquiler.");
        }
        
        
        
        
    }
    
    public boolean asignarProductoACliente(int idProducto, String dniCliente) {
        // Recuperar el producto y el cliente desde la base de datos
        Producto producto = tiendaDAO.consultaProducto(idProducto);
        ClienteUsuario cliente = tiendaDAO.consultaClienteDNI(dniCliente);

        if (producto != null && cliente != null) {
            // Asignar el producto al cliente y viceversa
            producto.setCliente(cliente);
            cliente.addProducto(producto);

            // Guardar los cambios en la base de datos
            tiendaDAO.modificarProducto(producto); // Asegúrate de que este método guarda el producto correctamente
            tiendaDAO.modificacion(cliente);  // Asegúrate de que este método guarda el cliente correctamente

            return true;
        }
        return false;
    }


    

    // Getters y Setters
    public ArrayList<ClienteUsuario> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<ClienteUsuario> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Producto> getProductosHistorial() {
        return productosHistorial;
    }

    public void setProductosHistorial(ArrayList<Producto> productosHistorial) {
        this.productosHistorial = productosHistorial;
    }

    public TiendaDAO getTiendaDAO() {
        return tiendaDAO;
    }

    public void setTiendaDAO(TiendaDAO tiendaDAO) {
        this.tiendaDAO = tiendaDAO;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}

