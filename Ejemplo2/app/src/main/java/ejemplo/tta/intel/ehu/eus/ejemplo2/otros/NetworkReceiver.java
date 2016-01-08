package ejemplo.tta.intel.ehu.eus.ejemplo2.otros;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import ejemplo.tta.intel.ehu.eus.ejemplo2.R;

public class NetworkReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info != null)
        {
            if(!info.isConnected())
            {
                Toast.makeText(context, R.string.network_disconnected, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
