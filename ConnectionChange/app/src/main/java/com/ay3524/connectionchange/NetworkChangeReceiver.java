package com.ay3524.connectionchange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChangeReceiver extends BroadcastReceiver {

    public static final String TAG = NetworkChangeReceiver.class.getSimpleName();
    ConnectionStatusCallback connectionStatusCallback;

    public NetworkChangeReceiver() {
        super();
    }

    public NetworkChangeReceiver(ConnectionStatusCallback connectionStatusCallback) {
        this.connectionStatusCallback = connectionStatusCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectionStatusCallback != null) {
            connectionStatusCallback.onNetworkConnectionChanged(isConnected);
        }
    }
}
