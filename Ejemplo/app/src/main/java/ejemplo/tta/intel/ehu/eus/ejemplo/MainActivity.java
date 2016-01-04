package ejemplo.tta.intel.ehu.eus.ejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_LOGIN = "eus.ehu.intel.tta.Ejemplo.login";
    public final static String EXTRA_PASSWD = "eus.ehu.intel.tta.Ejemplo.password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void login(View view)
    {
        Intent intent = new Intent(this, MenuActivity.class);
        EditText name = (EditText)findViewById(R.id.login);
        EditText pass = (EditText)findViewById(R.id.password);
        intent.putExtra(EXTRA_LOGIN, name.getText().toString());
        intent.putExtra(EXTRA_PASSWD, pass.getText().toString());
        startActivity(intent);
    }

}
