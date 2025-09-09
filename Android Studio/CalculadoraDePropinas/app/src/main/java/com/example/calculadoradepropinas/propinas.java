package com.example.calculadoradepropinas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class propinas extends AppCompatActivity {

    private Button regresarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propinas);

        // Obtener el mensaje de la actividad anterior
        String mensaje = getIntent().getStringExtra("cambio");

        // Mostrar el mensaje en el TextView
        TextView textView = findViewById(R.id.textosescrito);
        if (mensaje != null) {
            textView.setText(mensaje + "\n");
        }





        // Mostrar un Toast con el mensaje
        Toast.makeText(this, "Mensaje recibido: " + mensaje, Toast.LENGTH_LONG).show();

        // Añadir el botón para regresar a la pantalla anterior
        regresarButton = findViewById(R.id.BReiniciar);
        regresarButton.setOnClickListener(v -> {

            finish(); // Cierra esta actividad para no volver a ella al presionar atrás
        });


    }
}
