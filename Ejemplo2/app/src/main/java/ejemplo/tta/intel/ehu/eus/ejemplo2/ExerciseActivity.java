package ejemplo.tta.intel.ehu.eus.ejemplo2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;

import ejemplo.tta.intel.ehu.eus.ejemplo2.otros.RestClient;

public class ExerciseActivity extends AppCompatActivity {

    private final static int PICTURE_REQUEST_CODE = 10;
    private final static int AUDIO_REQUEST_CODE = 20;
    private final static int VIDEO_REQUEST_CODE = 30;
    private final static int READ_REQUEST_CODE = 40;
    private Uri pictureUri;
    private RestClient rest;
    private int userId;

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
        final TextView pregunta = (TextView)findViewById(R.id.exercise_question);
        String auth = getIntent().getStringExtra(MenuActivity.EXTRA_AUTH);
        userId = getIntent().getIntExtra(MenuActivity.EXTRA_ID, 0);
        rest = new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
        rest.setAuthorization(auth);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject exercise = rest.getJson("getExercise?id=1");
                    final String wording = exercise.getString("wording");
                    pregunta.post(new Runnable() {
                        @Override
                        public void run() {
                            pregunta.setText(wording);
                        }
                    });
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void file(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    public void photo(View view)
    {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Toast.makeText(this,R.string.exercise_toast_no_camara, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager()) != null)
            {
                //File directorio = Environment.getExternalStorageDirectory(Environment.DIRECTORY_PICTURES);
                File directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                try
                {
                    File archivo = File.createTempFile("tta", ".jpg", directorio);
                    pictureUri = Uri.fromFile(archivo);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                }
                catch (Exception e)
                {
                    Toast.makeText(this, R.string.exercise_toast_error, Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, R.string.exercise_toast_no_photo_service, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void audio(View view)
    {
        if( !getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE) )
        {
            Toast.makeText(this,R.string.exercise_toast_no_microphone, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager()) != null)
            {
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
            }
            else
            {
                Toast.makeText(this, R.string.exercise_toast_no_micro_service, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void video(View view)
    {
        if( !getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA) )
        {
            Toast.makeText(this,R.string.exercise_toast_no_camara, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager()) != null)
            {
                startActivityForResult(intent, VIDEO_REQUEST_CODE);
            }
            else
            {
                Toast.makeText(this, R.string.exercise_toast_no_micro_service, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != Activity.RESULT_OK)
        {
            return;
        }
        switch( requestCode )
        {
            case READ_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:
            case VIDEO_REQUEST_CODE:
                sendFile(data.getData());
                break;
            case PICTURE_REQUEST_CODE:
                sendFile(pictureUri);
                break;
        }
    }

    private void sendFile(Uri uri)
    {
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
        //rest.postFile("postExercise?user=" + userId + "&id=");
    }


}
