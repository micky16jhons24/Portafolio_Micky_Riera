package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.Serializable;
import java.sql.Date;



public class VentaProducto extends Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private Date fechaVenta;
    
    private int cantidadVendida=0;

    private ClienteUsuario cliente;
   
    // Constructor
    
    public VentaProducto() {}
    
    public VentaProducto(String nombre, String talla, Double precioVenta, boolean disponible, Date fechaVenta, int cantidadVendida) {
        super(nombre, talla, precioVenta, disponible);
        this.fechaVenta = fechaVenta;
        this.cantidadVendida = cantidadVendida;
    }
    

    // Getters y Setters específicos de VentaProducto
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

    @Override
    public String toString() {
        return super.toString() + 
               ", Fecha de Venta: " + fechaVenta + 
               ", Cantidad Vendida: " + cantidadVendida+
               ", Cliente: " + cliente.getNombre() 
              ;
    }
}

