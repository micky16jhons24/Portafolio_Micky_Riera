package com.example.googlemaps;

import android.app.Application;
import java.util.ArrayList;
import java.util.List;

public class MiAplicacion extends Application {
    private List<PuntosDeInteres> listaFavoritos = new ArrayList<>();

    public List<PuntosDeInteres> getListaFavoritos() {

        return listaFavoritos;
    }

    public void agregarFavorito(PuntosDeInteres point) {
        if (!listaFavoritos.contains(point)) {
            listaFavoritos.add(point);
        }
    }
}