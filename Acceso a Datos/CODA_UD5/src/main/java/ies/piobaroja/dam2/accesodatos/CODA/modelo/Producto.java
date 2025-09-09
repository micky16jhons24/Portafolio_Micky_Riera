package ies.piobaroja.dam2.accesodatos.CODA.modelo;



public class Producto {
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

    // Getters y setters...
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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
        this.cliente = cliente;
    }
    
    

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", talla=" + talla +
               ", precioVenta=" + precioVenta + ", disponibilidad=" + disponibilidad + 
               ", cliente=" + (cliente != null ? cliente.getDni() : "N/A") + "]";
    }

}

