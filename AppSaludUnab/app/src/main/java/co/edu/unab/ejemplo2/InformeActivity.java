package co.edu.unab.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.model.Informe;
import co.edu.unab.ejemplo2.model.Person;

public class InformeActivity extends AppCompatActivity {

    private ArrayList<Person> personArray = new ArrayList<Person>();
    private ArrayList<Informe> infoPeople = new ArrayList<Informe>();
    private ArrayList<Informe> registrosDePersonaEspecifica;


    private int position;


    ArrayList<String> listaDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        Button regresar = (Button)findViewById(R.id.btnRegresarInforme);
        Button salir = (Button)findViewById(R.id.btnSalir);

        Bundle recibeDatos = getIntent().getExtras();
        if(getIntent().getExtras().getSerializable("lista") != null){
            personArray= (ArrayList<Person>) recibeDatos.getSerializable("lista");

        }
        if(getIntent().getExtras().getSerializable("informe") != null){
            infoPeople= (ArrayList<Informe>) recibeDatos.getSerializable("informe");

        }
        if(getIntent().getExtras().getSerializable("position") != null) {
            position = recibeDatos.getInt("position");
        }

        registrosDePersonaEspecifica= (ArrayList<Informe>) infoPeople.clone();


        for (int i= 0; i<registrosDePersonaEspecifica.size();i++) {

            registrosDePersonaEspecifica.removeIf (n -> (n.getId() != position));

        }



        AdaptadorInformeRecycler adaptadorlista = new AdaptadorInformeRecycler(this, R.layout.layout_para_cada_item_del_recycle,registrosDePersonaEspecifica);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recyclerId);
        if(this.getResources().getConfiguration().orientation == 1) {
            recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }else {
            recycler.setLayoutManager(new GridLayoutManager(this, 2));
        }
        recycler.setAdapter(adaptadorlista);


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (InformeActivity.this, CalculateImc.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", personArray);
                bundle.putSerializable("informe", infoPeople);
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (InformeActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", personArray);
                bundle.putSerializable("informe", infoPeople);
                intent2.putExtras(bundle);
                startActivity(intent2);
                finish();
            }
        });


    }


}