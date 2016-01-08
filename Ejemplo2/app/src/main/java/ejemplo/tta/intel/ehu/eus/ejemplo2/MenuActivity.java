package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ejemplo.tta.intel.ehu.eus.ejemplo2.otros.RestClient;

public class MenuActivity extends AppCompatActivity {

    public final static String EXTRA_TEST = "eus.ehu.intel.tta.Ejemplo2.test";
    public final static String EXTRA_AUTH = "eus.ehu.intel.tta.Ejemplo2.auth";
    public final static String EXTRA_ID = "eus.ehu.intel.tta.Ejemplo2.userId";

    private RestClient rest;
    private String dni, pass;
    private TextView userView, lessonView;
    private JSONObject status;
    private String name, lessonNumber, lessonTitle;
    private int nextTest, nextExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

        //Código personalizado
        Intent intent = getIntent();
        dni = intent.getStringExtra(MainActivity.EXTRA_LOGIN);
        pass = intent.getStringExtra(MainActivity.EXTRA_PASS);

        userView = (TextView)findViewById(R.id.menu_label_welcome);
        lessonView = (TextView)findViewById(R.id.menu_label_lesson);

        rest = new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
        rest.setHttpBasicAuh(dni, pass);

        new Thread(new Runnable() {
            public void run()
            {
            try
            {
                status = rest.getJson("getStatus?dni=" + dni);
                name = status.getString("user");
                lessonNumber = status.getString("lessonNumber");
                lessonTitle = status.getString("lessonTitle");
                nextTest = status.getInt("nextTest");
                nextExercise = status.getInt("nextExercise");

                userView.post(new Runnable()
                {
                    public void run()
                    {
                        String greeting = getResources().getString(R.string.menu_greeting);
                        userView.setText(greeting + " " + name);
                    }
                });

                lessonView.post(new Runnable()
                {
                    public void run()
                    {
                        lessonView.setText(getResources().getString(R.string.menu_leccion) + " " + lessonNumber + ": " + lessonTitle);
                    }
                });

            } catch (IOException e) {
            } catch (JSONException e) {
            }
            }
        }).start();
    }

    public void doTest(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra(EXTRA_TEST, nextTest);
        intent.putExtra(EXTRA_AUTH, rest.getAuthorization());
        startActivity(intent);
    }

    public void doExercise(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra(EXTRA_AUTH, rest.getAuthorization());
        int userId = 0;
        try
        {
            userId = status.getInt("id");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(userId != 0)
            intent.putExtra(EXTRA_ID, userId);
        startActivity(intent);
    }

    public void doFollowup(View view) {
        Intent intent = new Intent(this, FollowupActivity.class);
        startActivity(intent);
    }

}
