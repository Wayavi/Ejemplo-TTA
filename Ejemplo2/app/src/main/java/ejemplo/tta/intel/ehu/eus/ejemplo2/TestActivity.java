package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.graphics.Color;
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

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    public int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
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

        //Custom code
        String pregunta = "¿En qué año fué 1 + 1?"; //Se hardcodea la pregunta (hatsa implementar el server)
        String[] respuestas = new String[5]; //Se hardcodean las respuestas (hasta implementar el server)
        respuestas[0] = "2";
        respuestas[1] = "¿Esto es una broma, no?";
        respuestas[2] = "La respuesta es el fantástico Ralph";
        respuestas[3] = "3,14";
        respuestas[4] = "Paso de esto, me voy a magisterio";

        correct = 2;

        TextView preguntaLabel = (TextView)findViewById(R.id.test_question);
        preguntaLabel.setText(pregunta);
        RadioGroup grupo = (RadioGroup)findViewById(R.id.test_group);
        for( int i = 0; i < respuestas.length; i++ )
        {
            RadioButton radio = new RadioButton(this);
            radio.setText(respuestas[i]);
            radio.setOnClickListener(this);
            grupo.addView(radio);
        }
    }

    public void send(View view)
    {
        Toast.makeText(this, "Enviar respuesta", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view)
    {
        Button boton = (Button)findViewById(R.id.test_button_send);
        boton.setVisibility(View.VISIBLE);

        RadioGroup grupo = (RadioGroup)findViewById(R.id.test_group);
        int radioId = grupo.getCheckedRadioButtonId();
        View radioButton = grupo.findViewById(radioId);
        int index = grupo.indexOfChild(radioButton);
        if(index != correct)
        {
            grupo.getChildAt(index).setBackgroundColor(Color.RED);
            grupo.getChildAt(correct).setBackgroundColor(Color.GREEN);
        }
        else
        {
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
        }
    }

}
