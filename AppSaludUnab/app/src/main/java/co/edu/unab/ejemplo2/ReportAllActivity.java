package co.edu.unab.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import co.edu.unab.ejemplo2.model.DatosPerson;
import co.edu.unab.ejemplo2.repository.InformeRepository;
import co.edu.unab.ejemplo2.repository.InformeRepositoryImpl;
import co.edu.unab.ejemplo2.repository.PersonaRepository;
import co.edu.unab.ejemplo2.repository.PersonaRepositoryImpl;


public class ReportAllActivity extends AppCompatActivity {

    PersonaRepository repository;
    InformeRepository repository2;


    private ArrayList<DatosPerson> allCommunity = new ArrayList<DatosPerson>();
    private int contador = 0;
    public HashMap<String, Integer> mapaContador = new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_all);
        Button salir = (Button) findViewById(R.id.btnSalirReportAll);
        TextView userLogin = (TextView) findViewById(R.id.tvCorreoBienestar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            userLogin.setText("" + user.getEmail());
        }

        repository = new PersonaRepositoryImpl();
        repository.findAll(new Callback() {
            @Override
            public void onSuccess(Object object) {

                QuerySnapshot objectSnapshot = (QuerySnapshot) object;

                int tamaño = objectSnapshot.size();

                for (int i = 0; i < tamaño; i++) {
                    String name = objectSnapshot.getDocuments().get(i).getString("nombre").toUpperCase();
                    String lastName = objectSnapshot.getDocuments().get(i).getString("apellido").toUpperCase();
                    String emailUser = objectSnapshot.getDocuments().get(i).getString("correo");
                    allCommunity.add(new DatosPerson(name, lastName, emailUser));


                    repository2 = new InformeRepositoryImpl();
                    repository2.findAll(new Callback() {
                        @Override
                        public void onSuccess(Object object) {
                            //
                            int contador = 0;
                            QuerySnapshot objectSnapshotRegistros = (QuerySnapshot) object;
                            int tamaño = objectSnapshotRegistros.size();
                            for (int j = 0; j < tamaño; j++) {
                                if (objectSnapshotRegistros.getDocuments().get(j).getString("email").equals(emailUser)) {
                                    contador = contador + 1;
                                }
                            }

                            mapaContador.put(emailUser, contador);
                            contador = 0;

                        }

                        @Override
                        public void onFailure(Object object) {
                            Toast.makeText(ReportAllActivity.this, "No encuentra Registros: " + object, Toast.LENGTH_LONG).show();

                        }


                    });




                }

                AdaptadorPersonaRecycler adaptadorlista = new AdaptadorPersonaRecycler(ReportAllActivity.this, R.layout.layout_para_recycler_all_comnunity, allCommunity);
                RecyclerView recycler = (RecyclerView) findViewById(R.id.recyclerReportAll);
                if (ReportAllActivity.this.getResources().getConfiguration().orientation == 1) {
                    recycler.setLayoutManager(new LinearLayoutManager(ReportAllActivity.this, LinearLayoutManager.VERTICAL, false));
                } else {
                    recycler.setLayoutManager(new GridLayoutManager(ReportAllActivity.this, 2));
                }
                recycler.setAdapter(adaptadorlista);

            }

            @Override
            public void onFailure(Object object) {
                Toast.makeText(ReportAllActivity.this, "Busqueda de todos los registros INCORRECTA", Toast.LENGTH_LONG).show();

            }
        });


        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ReportAllActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });

    }
}
