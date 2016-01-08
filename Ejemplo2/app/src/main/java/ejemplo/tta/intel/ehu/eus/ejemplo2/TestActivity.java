package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ejemplo.tta.intel.ehu.eus.ejemplo2.otros.AudioPlayer;
import ejemplo.tta.intel.ehu.eus.ejemplo2.otros.RestClient;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private RestClient rest;
    private RadioGroup grupo;
    private TextView preguntaLabel;
    private int correctIndex, selected, testId;
    private String[] advices;
    private String[] adviceTypes;
    private String auth, wording;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        contexto = this;
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

        grupo = (RadioGroup)findViewById(R.id.test_group);
        preguntaLabel = (TextView)findViewById(R.id.test_question);

        Intent intent = getIntent();
        auth = intent.getStringExtra(MenuActivity.EXTRA_AUTH);
        rest = new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
        rest.setAuthorization(auth);
        testId = intent.getIntExtra(MenuActivity.EXTRA_TEST, 0);

        if(testId != 0)
        {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            JSONObject json = rest.getJson("getTest?id=" + testId);
                            wording = json.getString("wording");
                            preguntaLabel.post(new Runnable() {
                                @Override
                                public void run() {
                                    preguntaLabel.setText(wording);
                                }
                            });

                            JSONArray respuestas = json.getJSONArray("choices");
                            int numResp = respuestas.length();
                            advices = new String[numResp];
                            adviceTypes = new String[numResp];
                            for(int i = 0; i < numResp; i++)
                            {
                                JSONObject choice = respuestas.getJSONObject(i);
                                int id = choice.getInt("id");
                                final String advise = choice.getString("advise");
                                advices[i] = advise;
                                final String answer = choice.getString("answer");
                                final Boolean correct = choice.getBoolean("correct");
                                JSONObject type = null;
                                if(!correct)
                                    type = choice.getJSONObject("resourceType");
                                else
                                    correctIndex = id - 1;
                                if(type != null)
                                {
                                    adviceTypes[i] = type.getString("description");
                                }
                                //description mp4 mp3 html
                                grupo.post(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        RadioButton button = new RadioButton(contexto);
                                        button.setText(answer);
                                        button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                    Button botonSend = (Button)findViewById(R.id.test_button_send);
                                                    botonSend.setVisibility(View.VISIBLE);
                                            }
                                        });
                                        grupo.addView(button);
                                    }
                                });


                            }
                        }
                        catch(JSONException e) {
                            e.printStackTrace();
                        }
                        catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ).start();
        }

    }

    public void send(View view)
    {
        RadioGroup grupo = (RadioGroup)findViewById(R.id.test_group);
        int radios = grupo.getChildCount();
        for(int i = 0; i < radios; i++ )
        {
            grupo.getChildAt(i).setEnabled(false);
        }
        int radioId = grupo.getCheckedRadioButtonId();
        View radioButton = grupo.findViewById(radioId);
        selected = grupo.indexOfChild(radioButton);
        if(selected != correctIndex)
        {
            grupo.getChildAt(selected).setBackgroundColor(Color.RED);
            grupo.getChildAt(correctIndex).setBackgroundColor(Color.GREEN);
            Button botonAdvice = (Button)findViewById(R.id.test_button_advice);
            botonAdvice.setVisibility(View.VISIBLE);
            View botonSend = findViewById(R.id.test_button_send);
            LinearLayout layout = (LinearLayout)findViewById(R.id.test_layout);
            layout.removeView(botonSend);
        }
        else
        {
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
            Button button = (Button)findViewById(R.id.test_button_send);
            button.setVisibility(View.INVISIBLE);
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
        String type = adviceTypes[selected];
        if (type.equals("html"))
        {
            if(advice.substring(0, 10).contains("://"))
            {
                //Es una url
                Toast.makeText(this, "URL", Toast.LENGTH_SHORT).show();
                LinearLayout layout = (LinearLayout)findViewById(R.id.test_layout);
                WebView web = new WebView(this);
                web.setWebViewClient(new WebViewClient());
                web.loadUrl(advice);
                layout.addView(web);
            }
            else
            {
                //Es html a pelo
                Toast.makeText(this, "HTML", Toast.LENGTH_SHORT).show();
                LinearLayout layout = (LinearLayout)findViewById(R.id.test_layout);
                WebView web = new WebView(this);
                web.loadData(advice, "text/html", null);
                web.setBackgroundColor(Color.TRANSPARENT);
                web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
                layout.addView(web);
            }
        }
        else if(type.equals("mp4"))
        {
            //Es un video
            Toast.makeText(this, "Video", Toast.LENGTH_SHORT).show();
            VideoView video = new VideoView(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            video.setLayoutParams(params);
            video.setVideoURI(Uri.parse(advices[3]));

            MediaController controller = new MediaController(this);
            controller.setAnchorView(video);
            video.setMediaController(controller);
            LinearLayout layout = (LinearLayout)findViewById(R.id.test_layout);
            layout.addView(video);
            video.start();
        }
        else if(type.equals("mp3"))
        {
            //Es un audio
            Toast.makeText(this, "Audio", Toast.LENGTH_SHORT).show();
            LinearLayout layout = (LinearLayout)findViewById(R.id.test_layout);
            AudioPlayer player = new AudioPlayer(layout, new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
            player.setUri();

        }

    }

}
