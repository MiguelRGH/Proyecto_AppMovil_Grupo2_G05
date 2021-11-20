package co.edu.unab.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.model.Person;

public class RegisterActivity extends AppCompatActivity {

    private ArrayList<Person> personArray = new ArrayList<>();

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

        if (receiveData != null) {
            personArray = (ArrayList<Person>) receiveData.getSerializable("lista");
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePerson = name.getText().toString();
                String lastNamePerson = lastName.getText().toString();
                String idPerson = id.getText().toString();
                String emailPerson = email.getText().toString();
                String passwordPerson = password.getText().toString();
                personArray.add(new Person(namePerson, lastNamePerson, idPerson, emailPerson, passwordPerson));
                Toast.makeText(RegisterActivity.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, login_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", personArray);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}