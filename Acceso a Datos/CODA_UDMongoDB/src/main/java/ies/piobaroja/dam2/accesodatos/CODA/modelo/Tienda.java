package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class Tienda implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Producto> productos;

    private List<ClienteUsuario> clientes;

    private List<Producto> productosHistorial;
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

    public void alta(ClienteUsuario cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        tiendaDAO.altaCliente(cliente);
        clientes.add(cliente);
        System.out.println("Cliente dado de alta correctamente: " + cliente);
    }

    public boolean baja(int dni) {
        
        ClienteUsuario cliente = tiendaDAO.consultaClienteDNI(dni);
        if (cliente != null) {
            tiendaDAO.bajaCliente(dni);
            clientes.remove(cliente);
            System.out.println("Cliente con DNI " + dni + " eliminado correctamente.");
            return true;
        }
        System.err.println("Cliente con DNI " + dni + " no encontrado.");
        return false;
    }

    public ClienteUsuario consultaPorDNI(int dni) {
        
        return tiendaDAO.consultaClienteDNI(dni);
    }

    public boolean modificacion(ClienteUsuario clienteModificado) {
        if (clienteModificado == null) {
            throw new IllegalArgumentException("El cliente modificado no puede ser nulo.");
        }
        boolean resultado = tiendaDAO.modificacion(clienteModificado);
        if (resultado) {
            System.out.println("Cliente modificado correctamente: " + clienteModificado);
        } else {
            System.err.println("No se pudo modificar el cliente: " + clienteModificado);
        }
        return resultado;
    }

    public List<ClienteUsuario> getClientes(int dni) {
        ClienteUsuario cliente = tiendaDAO.consultaClienteDNI(dni);
        return cliente != null ? List.of(cliente) : Collections.emptyList();
    }

    public void altaProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        tiendaDAO.altaProducto(producto);
        productos.add(producto);
        System.out.println("Producto dado de alta correctamente: " + producto);
    }

    public boolean bajaProducto(int id) {
        Producto producto = tiendaDAO.consultaProducto(id);
        if (producto != null) {
            tiendaDAO.bajaProducto(id);
            productos.remove(producto);
            System.out.println("Producto con ID " + id + " eliminado correctamente.");
            return true;
        }
        System.err.println("Producto con ID " + id + " no encontrado.");
        return false;
    }

    public Producto consultarProducto(int id) {
        return tiendaDAO.consultaProducto(id);
    }

    public boolean modificarProducto(Producto productoModificado) {
        if (productoModificado == null) {
            throw new IllegalArgumentException("El producto modificado no puede ser nulo.");
        }
        Producto producto = tiendaDAO.consultaProducto(productoModificado.getId());
        if (producto != null) {
            tiendaDAO.modificarProducto(productoModificado);
            System.out.println("Producto modificado correctamente: " + productoModificado);
            return true;
        }
        System.err.println("Producto con ID " + productoModificado.getId() + " no encontrado.");
        return false;
    }

    public void altaProductoAlquiler(AlquilerProducto productoAlquiler) {
        if (productoAlquiler == null) {
            throw new IllegalArgumentException("El producto de alquiler no puede ser nulo.");
        }
        tiendaDAO.altaProductoAlquiler(productoAlquiler);
        productos.add(productoAlquiler);
        System.out.println("Producto de alquiler registrado correctamente: " + productoAlquiler);
    }

    public boolean bajaProductoAlquiler(int id) {
        boolean resultado = tiendaDAO.bajaProductoAlquiler(id);
        if (resultado) {
            productos.removeIf(p -> p.getId() == id);
            System.out.println("Producto de alquiler con ID " + id + " eliminado correctamente.");
        } else {
            System.err.println("Producto de alquiler con ID " + id + " no encontrado.");
        }
        return resultado;
    }

    public AlquilerProducto consultaProductoAlquiler(int id) {
        return tiendaDAO.consultaProductoAlquiler(id);
    }

    public boolean modificarProductoAlquiler(AlquilerProducto productoAlquilerModificado) {
        if (productoAlquilerModificado == null) {
            throw new IllegalArgumentException("El producto de alquiler modificado no puede ser nulo.");
        }
        return tiendaDAO.modificarProductoAlquiler(productoAlquilerModificado);
    }

    public void realizarVenta(Producto producto) {
        if (producto != null && producto.isDisponibilidad()) {
            producto.setDisponibilidad(false);
            tiendaDAO.modificarProducto(producto);
            productosHistorial.add(producto);
            System.out.println("Venta realizada con éxito: " + producto);
        } else {
            System.err.println("Producto no disponible para la venta.");
        }
    }

    public void registrarAlquiler(AlquilerProducto producto) {
        if (producto != null && producto.isDisponibilidad()) {
            producto.setDisponibilidad(false);
            tiendaDAO.modificarProductoAlquiler(producto);
            productosHistorial.add(producto);
            System.out.println("Alquiler registrado con éxito: " + producto);
        } else {
            System.err.println("Producto no disponible para el alquiler.");
        }
    }

    public void asignarProductoACliente(ClienteUsuario cliente, Producto producto) {
        tiendaDAO.asignarProductoACliente(cliente, producto);
    }

    // Getters y Setters
    public List<ClienteUsuario> getClientes() {
        return Collections.unmodifiableList(clientes);
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public List<Producto> getProductosHistorial() {
        return Collections.unmodifiableList(productosHistorial);
    }
}