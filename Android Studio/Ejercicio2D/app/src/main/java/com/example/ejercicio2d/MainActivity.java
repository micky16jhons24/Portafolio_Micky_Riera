package com.example.ejercicio2d;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Sprite juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "Iniciando el juego");

        juego = new Sprite(this);
        if (juego == null) {
            Log.e("MainActivity", "Error: No se pudo crear el Sprite.");
        } else {
            Log.d("MainActivity", "Juego creado correctamente");
        }

        setContentView(juego);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (juego != null) {
            juego.stopGame();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (juego != null) {
            juego.startGame();
        }
    }
}
