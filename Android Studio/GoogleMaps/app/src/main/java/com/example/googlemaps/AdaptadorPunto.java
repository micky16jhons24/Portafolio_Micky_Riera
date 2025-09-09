package com.example.googlemaps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPunto extends RecyclerView.Adapter<AdaptadorPunto.VistaPunto> {

    private List<PuntosDeInteres> listaPuntos;
    private PuntoEnlace listener;
    private EnlaceFavoritos favoritosListener;

    public AdaptadorPunto(List<PuntosDeInteres> pointsList, PuntoEnlace listener, EnlaceFavoritos favoritesListener) {
        this.listaPuntos = pointsList;
        this.listener = listener;
        this.favoritosListener = favoritesListener; // Asegúrate de que este listener se esté configurando.
    }


    //crea un elemto de la lista de puntos de interes
    @NonNull
    @Override
    public VistaPunto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_puntos_interes, parent, false);
        return new VistaPunto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaPunto holder, int position) {
        PuntosDeInteres point = listaPuntos.get(position);
        holder.textoPunto.setText(point.getNombre());
        holder.descripcionTexto.setText(point.getDescripcion());
        holder.horarioTexto.setText(point.getHorario());
        holder.imagen.setImageResource(point.getIdImagen());

        // Al hacer clic en el botón "Mostrar en el mapa", mueve el mapa y agrega el marcador
        holder.botonMostrarEnMapa.setOnClickListener(v -> listener.onPointClick(point));

        // Al hacer clic en el botón "Agregar a favoritos", agrega el punto de interés a los favoritos
        holder.botonAgregarFavoritos.setOnClickListener(v -> favoritosListener.onAddToFavorites(point));
    }

    @Override
    public int getItemCount() {
        return listaPuntos.size();
    }

    public static class VistaPunto extends RecyclerView.ViewHolder {
        TextView textoPunto, descripcionTexto, horarioTexto;
        ImageView imagen;
        Button botonMostrarEnMapa, botonAgregarFavoritos;

        public VistaPunto(View itemView) {
            super(itemView);
            textoPunto = itemView.findViewById(R.id.tvPoint);
            descripcionTexto = itemView.findViewById(R.id.tvDescription);
            horarioTexto = itemView.findViewById(R.id.tvSchedule);
            imagen = itemView.findViewById(R.id.ivImage);
            botonMostrarEnMapa = itemView.findViewById(R.id.btnShowOnMap);
            botonAgregarFavoritos = itemView.findViewById(R.id.btnAddToFavorites);
        }
    }

    public interface PuntoEnlace {
        void onPointClick(PuntosDeInteres punto);
    }

    public interface EnlaceFavoritos {
        void onAddToFavorites(PuntosDeInteres punto);
    }
}
