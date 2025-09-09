package com.example.notificaciones;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CANAL_PIO = "canalpordefecto";
    private PendingIntent pendingIntent;

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

        // Crear Intent para abrir la actividad cuando activemos la notificación
        Intent intent = new Intent(this, NotificacionActividad.class);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Crear canal de notificaciones para Android 8+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal por Defecto";
            String description = "Canal para comunicaciones normales";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CANAL_PIO, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Configurar botón para activar la notificación
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::showNotification);
    }

    public void showNotification(View view) {
        // Construir la notificación
        NotificationCompat.Builder builderNotificacion = new NotificationCompat.Builder(this, CANAL_PIO)
                .setSmallIcon(R.drawable.setp_out) // Icono predeterminado
                .setContentTitle("Pio Baroja Notification")
                .setContentText("Este es un ejemplo de notificación en Android. Pio pio")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // Intent para abrir la actividad
                .setAutoCancel(true);

        // Enviar la notificación
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, builderNotificacion.build());
    }
}
