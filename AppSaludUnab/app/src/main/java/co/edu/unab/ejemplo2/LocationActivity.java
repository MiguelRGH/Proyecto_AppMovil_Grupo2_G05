package co.edu.unab.ejemplo2;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.repository.LocalizacionRepository;
import co.edu.unab.ejemplo2.repository.LocalizacionRepositoryImpl;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private String latitud;
    private String longitud;
    Marker marker;
    private String emailDesdeFireBase;
    private ArrayList<LatLng> puntosLocalizacionesUser;
    LocalizacionRepository repository;
    //private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

       // mDatabase = FirebaseDatabase.getInstance().getReference();

        puntosLocalizacionesUser = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Bundle recibeDatos = getIntent().getExtras();
        if (recibeDatos != null) {
            emailDesdeFireBase = recibeDatos.getString("email");

        } else {
            if (user != null) {
                emailDesdeFireBase = user.getEmail();

            }
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Button aceptarCoordenadas = (Button) findViewById(R.id.btnAceptarCoordenadas);
        aceptarCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (marker.getPosition() != null) {
                    latitud = marker.getPosition().latitude + "";
                    longitud = marker.getPosition().longitude + "";
                }
                Toast.makeText(LocationActivity.this, "Latitud: " + latitud + " Longitud: " + longitud, Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.setMinZoomPreference(10f);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                LocationActivity.this.marker = marker;

            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }
        });

        try {

            repository = new LocalizacionRepositoryImpl();
            repository.findAll(new Callback() {
                @Override
                public void onSuccess(Object object) {

                    QuerySnapshot objectSnapshot = (QuerySnapshot) object;

                    int tamaño = objectSnapshot.size();


                    for (int i = 0; i < tamaño; i++) {
                        if (objectSnapshot.getDocuments().get(i).getString("correo").equals(emailDesdeFireBase)) {
                            Object latitud = objectSnapshot.getDocuments().get(i).get("latitud");
                            double latitudDouble = (double) latitud;
                            Object longitud = objectSnapshot.getDocuments().get(i).get("longitud");
                            double longitudDouble = (double) longitud;
                            LatLng punto = new LatLng(latitudDouble, longitudDouble);
                            marker = mMap.addMarker(new MarkerOptions().draggable(true).position(punto).title("Posicion"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(punto));


                        }
                    }

                }


                @Override
                public void onFailure(Object object) {

                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}