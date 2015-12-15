package ejemplo.tta.intel.ehu.eus.ejemplo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
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
    }

    public void sendFile(View view)
    {
        Toast.makeText(this, "Enviar archivo", Toast.LENGTH_SHORT).show();
    }

    public void sendAudio(View view)
    {
        Toast.makeText(this, "Enviar audio", Toast.LENGTH_SHORT).show();
    }

    public void sendVideo(View view)
    {
        Toast.makeText(this, "Enviar video", Toast.LENGTH_SHORT).show();
    }

    public void sendPhoto(View view)
    {
        Toast.makeText(this, "Enviar foto", Toast.LENGTH_SHORT).show();
    }

}
