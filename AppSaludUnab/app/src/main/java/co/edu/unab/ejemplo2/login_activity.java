package co.edu.unab.ejemplo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import co.edu.unab.ejemplo2.model.Localizacion;
import co.edu.unab.ejemplo2.repository.LocalizacionRepository;
import co.edu.unab.ejemplo2.repository.LocalizacionRepositoryImpl;

public class login_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final String TAG = "Logs Autenticacion";
    private final int RC_SIGN_IN = 1;
    private String emailUser;
    private String passwordUser;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng punto;
    LocalizacionRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle recibeDatos = getIntent().getExtras();
        if (recibeDatos != null) {
            emailUser = recibeDatos.getString("email");
            passwordUser = recibeDatos.getString("clave");

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            punto = new LatLng(location.getLatitude(), location.getLongitude());

                        }
                    }
                });

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.request_google))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        Button loginGoogle = (Button) findViewById(R.id.btnInicioSesionGoogle);
        Button cerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);
        Button loginClave = (Button) findViewById(R.id.btnIniciarSesion);
        Button registro = (Button) findViewById(R.id.btnRegistrarme);
        EditText email = (EditText) findViewById(R.id.etUserEmail);
        EditText password = (EditText) findViewById(R.id.etUserClave);

        email.setText(emailUser);
        password.setText(passwordUser);

        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseSignOut();
            }
        });

        loginClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTexto = email.getText().toString();
                String passwordTexto = password.getText().toString();
                SignWithFirebase(emailTexto, passwordTexto);

            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(login_activity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void SignWithFirebase(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(login_activity.this, "Autenticación CORRECTA con clave en Firebase y el arroba esta en posicion No. ", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();


                            if (email.equals("bienestar@unab.edu.co")) {
                                Intent intent = new Intent(login_activity.this, ReportAllActivity.class);
                                startActivity(intent);

                            } else {
                                Localizacion localizacion = new Localizacion(email, punto.latitude, punto.longitude);

                                repository = new LocalizacionRepositoryImpl();

                                repository.createWithoutID(localizacion, new Callback() {
                                    @Override
                                    public void onSuccess(Object object) {
                                        Toast.makeText(login_activity.this, "Localizacion creada en FB", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onFailure(Object object) {

                                    }
                                });

                                Intent intent = new Intent(login_activity.this, CalculateImc.class);
                                startActivity(intent);
                            }

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login_activity.this, "Autenticación INCORRECTA con clave en Firebase", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                Toast.makeText(this, "Autenticación CORRECTA en Google", Toast.LENGTH_SHORT).show();
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Autenticación INCORRECTA de Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseSignOut() {
        mAuth.signOut();
        Toast.makeText(login_activity.this, "Sesion cerrada correctamente", Toast.LENGTH_SHORT).show();
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(login_activity.this, "Autenticación CORRECTA en Firebase", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(login_activity.this, "Autenticación INCORRECTA de Firebase", Toast.LENGTH_SHORT).show();
                            //Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        //...
                    }
                });
    }
}