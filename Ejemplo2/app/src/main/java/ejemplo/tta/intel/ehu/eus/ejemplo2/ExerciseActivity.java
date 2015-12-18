package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
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

        //Custom code
        TextView pregunta = (TextView)findViewById(R.id.exercise_question);
        pregunta.setText("Pulsa uno de estos botones al azar para ver un magnífico Toast");
    }

    public void file(View view)
    {
        Toast.makeText(this, "File pulsado", Toast.LENGTH_SHORT).show();
    }

    public void photo(View view)
    {
        Toast.makeText(this, "Photo pulsado", Toast.LENGTH_SHORT).show();
    }

    public void audio(View view)
    {
        Toast.makeText(this, "Audio pulsado", Toast.LENGTH_SHORT).show();
    }

    public void video(View view)
    {
        Toast.makeText(this, "Video pulsado", Toast.LENGTH_SHORT).show();
    }

}
