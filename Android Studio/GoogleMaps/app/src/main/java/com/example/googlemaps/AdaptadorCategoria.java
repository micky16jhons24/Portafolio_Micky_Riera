package com.example.googlemaps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//el adapter se necarga de mostrar listas y maneja los eventos cuando se hacen click en una categoria

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.VistaCategoria> {

    private List<String> listaCategoria;
    private OnCategoryClickListener listener;

    public AdaptadorCategoria(List<String> categoryList, OnCategoryClickListener listener) {
        this.listaCategoria = categoryList;
        this.listener = listener;
    }
    //crea la vista
    //crea  una instancia de VistaCategoria
    @NonNull
    @Override
    public VistaCategoria onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                //obtiene el layout de la categoria
                .inflate(R.layout.lista_categoria, parent, false);
        return new VistaCategoria(view);
    }
    //asigna los datos
    @Override
    public void onBindViewHolder(@NonNull VistaCategoria holder, int position) {
        String category = listaCategoria.get(position);
        holder.textoCategoria.setText(category);

        holder.itemView.setOnClickListener(v -> listener.alClickarEnCategoria(category));
    }

    //obtiene el tamaño de la lista
    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }
    //crea la vista
    public static class VistaCategoria extends RecyclerView.ViewHolder {
        TextView textoCategoria;
        //Esta clase representa una sola vista de la lista y manteine el texto de la categoria
        public VistaCategoria(View itemView) {
            super(itemView);
            //obtiene el texto de la categoria
            textoCategoria = itemView.findViewById(R.id.tvCategory);
        }
    }

    //interfaz para el click en la categoria
    public interface OnCategoryClickListener {
        void alClickarEnCategoria(String category);
    }
}
