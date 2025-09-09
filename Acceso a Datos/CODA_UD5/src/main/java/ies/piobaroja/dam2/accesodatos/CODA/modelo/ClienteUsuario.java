package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteUsuario implements Serializable {
    private static final long serialVersionUID = 1L;


    private String dni;

    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String codigoPostal;

    private List<Producto> productos = new ArrayList<>();

    public ClienteUsuario() {}

    public ClienteUsuario(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.codigoPostal = codigoPostal;
    }

    public void addProducto(Producto producto) {
        if (!productos.contains(producto)) {
            productos.add(producto);
            producto.setCliente(this);
        }
    }

    public void removeProducto(Producto producto) {
        if (productos.contains(producto)) {
            productos.remove(producto);
            producto.setCliente(null);
        }
    }

    // Getters y Setters...
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
 


    @Override
    public String toString() {
        return "ClienteUsuario [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + 
               ", telefono=" + telefono + ", correo=" + correo + ", codigoPostal=" + codigoPostal + "]";
    }

}

