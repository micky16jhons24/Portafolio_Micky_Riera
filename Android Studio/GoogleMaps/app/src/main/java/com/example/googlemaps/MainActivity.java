package com.example.googlemaps;


import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    // Constante para el código de solicitud de permiso POST_NOTIFICATIONS
    private static final int REQUEST_CODE_POST_NOTIFICATIONS = 1;
    private RecyclerView categoriaRecycledView;
    private static final String CHANNEL_ID = "gps_channel";
    private LinearLayout detallesLayout;
    private RecyclerView puntosRecycledView;
    private GoogleMap mapa;
    private Button botonAnnadirFavoritos;
    private List<PuntosDeInteres> listaFavoritos = new ArrayList<>();
    private PuntosDeInteres puntoSeleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa las vistas
        categoriaRecycledView = findViewById(R.id.reciclo24);
        detallesLayout = findViewById(R.id.detailsLayout);
        puntosRecycledView = findViewById(R.id.pointsRecyclerView);
        botonAnnadirFavoritos = findViewById(R.id.addFavoriteButton);

        // Configuramos el RecyclerView de las categorías , organiza los elementos en una lista de desplazamiento vertical u horizontal
        // Establece el adaptador personalizado para el RecyclerView
        categoriaRecycledView.setLayoutManager(new LinearLayoutManager(this));

        // Obtiene el fragmento del mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Datos de categorías
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Restaurantes");
        categoryList.add("Museos");
        categoryList.add("Parques");
        categoryList.add("Plazas");


        // Crea un adaptador personalizado para las categorías
        AdaptadorCategoria categoryAdapter = new AdaptadorCategoria(categoryList, category -> {
            showCategoryDetails(category);
        });
        // Establece el adaptador en el RecyclerView
        categoriaRecycledView.setAdapter(categoryAdapter);

        // Acción al hacer clic en el botón de "Agregar a favoritos", muestra el dialogo por si guardar a favoritos
        // Si no hay un punto seleccionado, no hace nada
        botonAnnadirFavoritos.setOnClickListener(v -> {
            if (puntoSeleccionado != null) {
                showFavoriteDialog(puntoSeleccionado);
            }
        });

        // Añadir el PrincipalFragment al contenedor , Fragmento
        // Si no hay ningún fragmento en el contenedor, lo agrega
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new PrincipalFragment());
            transaction.commit();
        }

        // Solicitar el permiso POST_NOTIFICATIONS en tiempo de ejecución , muestra el cuadro de dialogo de NTY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
            }
        }


        // Verificar si el GPS está habilitado al iniciar la aplicación
        // Si no está habilitado, muestra el cuadro de diálogo
        if (!isGPSEnabled()) {
            AvisoAlertaDialogo();
        }
        createNotificationChannel();
    }


    //verifica si tiene permisos de GPS
    //muestra una notificacion
    private void showGPSDisabledNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        // Crea una notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("GPS Deshabilitado")
                .setContentText("El GPS está deshabilitado. Por favor, habilítelo para usar todas las funciones.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //con esto muestra la notificaion de que esta desactuvado el GPS
        notificationManager.notify(1, builder.build());
    }

    //crea el canal de notificacion
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "GPS Channel";
            String description = "Notificaciones para el estado del GPS";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);


            // Obtiene el servicio NotificationManager del sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            // Obtiene el servicio NotificationManager del sistema
            // Crea el canal de notificación en el sistema
            notificationManager.createNotificationChannel(channel);
        }
    }
    //obtiene el mapa
    public GoogleMap getMap() {
        return mapa;
    }
    //obtiene la lista de favoritos
    public List<PuntosDeInteres> getFavoritesList() {
        return listaFavoritos;
    }
    //muestra los detalles de la categoria
    private void showCategoryDetails(String category) {
        detallesLayout.setVisibility(View.VISIBLE);
        TextView categoryTitle = findViewById(R.id.categoryTitle);
        categoryTitle.setText("Puntos de interés en " + category);

        List<PuntosDeInteres> pointsList = new ArrayList<>();
        if ("Restaurantes".equals(category)) {
            pointsList.add(new PuntosDeInteres("Restaurante La Vasija", "Comida tradicional Ecuatoriana.", "Horario: 12:00 - 23:00", R.drawable.restaurante_vasija, 40.4168, -3.7038));
        } else if ("Museos".equals(category)) {
            pointsList.add(new PuntosDeInteres("Museo del Prado", "Uno de los museos más famosos del mundo.", "Horario: 10:00 - 20:00", R.drawable.museo_prado, 40.4138, -3.6921));
        } else if ("Parques".equals(category)) {
            pointsList.add(new PuntosDeInteres("Parque El Retiro", "Un hermoso parque en el corazón de Madrid.", "Abierto todo el día", R.drawable.parque_retiro, 40.4154, -3.6840));
        } else if ("Plazas".equals(category)) {
            pointsList.add(new PuntosDeInteres("Plaza Mayor", "Una de las plazas más emblemáticas de Madrid.", "Abierta todo el día", R.drawable.plaza_mayor, 40.4153, -3.7074));
        }
        // Configura el RecyclerView para mostrar los puntos de interés
        puntosRecycledView.setLayoutManager(new LinearLayoutManager(this));
        // Crea un adaptador personalizado para los puntos de interés
        AdaptadorPunto pointsAdapter = new AdaptadorPunto(pointsList, point -> {
            // Muestra el marcador en el mapa
            LatLng location = new LatLng(point.getLatitud(), point.getLongitud());
            // Centra la cámara en la ubicación del punto de interés
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            // Añade un marcador al mapa
            mapa.addMarker(new MarkerOptions().position(location).title(point.getNombre()));
            // Guarda el punto de interés seleccionado
            puntoSeleccionado = point;
            // Muestra el botón de "Agregar a favoritos"
            botonAnnadirFavoritos.setVisibility(View.VISIBLE);
        }, point -> showFavoriteDialog(point));
        // Establece el adaptador en el RecyclerView
        puntosRecycledView.setAdapter(pointsAdapter);
    }
    //cuadro de dialogo
    private void showFavoriteDialog(final PuntosDeInteres point) {
        new AlertDialog.Builder(this)
                .setTitle("Agregar a favoritos")
                .setMessage("¿Deseas agregar este punto de interés a tus favoritos?")
                .setPositiveButton("Sí", (dialog, which) -> agregarAFavoritos(point))
                .setNegativeButton("No", null)
                .show();
    }

    //agrega a favoritos
    private void agregarAFavoritos(PuntosDeInteres point) {
        ((MiAplicacion) getApplication()).agregarFavorito(point);
        Toast.makeText(this, "Punto agregado a favoritos", Toast.LENGTH_SHORT).show();
    }
    //opciones del menu estrella y ajustes
    public void onClickFavoritos(View view) {
        Intent intent = new Intent(this, FavoritosActivity.class);
        startActivity(intent);
    }
    //opciones del menu estrella y ajustes
    public void onClickAjustes(View view) {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }
    //opciones del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
//opciones del menu estrella y ajustes
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opcion_favoritos) {
            Intent intent = new Intent(this, FavoritosActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.opcion_ajustes) {
            Intent intent = new Intent(this, AjustesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //cuadro de dialogo
    private void AvisoAlertaDialogo() {
        new AlertDialog.Builder(this)
                .setTitle("GPS Deshabilitado")
                .setMessage("El GPS está deshabilitado. Por favor, actívelo para un mejor funcionamiento de la aplicación.")
                .setCancelable(false)
                .setPositiveButton("Activar GPS", new  DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    //configuracion del mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        if (!isGPSEnabled()) {
            AvisoAlertaDialogo(); // Muestra el cuadro de diálogo
            showGPSDisabledNotification(); // Muestra una notificación
        } else {
            LatLng location = new LatLng(40.7128, -74.0060); // Nueva York
            mapa.addMarker(new MarkerOptions().position(location).title("Marker in NYC"));
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
        }
    }
    //verifica si esta habilitado
    private boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


}