package com.example.preferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager; // Importación necesaria para PreferenceManager

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button Boton1 = findViewById(R.id.button);
        Boton1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Preferencias.class);
            startActivity(intent);
        });

        Button Boton2 = findViewById(R.id.button2);
        Boton2.setOnClickListener(view -> {
            recuperarPreferencias();
        });
    }

    // Método para recuperar las preferencias
    private void recuperarPreferencias() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean unicoSO = preferences.getBoolean("desactivar_unico_SO", false);
        Log.i("Preferencias", "Es un Unico S.O: " + unicoSO);

        String sistemaOperativo = preferences.getString("sistema_operativo", "No definido");
        Log.i("Preferencias", "Sistema Operativo elegido: " + sistemaOperativo);

        String versionSistema = preferences.getString("version_sistema", "No definido");
        Log.i("Preferencias", "Version del Sisitema Operativo(S.O) : " + versionSistema);
    }
}
