package com.example.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PrincipalFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);

        ImageButton botonFavoritos = view.findViewById(R.id.botonFavoritos);
        ImageButton botonAjustes = view.findViewById(R.id.botonAjustes);
        return view;
    }

    public void onClickFavoritos(View view) {
        Intent intent = new Intent(getActivity(), FavoritosFragment.class);
        startActivity(intent);
    }

    public void onClickAjustes(View view) {
        Intent intent = new Intent(getActivity(), AjustesFragment.class);
        startActivity(intent);
    }
}
