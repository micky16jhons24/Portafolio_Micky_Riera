package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.sql.Date;
import java.time.LocalDate;




public class AlquilerProducto extends Producto {
    private LocalDate  fechaAlquiler;
    private int telefono; // Cambiado a String
    private int duracionDias;

    // Constructor vacío necesario para JPA
    public AlquilerProducto() {
    }

    // Constructor con todos los campos requeridos (sin id)
    public AlquilerProducto(String nombre, String talla, Double precioVenta, boolean disponibilidad, 
    		LocalDate  fechaAlquiler, int telefono, int duracionDias) {
        super(nombre, talla, precioVenta, disponibilidad);  // Llamada al constructor de Producto
        this.fechaAlquiler = fechaAlquiler;
        this.telefono = telefono; // Cambiado a String
        this.duracionDias = duracionDias;
    }

    // Getters y setters específicos de AlquilerProducto
    public LocalDate  getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDate  fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public int getTelefono() { // Cambiado a String
        return telefono;
    }

    public void setTelefono(int telefono2) { // Cambiado a String
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

