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

public class RegisterActivity extends AppCompatActivity {

    private ArrayList<Person> personArray = new ArrayList<>();
    private ArrayList<Informe> infoPeople = new ArrayList<Informe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button save = (Button) findViewById(R.id.btnSend);
        EditText name = (EditText) findViewById(R.id.etName);
        EditText lastName = (EditText) findViewById(R.id.etLastName);
        EditText id = (EditText) findViewById(R.id.etId);
        EditText email = (EditText) findViewById(R.id.etEmail);
        EditText password = (EditText) findViewById(R.id.etPassword);

        Bundle receiveData = getIntent().getExtras();

        if (getIntent().getExtras().getSerializable("lista") != null) {
            personArray = (ArrayList<Person>) receiveData.getSerializable("lista");

        }
        if (getIntent().getExtras().getSerializable("informe") != null) {
            infoPeople = (ArrayList<Informe>) receiveData.getSerializable("informe");

        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePerson = name.getText().toString();
                String lastNamePerson = lastName.getText().toString();
                String idPerson = id.getText().toString();
                String emailPerson = email.getText().toString();
                String passwordPerson = password.getText().toString();
                if (namePerson.equals("") || lastNamePerson.equals("") || idPerson.equals("") ||
                        emailPerson.equals("") || passwordPerson.equals("")) {
                    personArray.add(new Person(namePerson, lastNamePerson, idPerson, emailPerson, passwordPerson));
                    Toast.makeText(RegisterActivity.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, login_activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("lista", personArray);
                    bundle.putSerializable("informe", infoPeople);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "No se permiten campos vacios. Intente nuevamente",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}