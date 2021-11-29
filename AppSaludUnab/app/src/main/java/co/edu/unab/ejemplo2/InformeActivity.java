package co.edu.unab.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.model.Informe;
import co.edu.unab.ejemplo2.repository.InformeRepository;
import co.edu.unab.ejemplo2.repository.InformeRepositoryImpl;

public class InformeActivity extends AppCompatActivity {


    InformeRepository repository;
    private ArrayList<Informe> infoPeople = new ArrayList<Informe>();
    private boolean identifierActivity;
    private String emailDesdeFireBase;
    ArrayList<String> listaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Bundle recibeDatos = getIntent().getExtras();
        if (recibeDatos != null) {
            emailDesdeFireBase = recibeDatos.getString("email");
            identifierActivity = false;
        } else {
            if (user != null) {
                emailDesdeFireBase = user.getEmail();
                identifierActivity = true;
            }
        }

        Button regresar = (Button) findViewById(R.id.btnRegresarInforme);
        Button salir = (Button) findViewById(R.id.btnSalir);


        try {
            repository = new InformeRepositoryImpl();
            repository.findAll(new Callback() {
                @Override
                public void onSuccess(Object object) {

                    QuerySnapshot objectSnapshot = (QuerySnapshot) object;

                    int tamaño = objectSnapshot.size();

                    for (int i = 0; i < tamaño; i++) {
                        if (objectSnapshot.getDocuments().get(i).getString("email").equals(emailDesdeFireBase)) {

                            String email = objectSnapshot.getDocuments().get(i).getString("email");
                            String valorEdad = objectSnapshot.getDocuments().get(i).getString("edad");
                            String valorPeso = objectSnapshot.getDocuments().get(i).getString("peso");
                            String valorAltura = objectSnapshot.getDocuments().get(i).getString("altura");

                            Object resultadoImc = objectSnapshot.getDocuments().get(i).get("imc");
                            double resultadoIMCDouble = (double) resultadoImc;
                            float resultadoIMCFloat = (float) resultadoIMCDouble;


                            Object basalMetabolism = objectSnapshot.getDocuments().get(i).get("m_basal");
                            double basalMetabolismDouble = (double) basalMetabolism;
                            float basalMetabolismoFloat = (float) basalMetabolismDouble;

                            Object estadoSalud = objectSnapshot.getDocuments().get(i).get("estadoSalud");
                            int estadoSaludInt = Integer.parseInt(estadoSalud.toString());

                            String date = objectSnapshot.getDocuments().get(i).getString("fecha");
                            Object generoFemenino = objectSnapshot.getDocuments().get(i).get("genero");
                            boolean generoFemBoolean = (Boolean) generoFemenino;
                            infoPeople.add(new Informe(email, valorEdad, valorPeso, valorAltura, resultadoIMCFloat, basalMetabolismoFloat, estadoSaludInt, date, generoFemBoolean));
                        }
                    }
                    AdaptadorInformeRecycler adaptadorlista = new AdaptadorInformeRecycler(InformeActivity.this, R.layout.layout_para_cada_item_del_recycle, infoPeople);
                    RecyclerView recycler = (RecyclerView) findViewById(R.id.recyclerId);
                    if (InformeActivity.this.getResources().getConfiguration().orientation == 1) {
                        recycler.setLayoutManager(new LinearLayoutManager(InformeActivity.this, LinearLayoutManager.VERTICAL, false));
                    } else {
                        recycler.setLayoutManager(new GridLayoutManager(InformeActivity.this, 2));
                    }
                    recycler.setAdapter(adaptadorlista);
                }

                @Override
                public void onFailure(Object object) {
                    Toast.makeText(InformeActivity.this, "Busqueda de todos los registros INCORRECTA", Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {

        }

        regresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (identifierActivity == true) {
                    Intent intent = new Intent(InformeActivity.this, CalculateImc.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(InformeActivity.this, ReportAllActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(InformeActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}