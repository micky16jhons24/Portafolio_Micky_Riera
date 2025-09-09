package com.example.googlemaps;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class FavoritosFragment extends Fragment {

    private RecyclerView favoritesRecyclerView;
    private AdaptadorPunto pointsAdapter;
    private List<PuntosDeInteres> listaFavoritos;

    public FavoritosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favoritos, container, false);

        favoritesRecyclerView = view.findViewById(R.id.favoritosRecycled);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtener la lista de favoritos desde la actividad principal
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            listaFavoritos = mainActivity.getFavoritesList();
        }

        // Configurar el adaptador
        pointsAdapter = new AdaptadorPunto(listaFavoritos, point -> {
            // Acción al hacer clic en un favorito
            LatLng location = new LatLng(point.getLatitud(), point.getLongitud());
            if (mainActivity != null) {
                mainActivity.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                mainActivity.getMap().addMarker(new MarkerOptions().position(location).title(point.getNombre()));
                mainActivity.onBackPressed(); // Volver a la actividad principal
            }
        }, null);

        favoritesRecyclerView.setAdapter(pointsAdapter);

        return view;
    }
}