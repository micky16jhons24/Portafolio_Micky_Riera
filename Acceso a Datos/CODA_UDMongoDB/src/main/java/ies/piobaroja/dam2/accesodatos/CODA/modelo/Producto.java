package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

   
    private int id;

    private String nombre;

    private String talla;

    private Double precioVenta;

    private boolean disponibilidad;

    
    private ClienteUsuario cliente;

    public Producto() {}

    public Producto(String nombre, String talla, Double precioVenta, boolean disponibilidad) {
        this.nombre = nombre;
        this.talla = talla;
        this.precioVenta = precioVenta;
        this.disponibilidad = disponibilidad;
    }

    public Producto(String nombre, String talla, Double precioVenta, boolean disponibilidad, ClienteUsuario cliente) {
        this(nombre, talla, precioVenta, disponibilidad);
        this.setCliente(cliente);
    }

    public int getId() {
        return id;
    }
    

    public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        if (precioVenta == null || precioVenta <= 0) {
            throw new IllegalArgumentException("El precio de venta debe ser mayor que cero");
        }
        this.precioVenta = precioVenta;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public ClienteUsuario getCliente() {
        return cliente;
    }

    public void setCliente(ClienteUsuario cliente) {
        if (this.cliente != null) {
            this.cliente.getProductos().remove(this);
        }
        this.cliente = cliente;
        if (cliente != null && !cliente.getProductos().contains(this)) {
            cliente.getProductos().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", talla=" + talla +
               ", precioVenta=" + precioVenta + ", disponibilidad=" + disponibilidad +
               ", cliente=" + (cliente != null ? cliente.getDni() : "N/A") + "]";
    }
}
