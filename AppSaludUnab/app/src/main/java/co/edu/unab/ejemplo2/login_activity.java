package co.edu.unab.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.model.Informe;
import co.edu.unab.ejemplo2.model.Person;

public class login_activity extends AppCompatActivity {

    private ArrayList<Person> personArray = new ArrayList<Person>();
    private ArrayList<Informe> infoPeople = new ArrayList<Informe>();
    boolean usuarioNoExiste = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        EditText idUsuario = (EditText) findViewById(R.id.etUserIdentificacion);
        EditText ClaveUsuario = (EditText) findViewById(R.id.etUserClave);
        Button botonInicio = (Button) findViewById(R.id.btnInicioSesion2);
        Bundle recibeDatos = getIntent().getExtras();

        if(getIntent().getExtras().getSerializable("lista") != null){
            personArray= (ArrayList<Person>) recibeDatos.getSerializable("lista");
            boolean usuarioNoExiste= false;

        }

        if(getIntent().getExtras().getSerializable("informe") != null){
            infoPeople= (ArrayList<Informe>) recibeDatos.getSerializable("informe");

        }

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idUsuarioRecibido = idUsuario.getText().toString();
                String ClaveUsuarioRecibido = ClaveUsuario.getText().toString();

                int tamañoLista = personArray.size();
                if (tamañoLista == 0) {
                    Toast.makeText(login_activity.this, "No hay usuarios registrados. \nPor favor registrarse", Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i < tamañoLista; i++) {
                    if (personArray.get(i).getId().equals(idUsuarioRecibido)) {
                        if (personArray.get(i).getPassword().equals(ClaveUsuarioRecibido)) {
                            usuarioNoExiste = false;
                            Intent intent2 = new Intent(login_activity.this, CalculateImc.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("lista", personArray);
                            bundle.putSerializable("informe", infoPeople);
                            bundle.putInt("position", i);
                            intent2.putExtras(bundle);
                            startActivity(intent2);
                            finish();
                            break;
                        } else {
                            Toast.makeText(login_activity.this, "Clave Incorrecta, vuelva a intentarlo", Toast.LENGTH_LONG).show();
                            usuarioNoExiste = false;
                            break;
                        }
                    } else {
                        usuarioNoExiste = true;
                    }
                }
                if (usuarioNoExiste == true) {
                    Toast.makeText(login_activity.this, "Usuario No registrado. \nPor favor registrese", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
