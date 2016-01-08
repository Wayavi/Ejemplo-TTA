package ejemplo.tta.intel.ehu.eus.ejemplo2.presentacion;

import android.os.Bundle;

import ejemplo.tta.intel.ehu.eus.ejemplo2.modelo.Exercise;

public class Data
{
    private final static String EXTRA_USER = "eus.ehu.intel.tta.Ejemplo.user";
    private final static String EXTRA_AUTH = "eus.ehu.intel.tta.Ejemplo.auth";
    private final static String EXTRA_NAME = "eus.ehu.intel.tta.Ejemplo.name";
    private final static String EXTRA_EXERCISE_ID = "eus.ehu.intel.tta.Ejemplo.exerciseId";
    private final static String EXTRA_EXERCISE_WORDING = "eus.ehu.intel.tta.Ejemplo.exerciseWording";

    private final Bundle bundle;

    public Data(Bundle b)
    {
        if(b == null)
        {
            b = new Bundle();
        }
        this.bundle = b;
    }

    public Bundle getBundle()
    {
        return bundle;
    }

    public void putUserId(int id)
    {
        bundle.putInt(EXTRA_USER, id);
    }

    public int getUserId()
    {
        return bundle.getInt(EXTRA_USER);
    }

    public void putAuthToken(String auth)
    {
        bundle.putString(EXTRA_AUTH, auth);
    }

    public String getAuthToken()
    {
        return bundle.getString(EXTRA_AUTH);
    }

    public void putUserName(String name)
    {
        bundle.putString(EXTRA_NAME, name);
    }

    public String getUserName()
    {
        return bundle.getString(EXTRA_NAME);
    }

    public void putExercise(Exercise exercise)
    {
        bundle.putInt(EXTRA_EXERCISE_ID, exercise.getId());
        bundle.putString(EXTRA_EXERCISE_WORDING, exercise.getWording());
    }

    public Exercise getExercise()
    {
        Exercise exercise = new Exercise();
        exercise.setId(bundle.getInt(EXTRA_EXERCISE_ID));
        exercise.setWording(bundle.getString(EXTRA_EXERCISE_WORDING));
        return exercise;
    }

}
