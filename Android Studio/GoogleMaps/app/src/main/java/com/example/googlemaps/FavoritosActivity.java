package com.example.googlemaps;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class FavoritosActivity extends AppCompatActivity {
    private RecyclerView favoritosRecycled;
    private AdaptadorPunto puntoAdaptador;
    private List<PuntosDeInteres> favoritosLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        favoritosRecycled = findViewById(R.id.favoritosRecycled);
        favoritosRecycled.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista de favoritos desde la aplicación
        favoritosLista = ((MiAplicacion) getApplication()).getListaFavoritos();

        // Configurar el adaptador
        puntoAdaptador = new AdaptadorPunto(favoritosLista, point -> {
            // Acción al hacer clic en un favorito
            LatLng location = new LatLng(point.getLatitud(), point.getLongitud());
            // Navegar al punto en el mapa
            // Aquí puedes definir la lógica para volver a la actividad principal si es necesario
        }, null);

        favoritosRecycled.setAdapter(puntoAdaptador);
    }
}