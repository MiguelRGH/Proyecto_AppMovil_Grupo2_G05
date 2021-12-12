package co.edu.unab.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import co.edu.unab.ejemplo2.model.Informe;
import co.edu.unab.ejemplo2.repository.InformeRepository;
import co.edu.unab.ejemplo2.repository.InformeRepositoryImpl;


public class CalculateImc extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final String TAG = "Logs Autenticacion";
    InformeRepository repository;


    private int position;
    private boolean generoFemenino;
    private String emailDesdeFireBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);

        TextView name = (TextView) findViewById(R.id.etTituloIMC);
        EditText weight = (EditText) findViewById(R.id.etPeso);
        EditText height = (EditText) findViewById(R.id.etTalla);
        EditText age = (EditText) findViewById(R.id.etEdad);
        EditText observations = (EditText) findViewById(R.id.etObservaciones);
        Button calculate = (Button) findViewById(R.id.btnCalcular);
        TextView result = (TextView) findViewById(R.id.etValorIMC);
        Button back = (Button) findViewById(R.id.btnRegresar);
        Button history = (Button) findViewById(R.id.btnHistorial);
        Button mapa = (Button) findViewById(R.id.btnMapa);
        RadioButton female = findViewById(R.id.femaleRadioButton);
        RadioButton male = findViewById(R.id.maleRadioButton);
        EditText resultBM = (EditText) findViewById(R.id.etValorMetabolicoBasal);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            emailDesdeFireBase = user.getEmail();

            int posicionArroba = emailDesdeFireBase.indexOf("@");
            String primeraLetraEmail = emailDesdeFireBase.substring(0, 1);
            String restoLetrasEmail = emailDesdeFireBase.substring(1, posicionArroba);
            name.setText(primeraLetraEmail.toUpperCase() + restoLetrasEmail + ", Tu IMC");
        }

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorAltura = height.getText().toString();
                String valorPeso = weight.getText().toString();
                String valorEdad = age.getText().toString();


                try {

                    if (!valorPeso.isEmpty() && !valorAltura.isEmpty() && !valorEdad.isEmpty() && (female.isChecked() || male.isChecked())) {

                        float alturaDec = Float.parseFloat(valorAltura);
                        float pesoDec = Float.parseFloat(valorPeso);
                        float edadInt = Integer.parseInt(valorEdad);
                        float resultadoDec = pesoDec / (alturaDec * alturaDec);

                        float basalMetabolism = 0;
                        int estadoSalud = 0;

                        String date = new SimpleDateFormat("dd-MM-yyyy  HH: mm", Locale.getDefault()).format(new Date());
                        String mensajeInicial = "Tu Indice de Masa Corporal (IMC) indica\n" +
                                "que tu rango es de: ";

                        result.setText(String.format("%.2f", resultadoDec));
                        if (resultadoDec < 18.5) {
                            estadoSalud = 0;
                            observations.setText(mensajeInicial + "Peso insuficiente\n" + "Se recomienda: Realizar ejercicio moderado para mantener weight y mejorar alimentación rica en proteínas.");
                        }
                        if ((resultadoDec >= 18.5) && (resultadoDec < 25)) {
                            estadoSalud = 1;
                            observations.setText(mensajeInicial + "Peso normal o saludable\n" + "Se recomienda: Realizar ejercicio para mantener weight y mantener alimentación balanceada");
                        }
                        if ((resultadoDec >= 25) && (resultadoDec < 30)) {
                            estadoSalud = 2;
                            observations.setText(mensajeInicial + "Sobrepeso\n" + "Se recomienda: Realizar ejercicio intenso para bajar de weight y mejorar alimentación rica en frutas y verduras");
                        }
                        if (resultadoDec >= 30) {
                            estadoSalud = 3;
                            observations.setText(mensajeInicial + "Obesidad\n" + "Se recomienda: Realizar ejercicio mas intenso para bajar de weight y mejorar alimentación rica en frutas, verduras y abundante agua. ");
                        }
                        if (female.isChecked()) {
                            basalMetabolism = (10 * pesoDec) + (6.25f * (alturaDec * 100)) -
                                    (5 * edadInt) - 161;
                            resultBM.setText("" + basalMetabolism);
                            generoFemenino = true;
                        } else {
                            basalMetabolism = (10 * pesoDec) + (6.25f * (alturaDec * 100)) -
                                    (5 * edadInt) + 5;
                            resultBM.setText("" + basalMetabolism);
                            generoFemenino = false;
                        }

                        Informe informe = new Informe(emailDesdeFireBase, valorEdad, valorPeso, valorAltura, resultadoDec, basalMetabolism, estadoSalud, date, generoFemenino);
                        ;
                        repository = new InformeRepositoryImpl();

                        repository.createWithoutID(informe, new Callback() {
                            @Override
                            public void onSuccess(Object object) {
                                Toast.makeText(CalculateImc.this, "Creacion de registro CORRECTO", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Object object) {
                                Toast.makeText(CalculateImc.this, "Creacion de registro INCORRECTO", Toast.LENGTH_SHORT).show();

                            }
                        });


                    } else {
                        Toast.makeText(CalculateImc.this, "Por favor ingrese todos los datos solicitados", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(CalculateImc.this, MainActivity.class);

                startActivity(intentMain);
                finish();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CalculateImc.this, InformeActivity.class);

                startActivity(intent);
            }
        });
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateImc.this, LocationActivity.class);

                startActivity(intent);

            }
        });
    }
}