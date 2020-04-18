package org.game.pictionary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import org.game.pictionary.R;

/**
 * <code>AppsNetworkUtils</code>
 *  class defines network related utility.
 *  The class provides a method to  confirm network connection is available or not.
 *
 * @author Amir Ansari
 */

public class AppsNetworkUtils {
    private static String TAG = AppsNetworkUtils.class.getSimpleName();


    public static boolean isConnected(Context context){

        try {
            ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(cm != null){

                if(Build.VERSION.SDK_INT < 23){
                   final NetworkInfo ni = cm.getActiveNetworkInfo();
                   if(ni != null)
                   {
                       return (ni.isConnected() && (ni.getType() == ConnectivityManager
                       .TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                   }
                }

                else{
                    final Network ni = cm.getActiveNetwork();
                    if(ni != null){
                        final NetworkCapabilities nc = cm.getNetworkCapabilities(ni);
                        return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                    }
                }
            }
            return  false;
        } catch (NullPointerException e){
            LogUtils.e(TAG,context.getString(R.string.network_error),e);
        }

        return false;
    }
}
