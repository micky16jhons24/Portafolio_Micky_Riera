package com.example.googlemaps;

public class PuntosDeInteres {

    private String nombre;
    private String descripcion;
    private String horario;
    private int idImagen;
    private double latitud;
    private double longitud;

    public PuntosDeInteres(String name, String description, String schedule, int imageResId, double latitude, double longitude) {
        this.nombre = name;
        this.descripcion = description;
        this.horario = schedule;
        this.idImagen = imageResId;
        this.latitud = latitude;
        this.longitud = longitude;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
