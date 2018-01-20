package aygalinc.fr.showremote.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by colin on 20/01/2018.
 */

public class ConnectivityUtil {

    public static boolean checkConnectivity(Context ctx)  {
        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void toast(Activity act, String text)  {
        Toast.makeText(act, text, Toast.LENGTH_SHORT).show();
    }
}
