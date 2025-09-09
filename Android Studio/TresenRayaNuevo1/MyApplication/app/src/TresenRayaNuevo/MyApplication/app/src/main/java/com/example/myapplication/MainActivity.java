package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int cont = 0;  // Contador para llevar los turnos (pares para 'O', impares para 'X')
    Button[][] XyO = new Button[3][3];  // Matriz de botones para el tablero 3x3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Vinculamos la vista (donde están los botones)

        // Inicializamos la matriz con los botones que están en el layout (activity_main.xml)
        XyO[0][0] = findViewById(R.id.B1); //findViewById esto enlaza cada boton con la posicion de la matriz o del boton
        XyO[0][1] = findViewById(R.id.B2);
        XyO[0][2] = findViewById(R.id.B3);
        XyO[1][0] = findViewById(R.id.B4);
        XyO[1][1] = findViewById(R.id.B5);
        XyO[1][2] = findViewById(R.id.B6);
        XyO[2][0] = findViewById(R.id.B7);
        XyO[2][1] = findViewById(R.id.B8);
        XyO[2][2] = findViewById(R.id.B9);
    }

    // Este método se llama cuando se hace clic en un botón (casilla del juego)
    public void clicar(View v) {
        Button button = (Button) v;  // Convertimos el View en un Button
        button.setEnabled(false);  // Desactivamos el botón después de ser presionado

        cont++;  // Incrementamos el contador para alternar turnos

        // Si el turno es par, ponemos "O", si es impar ponemos "X"
        if (cont % 2 == 0) {
            button.setBackgroundColor(Color.GREEN);
            button.setText("O");
        } else {
            button.setBackgroundColor(Color.BLUE);
            button.setText("X");
        }

        // Después de cada clic, verificamos si hay un ganador
        if (verificarGanador()) {
            String ganador = (cont % 2 == 0) ? "O" : "X"; //mira si ha ganado X o O
            finalPartida( "¡Ha ganado " + ganador + "!");//Toast muestra por pantalla si ha ganado
            reiniciarJuego();  // Reiniciamos el juego si hay un ganador

        } else if (cont == 9) {  // Si todos los botones han sido presionados y no hay ganador
            finalPartida("¡Empate!"); //Toast muestra por pantalla si ha empatado
            reiniciarJuego();  // Reiniciamos el juego en caso de empate
        }
    }

    // Método que verifica si hay un ganador
    public boolean verificarGanador() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            // Verificar filas: si todos los botones en una fila tienen el mismo texto y no están vacíos
            //pone text y equal por que hay que verificar el textto
            if (XyO[i][0].getText().equals(XyO[i][1].getText()) &&
                    XyO[i][0].getText().equals(XyO[i][2].getText()) &&
                    !XyO[i][0].getText().toString().isEmpty()) {
                return true;
            }

            // Verificar columnas: si todos los botones en una columna tienen el mismo texto y no están vacíos
            if (XyO[0][i].getText().equals(XyO[1][i].getText()) &&
                    XyO[0][i].getText().equals(XyO[2][i].getText()) &&
                    !XyO[0][i].getText().toString().isEmpty()) {
                return true;
            }
        }

        // Verificar diagonales
        // Diagonal principal: B1 -> B5 -> B9
        if (XyO[0][0].getText().equals(XyO[1][1].getText()) &&
                XyO[0][0].getText().equals(XyO[2][2].getText()) &&
                !XyO[0][0].getText().toString().isEmpty()) {
            return true;
        }

        // Diagonal secundaria: B3 -> B5 -> B7
        if (XyO[0][2].getText().equals(XyO[1][1].getText()) &&
                XyO[0][2].getText().equals(XyO[2][0].getText()) &&
                !XyO[0][2].getText().toString().isEmpty()) {
            return true;
        }

        return false;  // Si no hay ganador, devolvemos false
    }

    // Método para reiniciar el juego después de que alguien gana o hay empate
    public void reiniciarJuego() {
        // Reiniciar el contador
        cont = 0;

        // Limpiar el texto y habilitar los botones de nuevo
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                XyO[i][j].setText("");  // vuelva al botton blanco osea que quita la X o O
                XyO[i][j].setBackgroundColor(Color.LTGRAY);  // Color de fondo original
                XyO[i][j].setEnabled(true);  // Volver a habilitar los botones
            }
        }
    }

    public void finalPartida(String mensaje){
        Intent i=new Intent(this, juegoFin.class);
        i.putExtra("resultado", mensaje);
        startActivity(i);


    }


}
