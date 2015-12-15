package ejemplo.tta.intel.ehu.eus.ejemplo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.List;

public class TestActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView pregunta = (TextView)findViewById(R.id.test_pregunta);
        String contenidoPregunta = "¿Cuando fué uno mas uno?"; //De momento lo dejo aquí hardcodeado, se debe coger del server
        pregunta.setText(contenidoPregunta);
        RadioGroup grupo = (RadioGroup)findViewById(R.id.test_grupo);
        String[] respuestas = new String[5]; //Las posibles respuestas también se hardcordean
        respuestas[0] = "Dos";
        respuestas[1] = "Esto no entraba en el examen";
        respuestas[2] = "La respuesta es el fantástico Ralph";
        respuestas[3] = "7";
        respuestas[4] = "Que le den, me voy a magisterio";

        for(int i = 0; i < respuestas.length; i++)
        {
            RadioButton radio = new RadioButton(this);
            radio.setText(respuestas[i]);
            radio.setOnClickListener(this);
        }
    }

    public void sendAnswer(View view)
    {
        Toast.makeText(this, "Pregunta enviada", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view)
    {
        Button boton = (Button)findViewById(R.id.button_enviar_respuesta);
        boton.setVisibility(View.VISIBLE);
    }

}
