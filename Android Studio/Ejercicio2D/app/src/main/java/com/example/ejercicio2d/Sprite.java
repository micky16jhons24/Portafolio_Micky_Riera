package com.example.ejercicio2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Sprite extends SurfaceView implements Runnable {
    private Thread hilojuego;
    private boolean ejecutandose;
    private Bitmap fondo;
    private float munnecox = 300;
    private float munnecoy;
    private boolean derecha = false;
    private boolean izquierda = false;
    private float velocidad = 70;
    private Bitmap spriteDerecha, spriteIzquierda;
    private int spriteWidth, spriteHeight;
    private int frame = 0;
    private static final int COLUMNS = 10;
    private static final int ROWS = 3;
    private int[] frameOrderDerecha = {0, 10, 11};
    private int[] frameOrderIzquierda = {9, 19, 18};
    private int currentFrame = 0;
    private long lastFrameTime;
    private int frameRate = 100;
    private boolean mirandoDerecha = true;

    // Variables para Obstáculos
    private ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    private long ultimoObstaculo;
    private int intervaloObstaculos = 2000;
    private Paint pintarObstaculo = new Paint();
    private Random random = new Random();


    // Variables para la puntuación
    private int puntuacion = 0;
    private long tiempoInicio = System.currentTimeMillis();
    private Paint pintarTexto = new Paint();

    public Sprite(Context contexto) {
        super(contexto);
        setFocusable(true);

        spriteDerecha = BitmapFactory.decodeResource(getResources(), R.drawable.zombiederecha);
        spriteIzquierda = BitmapFactory.decodeResource(getResources(), R.drawable.zombieizquierda);
        fondo = BitmapFactory.decodeResource(getResources(), R.drawable.fondonoche);
        spriteWidth = spriteDerecha.getWidth() / COLUMNS;
        spriteHeight = spriteDerecha.getHeight() / ROWS;

        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                munnecoy = getHeight() - 64 - 600;
                startGame();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                stopGame();
            }
        });
    }

    public void startGame() {
        ejecutandose = true;
        hilojuego = new Thread(this);
        hilojuego.start();
    }

    public void stopGame() {
        ejecutandose = false;
        try {
            if (hilojuego != null) {
                hilojuego.join();
                hilojuego = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (ejecutandose) {
            update();
            draw();
            sleep();
        }
    }

    public void update() {
        if (derecha) {
            munnecox += velocidad;
            mirandoDerecha = true;
            animateSprite();
        } else if (izquierda) {
            munnecox -= velocidad;
            mirandoDerecha = false;
            animateSprite();
        }

        if (munnecox < 0) munnecox = 0;
        if (munnecox > getWidth() - 64) munnecox = getWidth() - 64;

        generarObstaculo();
        actualizarObstaculos();
    }


    private void generarObstaculo() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - ultimoObstaculo > intervaloObstaculos) {
            int anchoPantalla = getWidth();

            // Asegurarse de que el ancho de la pantalla es válido
            if (anchoPantalla > 0) {
                int posicionX = random.nextInt(anchoPantalla - 50) + 1000;
                Obstaculo obstaculo = new Obstaculo(getContext(), posicionX);
                obstaculo.setTamano(random.nextInt(50) + 30);
                obstaculo.setVelocidad(random.nextInt(5) + 3);
                obstaculos.add(obstaculo);

                ultimoObstaculo = tiempoActual;
            }
        }
    }


    private void actualizarObstaculos() {
        Iterator<Obstaculo> it = obstaculos.iterator();
        while (it.hasNext()) {
            Obstaculo obstaculo = it.next();
            obstaculo.mover();

            if (obstaculo.colisionaCon(munnecox, munnecoy, spriteWidth, spriteHeight)) {
                ejecutandose = false; // Si colisiona, termina el juego
            }

            if (obstaculo.getY() > getHeight()) {
                it.remove();
                puntuacion = (int) ((System.currentTimeMillis() - tiempoInicio) / 1000);
            }
        }
    }



    private void animateSprite() {
        if (System.currentTimeMillis() - lastFrameTime > frameRate) {
            currentFrame = (currentFrame + 1) % (mirandoDerecha ? frameOrderDerecha.length : frameOrderIzquierda.length);
            lastFrameTime = System.currentTimeMillis();
        }
    }

    public void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas c = getHolder().lockCanvas();
            c.drawColor(Color.BLACK);

            Bitmap fondoEntero = Bitmap.createScaledBitmap(fondo, getWidth(), getHeight(), false);
            c.drawBitmap(fondoEntero, 0, 0, null);

            Paint pintar = new Paint();
            Bitmap spriteActual = mirandoDerecha ? spriteDerecha : spriteIzquierda;

            int spriteIndex = mirandoDerecha ? frameOrderDerecha[currentFrame] : frameOrderIzquierda[currentFrame];
            int srcX = (spriteIndex % COLUMNS) * spriteWidth;
            int srcY = (spriteIndex / COLUMNS) * spriteHeight;

            Rect srcRect = new Rect(srcX, srcY, srcX + spriteWidth, srcY + spriteHeight);
            Rect destRect = new Rect((int) munnecox, (int) munnecoy, (int) munnecox + spriteWidth, (int) munnecoy + spriteHeight);

            c.drawBitmap(spriteActual, srcRect, destRect, pintar);

            // Dibujar los obstáculos
            for (Obstaculo obstaculo : obstaculos) {
                obstaculo.dibujar(c, pintarObstaculo);
            }

            // Dibujar la puntuación en la pantalla
            pintarTexto.setColor(Color.WHITE);
            pintarTexto.setTextSize(50);
            pintarTexto.setAntiAlias(true);
            c.drawText("Puntuación: " + puntuacion, 50, 100, pintarTexto);

            getHolder().unlockCanvasAndPost(c);
        }
    }


    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int llave, KeyEvent evento) {
        if (llave == KeyEvent.KEYCODE_DPAD_RIGHT) {
            derecha = true;
        } else if (llave == KeyEvent.KEYCODE_DPAD_LEFT) {
            izquierda = true;
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int llave, KeyEvent evento) {
        if (llave == KeyEvent.KEYCODE_DPAD_RIGHT) {
            derecha = false;
        } else if (llave == KeyEvent.KEYCODE_DPAD_LEFT) {
            izquierda = false;
        }
        return true;
    }
}
