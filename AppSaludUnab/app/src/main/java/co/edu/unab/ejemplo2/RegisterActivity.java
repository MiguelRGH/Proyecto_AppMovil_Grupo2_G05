package co.edu.unab.ejemplo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.unab.ejemplo2.model.Person;
import co.edu.unab.ejemplo2.repository.PersonaRepository;
import co.edu.unab.ejemplo2.repository.PersonaRepositoryImpl;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final String TAG = "Logs Autenticacion";
    PersonaRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button guardar = (Button) findViewById(R.id.btnSend);
        EditText nombre = (EditText) findViewById(R.id.etName);

        EditText apellido = (EditText) findViewById(R.id.etLastName);
        EditText identificacion = (EditText) findViewById(R.id.etId);
        EditText email = (EditText) findViewById(R.id.etEmail);
        EditText clave = (EditText) findViewById(R.id.etPassword);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombrePerson = nombre.getText().toString();
                String apellidoPerson = apellido.getText().toString();
                String idPerson = identificacion.getText().toString();
                //long idPersonNum = Long.parseLong(idPerson);
                String emailPerson = email.getText().toString();
                String clavePerson = clave.getText().toString();


                try {

                    if (!nombrePerson.isEmpty() && !apellidoPerson.isEmpty() && !idPerson.isEmpty() && !emailPerson.isEmpty() && !clavePerson.isEmpty()) {

                        Person persona = new Person(nombrePerson, apellidoPerson, idPerson, emailPerson, clavePerson);
                        repository = new PersonaRepositoryImpl();

                        repository.findById(idPerson, new Callback() {

                            @Override
                            public void onSuccess(Object object) {

                                if (object.equals(true)) {
                                    Toast.makeText(RegisterActivity.this, "Registro NO exitoso debido a que el ID ya existe, por favor inicie sesión con su ID", Toast.LENGTH_LONG).show();
                                } else {
                                    repository.create(persona, new Callback() {
                                        @Override
                                        public void onSuccess(Object object) {
                                            Log.d(" MSJ:", "Persona creada");
                                            createAccount(emailPerson, clavePerson);
                                            Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(Object object) {
                                            Log.d(" MSJ:", "Persona NO creada");
                                        }
                                    });
                                }

                                Intent intent = new Intent(RegisterActivity.this, login_activity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("email", emailPerson);
                                bundle.putString("clave", clavePerson);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();


                            }

                            @Override
                            public void onFailure(Object object) {
                                Toast.makeText(RegisterActivity.this, "Busqueda NO completada", Toast.LENGTH_LONG).show();

                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Por favor ingrese todos los datos solicitados", Toast.LENGTH_LONG).show();

                    }

                } catch (Exception e) {

                }
            }
        });

    }

    private void createAccount(String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "Creacion de usuario correcta",
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Creacion de usuario incorrecta",
                                    Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }
                    }
                });

    }
}