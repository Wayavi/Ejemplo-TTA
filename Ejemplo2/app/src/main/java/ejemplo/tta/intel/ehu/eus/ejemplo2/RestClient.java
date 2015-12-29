package ejemplo.tta.intel.ehu.eus.ejemplo2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RestClient
{
    private final static String AUTH = "Authorization";
    private final String baseUrl;
    private final Map<String,String> properties = new HashMap();

    public RestClient (String url)
    {
        this.baseUrl = url;
        //"http://u017633.ehu.eus:18080/AlumnoTta/rest/tta/"
    }

    public void setHttpBasicAuh(String user, String pass)
    {
        //TODO
    }

    public String getAuthorization()
    {
        return properties.get(AUTH);
    }

    public void setAuthorization(String auth)
    {
        properties.put(AUTH, auth);
    }

    public void setProperty(String name, String value)
    {
        properties.put(name, value);
    }

    private HttpURLConnection getConnection(String path) throws IOException
    {
        URL url = new URL(String.format("%s/%s", baseUrl, path));
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        for(Map.Entry<String,String> property : properties.entrySet())
        {
            conn.setRequestProperty(property.getKey(), property.getValue());
        }
        conn.setUseCaches(false);
        return conn;
    }

    public String getString(String path) throws IOException
    {
        //TODO
        String cad = "EYEYEYE";
        return cad;
    }

    public JSONObject getJson(String path) throws  IOException, JSONException
    {
        //TODO
        JSONObject obj = new JSONObject();
        return obj;
    }

    public int postFile(String path, InputStream is, String fileName) throws IOException
    {
       //TODO
        return 1;
    }

    public int postJson(final JSONObject json, String path) throws IOException
    {
        //TODO
        return 1;
    }



}
