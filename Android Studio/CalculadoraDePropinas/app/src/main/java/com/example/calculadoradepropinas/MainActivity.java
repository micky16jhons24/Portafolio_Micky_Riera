package com.example.calculadoradepropinas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button regresarButton;
    private RadioButton rb10, rb15, rb20;
    private EditText datoCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regresarButton = findViewById(R.id.button);

        rb10 = findViewById(R.id.radioButton);
        rb15 = findViewById(R.id.radioButton3);
        rb20 = findViewById(R.id.radioButton4);
        datoCalcular = findViewById(R.id.editTextText);

    }

    ;


    public void clicar(View v) {

        String cobro = datoCalcular.getText().toString();

        if (cobro.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un monto válido", Toast.LENGTH_SHORT).show();
            return;
        }
            // double cobroComvertido= Double.parseDouble(cobro);

            //  double porcentaje10=(cobroComvertido * 10)/100;
            //  double porcentaje15=(cobroComvertido * 15)/100;
            //  double porcentaje20=(cobroComvertido * 20)/100;

            double cobrodecimas;
            try {
                cobrodecimas = Double.parseDouble(cobro);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "El monto ingresado no es válido", Toast.LENGTH_SHORT).show();
                return;
            }
            double propina = 0;

            if (rb10.isChecked()) {
                propina = cobrodecimas * 0.10;
            } else if (rb15.isChecked()) {
                propina = cobrodecimas * 0.15;

            } else if (rb20.isChecked()) {
                propina = cobrodecimas * 0.20;

            } else {
                Toast.makeText(this, "Por favor, seleccione un porcentaje de propina", Toast.LENGTH_SHORT).show();
                return;
            }

            double totalCONpropinas = cobrodecimas + propina;

            String mensaje = "mmonton: " + cobrodecimas + "\nPropina: " + propina + "\nTotal: " + totalCONpropinas;

            // Cambiar a la siguiente pantalla
            CAMBIARPANTALLA("\nmensaje: " + mensaje);


        }


        private void CAMBIARPANTALLA (String mensaje){
            // Crear el Intent para abrir pantalla2
            Intent i = new Intent(this, propinas.class);
            i.putExtra("cambio", mensaje);
            startActivity(i);
            Toast.makeText(this, "cambio de pantalla: ", Toast.LENGTH_LONG).show();

            // Actualizar las estadísticas al cambiar de pantalla
        }

    }




