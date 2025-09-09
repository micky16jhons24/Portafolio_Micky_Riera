package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.util.List;
import java.time.LocalDate;
import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class APIModelo {
    private static APIModelo instancia = new APIModelo(); 
    private Tienda tienda;
    private TiendaDAO tiendaDAO = new TiendaDAO();

    private APIModelo() {
        tienda = new Tienda();
    }

    public static APIModelo getInstance() {
        return instancia;
    }

    public void altaCliente( String nombre,String apellido, String telefono, String correo, String codigoPostal) {
        ClienteUsuario cliente = new ClienteUsuario(nombre ,apellido, telefono, correo, codigoPostal);
        this.tienda.alta(cliente);
    }

    public boolean bajaCliente(int dni) {
        return this.tienda.baja(dni);
    }

    public ClienteUsuario consultaClientePorDNI(int dni) {
        return this.tienda.consultaPorDNI(dni);
    }

    public boolean modificarCliente(int dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        ClienteUsuario cliente = tienda.consultaPorDNI(dni);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            cliente.setCodigoPostal(codigoPostal);
            cliente.setDni(dni);
            return tienda.modificacion(cliente);
        }
        return false;
    }

    // Métodos para gestionar productos de alquiler
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
            producto.setTelefono(telefono);
            producto.setDuracionDias(duracionDias);
            producto.setId(id);
            return tienda.modificarProductoAlquiler(producto);
            
        }
        return false;
    }

    public boolean bajaProductoAlquiler(int id) {
        return tienda.bajaProductoAlquiler(id);
    }

    public AlquilerProducto consultarProductoAlquiler(int id) {
        return tienda.consultaProductoAlquiler(id);
    }

    // Métodos para productos en general
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
            producto.setId(id);
            return tienda.modificarProducto(producto);
             
        }
        return false;
    }

    public void realizarVentaProducto(int id, int cantidadVendida) {
        Producto producto = tienda.consultarProducto(id);

        if (producto != null && producto.isDisponibilidad() && cantidadVendida > 0) {
            producto.setDisponibilidad(false);
            tienda.realizarVenta(producto);
            System.out.println("Venta realizada con éxito.");
        } else {
            System.out.println("Producto no disponible para la venta o cantidad solicitada no válida.");
        }
    }

    public List<ClienteUsuario> obtenerClientes(int dni) {
        return List.of(tienda.consultaPorDNI(dni));
    }

    public void asignarProductoACliente(ClienteUsuario cliente, Producto producto) {
        if (!tiendaDAO.asignarProductoACliente(cliente, producto)) {
            throw new RuntimeException("No se pudo asignar el producto al cliente.");
        }
    }

    public void asignarProductoACliente(int dni, int idProducto) {
        ClienteUsuario cliente = tiendaDAO.consultaClienteDNI(dni);
        Producto producto = tiendaDAO.consultaProducto(idProducto);

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado con el DNI especificado: " + dni);
        }

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado con el ID especificado: " + idProducto);
        }

        if (!tiendaDAO.asignarProductoACliente(cliente, producto)) {
            throw new RuntimeException("No se pudo asignar el producto al cliente.");
        }
    }
}

