package ejemplo.tta.intel.ehu.eus.ejemplo2.modelo;


import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import ejemplo.tta.intel.ehu.eus.ejemplo2.otros.RestClient;

public class Business
{
    private final RestClient rest;

    public Business(RestClient re)
    {
        rest = re;
    }

    public void getStatus(String dni) throws IOException, JSONException
    {
        //TODO change return value to Status class
    }

    public void getTest(int id) throws IOException, JSONException
    {
        //TODO change return value to Test class
    }

    public void getExercise(int id) throws IOException, JSONException
    {
        //TODO change return value to Exercise class
    }

    public void uploadSolution(int userId, int exerciseId, InputStream is, String fileName) throws IOException
    {
        //TODO
    }

    public void uploadChoice(int userId, int choiceId)
    {
        //TODO
    }
}
