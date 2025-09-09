package com.example.ahorcadomicky;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Ahorcado extends View {
    private Paint Pintar;
    private int intentosFallidos;

    public Ahorcado(Context context, AttributeSet attrs) {
        super(context, attrs);
        Pintar = new Paint();
        Pintar.setColor(Color.BLACK);
        Pintar.setStrokeWidth(10);
        intentosFallidos = 0;
    }

    public void setIntentosFallidos(int attempts) {
        intentosFallidos = attempts;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseX = getWidth() / 2 - 100;  // Coordenada X de la base centrada
        int baseY = getHeight() / 2 + 100;  // Coordenada Y de la base centrada

        // aqui centramos el muñeco
        canvas.drawLine(baseX, baseY, baseX + 200, baseY, Pintar);  // Base
        canvas.drawLine(baseX + 100, baseY - 300, baseX + 100, baseY, Pintar);  // Poste vertical
        canvas.drawLine(baseX + 100, baseY - 300, baseX + 250, baseY - 300, Pintar);  // Poste horizontal
        canvas.drawLine(baseX + 250, baseY - 300, baseX + 250, baseY - 250, Pintar);  // Cuerda

        // Aqui dibujamos el muñeoco segun los intentos que hayamos intentado
        if (intentosFallidos > 0) canvas.drawCircle(baseX + 250, baseY - 220, 30, Pintar);  // Cabeza
        if (intentosFallidos > 1)
            canvas.drawLine(baseX + 250, baseY - 190, baseX + 250, baseY - 100, Pintar);  // Cuerpo
        if (intentosFallidos > 2)
            canvas.drawLine(baseX + 250, baseY - 170, baseX + 200, baseY - 140, Pintar);  // Brazo izquierdo
        if (intentosFallidos > 3)
            canvas.drawLine(baseX + 250, baseY - 170, baseX + 300, baseY - 140, Pintar);  // Brazo derecho
        if (intentosFallidos > 4)
            canvas.drawLine(baseX + 250, baseY - 100, baseX + 200, baseY - 50, Pintar);  // Pierna izquierda
        if (intentosFallidos > 5)
            canvas.drawLine(baseX + 250, baseY - 100, baseX + 300, baseY - 50, Pintar);  // Pierna derecha
    }
}