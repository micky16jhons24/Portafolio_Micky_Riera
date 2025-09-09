package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

@Entity
public class VentaProducto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaVenta;
    private int cantidadVendida;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteUsuario cliente;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public VentaProducto() {}

    public VentaProducto(Date fechaVenta, int cantidadVendida, ClienteUsuario cliente, Producto producto) {
        this.fechaVenta = fechaVenta;
        this.cantidadVendida = cantidadVendida;
        this.cliente = cliente;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        if (cantidadVendida < 0) {
            throw new IllegalArgumentException("La cantidad vendida no puede ser negativa.");
        }
        this.cantidadVendida = cantidadVendida;
    }

    public ClienteUsuario getCliente() {
        return cliente;
    }

    public void setCliente(ClienteUsuario cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "VentaProducto [id=" + id +
               ", fechaVenta=" + fechaVenta +
               ", cantidadVendida=" + cantidadVendida +
               ", producto=" + (producto != null ? producto.getNombre() : "Producto no disponible") +
               ", cliente=" + (cliente != null ? cliente.getNombre() : "Cliente no disponible") +
               "]";
    }
}
