package com.example.ejercicio2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class Obstaculo {
    private Bitmap obstaculo;
    private float x, y;
    private float velocidad = 100; // Velocidad de caída
    private int ancho, alto;

    public Obstaculo(Context context, int screenWidth) {
        obstaculo = BitmapFactory.decodeResource(context.getResources(), R.drawable.obstaculo);

        // Verificar que la imagen se haya cargado correctamente
        if (obstaculo == null) {
            throw new RuntimeException("Error: No se pudo cargar la imagen del obstáculo.");
        }

        ancho = obstaculo.getWidth();
        alto = obstaculo.getHeight();

        // Asegurar que screenWidth es válido
        if (screenWidth <= 0) {
            screenWidth = 1080;  // Valor por defecto
        }

        Random random = new Random();
        int maxX = Math.max(1, screenWidth - ancho);  // Evitar valores negativos
        x = random.nextInt(maxX);
        y = 0;
    }

    public void mover() {
        y += velocidad;
    }

    public void dibujar(Canvas canvas, Paint paint) {
        canvas.drawBitmap(obstaculo, x, y, paint);
    }

    public boolean colisionaCon(float px, float py, int pAncho, int pAlto) {
        return (x + ancho / 2 > px && x < px + pAncho) && (y + alto / 2 > py && y < py + pAlto);
    }

    public boolean fueraDePantalla(int screenHeight) {
        return y > screenHeight;
    }

    public float getY() {
        return y;
    }

    public void setTamano(int nuevoTamano) {
        if (obstaculo != null) {
            obstaculo = Bitmap.createScaledBitmap(obstaculo, nuevoTamano, nuevoTamano, false);
            ancho = obstaculo.getWidth();
            alto = obstaculo.getHeight();
        }
    }

    public void setVelocidad(int nuevaVelocidad) {
        velocidad = nuevaVelocidad;
    }

}