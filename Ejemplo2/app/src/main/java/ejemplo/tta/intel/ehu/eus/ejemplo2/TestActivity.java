package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.content.Intent;
import android.graphics.Color;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    public int correct, selected;
    public String[] advices;

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

        advices = new String[5];
        advices[0] = "http://www.google.es";
        advices[1] = "<h1> The simpsons </h1> <p> Deberías ver más esta serie, es una frase mítica de <b> Ralph </b> </p>";
        advices[2] = null;
        advices[3] = "";
        advices[4] = "";

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
        RadioGroup grupo = (RadioGroup)findViewById(R.id.test_group);
        int radioId = grupo.getCheckedRadioButtonId();
        View radioButton = grupo.findViewById(radioId);
        selected = grupo.indexOfChild(radioButton);
        if(selected != correct)
        {
            grupo.getChildAt(selected).setBackgroundColor(Color.RED);
            grupo.getChildAt(correct).setBackgroundColor(Color.GREEN);
            Button botonAdvice = (Button)findViewById(R.id.test_button_advice);
            botonAdvice.setVisibility(View.VISIBLE);
            View botonSend = findViewById(R.id.test_button_send);
            LinearLayout layout = (LinearLayout)findViewById(R.id.test_layout);
            layout.removeView(botonSend);
        }
        else
        {
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view)
    {
        Button botonSend = (Button)findViewById(R.id.test_button_send);
        botonSend.setVisibility(View.VISIBLE);
    }

    public void advice(View view)
    {
        String advice = advices[selected];
        if(selected == 0)
        {
            //Es una url
            Uri uri = Uri.parse(advice);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else if(selected == 1)
        {
            LinearLayout layout = (LinearLayout)findViewById(R.id.test_layout);
            WebView web = new WebView(this);
            web.loadData(advice, "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            layout.addView(web);
        }
        else if(selected == 3)
        {
            VideoView video = new VideoView(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            video.setLayoutParams(params);
            video.setVideoURI(Uri.parse("https://youtu.be/q8ir8rVl2Z4"));
        }
    }

}
