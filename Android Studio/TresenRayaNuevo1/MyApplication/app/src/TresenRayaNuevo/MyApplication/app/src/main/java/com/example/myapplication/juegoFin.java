package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class juegoFin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_juego_fin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent= getIntent();
        String mensaje= intent.getStringExtra("resultado");


        TextView textoResultado= findViewById(R.id.textView);

        textoResultado.setText(mensaje);

        Button regresarButton = findViewById(R.id.BReiniciar);
        regresarButton.setOnClickListener(v -> {
            Intent l = new Intent(juegoFin.this, MainActivity.class);
            startActivity(l);
            finish(); // Opcional: para cerrar MainActivity2 y no volver a ella al presionar atrás
        });

    }
}