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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import co.edu.unab.ejemplo2.model.Informe;
import co.edu.unab.ejemplo2.model.Person;

public class CalculateImc extends AppCompatActivity {

    private ArrayList<Person> personArray = new ArrayList<>();
    private ArrayList<Informe> infoPeople = new ArrayList<Informe>();
    private int position;
    private boolean generoFemenino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);

        TextView name = (TextView)findViewById(R.id.etTituloIMC);
        EditText weight = (EditText) findViewById(R.id.etPeso);
        EditText height = (EditText) findViewById(R.id.etTalla);
        EditText age = (EditText) findViewById(R.id.etEdad);
        EditText observations = (EditText) findViewById(R.id.etObservaciones);
        Button calculate = (Button) findViewById(R.id.btnCalcular);
        TextView result = (TextView) findViewById(R.id.etValorIMC);
        Button back = (Button) findViewById(R.id.btnRegresar);
        Button history = (Button) findViewById(R.id.btnHistorial);
        RadioButton female = findViewById(R.id.femaleRadioButton);
        RadioButton male = findViewById(R.id.maleRadioButton);
        EditText resultBM = (EditText) findViewById(R.id.etValorMetabolicoBasal);

        Bundle recibeDatos = getIntent().getExtras();
        if(getIntent().getExtras().getSerializable("lista") != null){
            personArray= (ArrayList<Person>) recibeDatos.getSerializable("lista");
            //position = recibeDatos.getInt("position");

        }
        if(getIntent().getExtras().getSerializable("informe") != null){
            infoPeople= (ArrayList<Informe>) recibeDatos.getSerializable("informe");

        }

        if(getIntent().getExtras().getSerializable("position") != null) {
            position = recibeDatos.getInt("position");
        }

        name.setText(personArray.get(position).getName().toUpperCase()+ ", Tu IMC");

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float basalMetabolism;
                int estadoSalud=0;

                String date = new SimpleDateFormat("dd-MM-yyyy  HH: mm", Locale.getDefault()).format(new Date());
                String mensajeInicial = "Tu Indice de Masa Corporal (IMC) indica\n"  +
                                        "que tu rango es de: ";
                String valorAltura = height.getText().toString();
                String valorPeso = weight.getText().toString();
                String valorEdad = age.getText().toString();
                float alturaDec = Float.parseFloat(valorAltura);
                float pesoDec = Float.parseFloat(valorPeso);
                float edadInt = Integer.parseInt(valorEdad);
                float resultadoDec = pesoDec / (alturaDec*alturaDec);
                result.setText(String.format("%.2f" , resultadoDec));
                if (resultadoDec < 18.5 ){
                    estadoSalud = 0;
                    observations.setText(mensajeInicial + "Peso insuficiente\n" +"Se recomienda: Realizar ejercicio moderado para mantener weight y mejorar alimentación rica en proteínas." );
                }
                if ((resultadoDec >= 18.5)&&(resultadoDec < 25)){
                    estadoSalud = 1;
                    observations.setText( mensajeInicial + "Peso normal o saludable\n"  +"Se recomienda: Realizar ejercicio para mantener weight y mantener alimentación balanceada"  );
                }
                if ((resultadoDec >= 25)&&(resultadoDec <30)){
                    estadoSalud = 2;
                    observations.setText( mensajeInicial + "Sobrepeso\n" +"Se recomienda: Realizar ejercicio intenso para bajar de weight y mejorar alimentación rica en frutas y verduras"  );
                }
                if (resultadoDec >= 30){
                    estadoSalud = 3;
                    observations.setText( mensajeInicial + "Obesidad\n"  +"Se recomienda: Realizar ejercicio mas intenso para bajar de weight y mejorar alimentación rica en frutas, verduras y abundante agua. "   );
                }
                if (female.isChecked()) {
                   basalMetabolism = (10 * pesoDec) + (6.25f * (alturaDec * 100)) -
                            (5 * edadInt) - 161;
                    resultBM.setText(""+basalMetabolism);
                    generoFemenino = true;
                }
                else {
                    basalMetabolism = (10 * pesoDec) + (6.25f * (alturaDec * 100)) -
                           (5 * edadInt) +5;
                    resultBM.setText(""+basalMetabolism);
                    generoFemenino = false;
                }
                infoPeople.add(new Informe(position, valorEdad,valorPeso, valorAltura, resultadoDec, basalMetabolism, estadoSalud, date, generoFemenino));
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent (CalculateImc.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", personArray);
                bundle.putSerializable("informe", infoPeople);
                intentMain.putExtras(bundle);
                startActivity(intentMain);
                finish();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CalculoIMC.this, "Historial en construcción...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent (CalculateImc.this, InformeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", personArray);
                bundle.putSerializable("informe", infoPeople);
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}