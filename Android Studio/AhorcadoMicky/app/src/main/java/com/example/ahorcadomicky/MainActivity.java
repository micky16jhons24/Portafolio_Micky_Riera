package com.example.ahorcadomicky;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView guiones;
    private EditText caja;
    private Button adivinar;
    private Button caja2;
    private Button botonResetear;
    private TextView intentos;
    private TextView Rojo;
    private EditText IngresaLetra;
    private Ahorcado ahorcado;

    private String adivinarPalabra = "";
    private String mostrarvisualizacion = "";
    private int vidas = 6;
    private Set<Character> Letraadivinada = new HashSet<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IngresaLetra = findViewById(R.id.etWordToGuess);
        caja2 = findViewById(R.id.btnStartGame);
        botonResetear = findViewById(R.id.btnRestartGame);
        guiones = findViewById(R.id.tvWord);
        caja = findViewById(R.id.etLetter);
        adivinar = findViewById(R.id.btnGuess);
        intentos = findViewById(R.id.tvAttempts);
        Rojo = findViewById(R.id.tvStatus);
        ahorcado = findViewById(R.id.hangmanView);

        ahorcado.setVisibility(View.GONE);
        botonResetear.setVisibility(View.GONE);

        caja2.setOnClickListener(v -> EmpezarJuego());

        adivinar.setOnClickListener(v -> adivinaza());

        botonResetear.setOnClickListener(v -> restear());
    }

    private void EmpezarJuego() {
        adivinarPalabra = IngresaLetra.getText().toString().toUpperCase().trim();
        if (adivinarPalabra.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese una palabra para comenzar el juego", Toast.LENGTH_SHORT).show();
            return;
        }

        mostrarvisualizacion = "_".repeat(adivinarPalabra.length());
        guiones.setText(mostrarvisualizacion);
        //aqui configuro la visualizacion y la habilitacion de los componentes del ahorcado
        IngresaLetra.setVisibility(View.GONE);
        caja2.setVisibility(View.GONE);
        guiones.setVisibility(View.VISIBLE);
        caja.setVisibility(View.VISIBLE);
        caja.setEnabled(true); // Habilitar el campo de letra
        adivinar.setVisibility(View.VISIBLE);
        adivinar.setEnabled(true); // Habilitar el botón de adivinar
        intentos.setVisibility(View.VISIBLE);
        Rojo.setVisibility(View.GONE);
        ahorcado.setVisibility(View.VISIBLE);
        botonResetear.setVisibility(View.VISIBLE);

        vidas = 6;
        Letraadivinada.clear();
        intentos.setText("Intentos restantes: " + vidas);
        ahorcado.setIntentosFallidos(0);
        Rojo.setText("");
    }

    private void adivinaza() {
        String letter = caja.getText().toString().toUpperCase();
        if (!letter.isEmpty()) {
            char guessedChar = letter.charAt(0);

            if (Letraadivinada.contains(guessedChar)) {
                caja.setError("Ya has adivinado esa letra");
                return;
            }

            Letraadivinada.add(guessedChar);

            if (adivinarPalabra.contains(String.valueOf(guessedChar))) {
                actualizarPalabra(guessedChar);
            } else {
                vidas--;
                intentos.setText("Intentos restantes: " + vidas);
                ahorcado.setIntentosFallidos(6 - vidas);
            }

            caja.getText().clear();
            ComprobarJuego();
        }
    }

    private void actualizarPalabra(char guessedChar) {
        StringBuilder updatedWord = new StringBuilder(mostrarvisualizacion);
        for (int i = 0; i < adivinarPalabra.length(); i++) {
            if (adivinarPalabra.charAt(i) == guessedChar) {
                updatedWord.setCharAt(i, guessedChar);
            }
        }
        mostrarvisualizacion = updatedWord.toString();
        guiones.setText(mostrarvisualizacion);
    }

    private void ComprobarJuego() {
        if (mostrarvisualizacion.equals(adivinarPalabra)) {
            Rojo.setText("¡Felicidades! Has ganado.");
            Rojo.setVisibility(View.VISIBLE);
            deshabilitarjuego();
        } else if (vidas == 0) {
            Rojo.setText("Has perdido. La palabra era: " + adivinarPalabra);
            Rojo.setVisibility(View.VISIBLE);
            deshabilitarjuego();
        }
    }

    private void deshabilitarjuego() {
        adivinar.setEnabled(false);
        caja.setEnabled(false);
    }

    private void restear() {
        // Resetear juego
        IngresaLetra.setText("");
        IngresaLetra.setVisibility(View.VISIBLE);
        caja2.setVisibility(View.VISIBLE);
        guiones.setVisibility(View.GONE);
        caja.setVisibility(View.GONE);
        caja.setEnabled(true);  // Habilitar el campo de letra
        adivinar.setVisibility(View.GONE);
        adivinar.setEnabled(true);  // Habilitar el botón de adivinar
        intentos.setVisibility(View.GONE);
        Rojo.setVisibility(View.GONE);
        ahorcado.setVisibility(View.GONE);
        botonResetear.setVisibility(View.GONE);

        // Reiniciar el juego
        vidas = 6;
        Letraadivinada.clear();
        ahorcado.setIntentosFallidos(0);
        mostrarvisualizacion = "";
        adivinarPalabra = "";
    }
}
