package co.edu.unab.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.model.Person;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> personArray = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.btnIniciarSesion);
        Button registerButton = (Button) findViewById(R.id.btnRegistrarme) ;
        Bundle recieveData = getIntent().getExtras();

        if (recieveData != null) {
            personArray= (ArrayList<Person>) recieveData.getSerializable("lista");
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", personArray);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, login_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", personArray);
                intent2.putExtras(bundle);
                startActivity(intent2);
            }
        });

    }
}