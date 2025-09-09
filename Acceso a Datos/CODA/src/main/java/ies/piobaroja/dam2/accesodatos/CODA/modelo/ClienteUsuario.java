package ies.piobaroja.dam2.accesodatos.CODA.modelo;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "cliente")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"codigo", "dni", "nombre", "apellido", "telefono", "correo", "codigoPostal"})
public class ClienteUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int contador = 1; // Contador para generar códigos únicos

    @XmlElement
    private int codigo;

    @XmlElement
    private String dni;

    @XmlElement
    private String nombre;

    @XmlElement
    private String apellido;

    @XmlElement
    private String telefono;

    @XmlElement
    private String correo;

    @XmlElement
    private String codigoPostal;

    // Constructor vacío necesario para JAXB
    public ClienteUsuario() {
        this.codigo = contador++; // Generación automática del código
    }

    // Constructor con parámetros
    public ClienteUsuario(String dni, String nombre, String apellido, String telefono, String correo, String codigoPostal) {
        this(); // Llama al constructor vacío para inicializar el código
        setDni(dni); // Uso de setter para aplicar validación
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
        setCodigoPostal(codigoPostal);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni.matches("\\d{8}[A-Za-z]")) { 
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("El DNI debe tener 8 números y una letra mayúscula.");
        }
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
        if (telefono.matches("\\d{9}")) {  
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono debe tener 9 dígitos.");
        }
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
        return "ClienteUsuario [codigo=" + codigo + ", dni=" + dni + ", nombre=" + nombre
                + ", apellido=" + apellido + ", telefono=" + telefono + ", correo=" + correo
                + ", codigoPostal=" + codigoPostal + "]";
    }
}
