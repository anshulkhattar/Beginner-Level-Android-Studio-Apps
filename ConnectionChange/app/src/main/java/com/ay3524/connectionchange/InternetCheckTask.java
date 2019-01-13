package com.ay3524.connectionchange;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class InternetCheckTask extends AsyncTask<Void, Boolean, Boolean> {

    private InternetCheckCallback internetCheckCallback;
    private boolean isInternetAvailable = false;

    InternetCheckTask(InternetCheckCallback internetCheckCallback) {
        this.internetCheckCallback = internetCheckCallback;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return checkSocket("8.8.8.8", 53);
    }


    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        internetCheckCallback.isInternetAvailable(aBoolean);
    }

    public boolean checkSocket(String ip, int port) {
        try {
            int timeOut = 5000;
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("8.8.8.8", 53);
            socket.connect(socketAddress, timeOut);
            socket.close();
            isInternetAvailable = true;

            // TODO test woohoo server
//          IP Address :  18.236.139.81 Port No. - 22


            return true;
        } catch (IOException e) {
            Log.e(MainActivity.class.getSimpleName(), e.getMessage());
        }
        return false;
    }
}
