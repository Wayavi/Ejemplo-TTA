package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

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
        String name = intent.getStringExtra(MainActivity.EXTRA_LOGIN);
        TextView user = (TextView)findViewById(R.id.menu_label_welcome);
        String greeting = getResources().getString(R.string.menu_greeting);
        user.setText(greeting + " " + name);

    }

    public void doTest(View view)
    {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public void doExercise(View view)
    {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }

    public void doFollowup(View view)
    {
        Intent intent = new Intent(this, FollowupActivity.class);
        startActivity(intent);
    }

}
