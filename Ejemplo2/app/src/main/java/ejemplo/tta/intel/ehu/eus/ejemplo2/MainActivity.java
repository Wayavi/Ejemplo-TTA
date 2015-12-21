package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_LOGIN = "eus.ehu.intel.tta.Ejemplo2.user";
    public final static String EXTRA_PASS = "eus.ehu.intel.tta.Ejemplo2.pass";
    public static final String PREF_LOGIN = "eus.ehu.intel.tta.Ejemplo2.login";

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

        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        String userShared = preferencias.getString(PREF_LOGIN, null);
        if(userShared != null)
        {
            EditText userName = (EditText)findViewById(R.id.main_user);
            userName.setText(userShared);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view)
    {
        EditText user = (EditText)findViewById(R.id.main_user);
        EditText pass = (EditText)findViewById(R.id.main_pass);

        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(PREF_LOGIN, user.getText().toString());
        editor.commit();

        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(EXTRA_LOGIN, user.getText().toString());
        intent.putExtra(EXTRA_PASS, pass.getText().toString());
        startActivity(intent);
    }
}
