package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.sql.Date;
import java.time.LocalDate;



public class AlquilerProducto extends Producto {
    private Date  fechaAlquiler;
    private int telefono; 
    private int duracionDias;

    public AlquilerProducto() {
    }

    public AlquilerProducto(String nombre, String talla, Double precioVenta, boolean disponibilidad, 
    		Date  fechaAlquiler, int telefono, int duracionDias) {
        super(nombre, talla, precioVenta, disponibilidad);  
        this.fechaAlquiler = fechaAlquiler;
        this.telefono = telefono; 
        this.duracionDias = duracionDias;
    }

    public Date  getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date  fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public int getTelefono() { 
        return telefono;
    }

    public void setTelefono(int telefono2) { 
        this.telefono = telefono2;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    @Override
    public String toString() {
        return String.format("AlquilerProducto [fechaAlquiler=%s, telefono=%s, duracionDias=%d, %s]", 
                              fechaAlquiler, telefono, duracionDias, super.toString());
    }

}

