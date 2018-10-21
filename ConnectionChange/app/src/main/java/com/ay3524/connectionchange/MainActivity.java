package com.ay3524.connectionchange;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ay3524.connectionchange.di.DaggerMainComponent;
import com.ay3524.connectionchange.di.MainComponent;
import com.ay3524.connectionchange.di.MainModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ConnectionStatusCallback, InternetCheckCallback {

    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView textView;

    @Inject
    BroadcastReceiver mNetworkReceiver;

    @Inject
    IntentFilter intentFilter;
    private InternetCheckTask internetCheckTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        diSetup();

        registerNetworkBroadcastForNougat();

        internetCheckTask = new InternetCheckTask(this);
    }

    private void diSetup() {
        MainComponent mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();

        mainComponent.injectMainActvity(this);
    }

    private void registerNetworkBroadcastForNougat() {
        registerReceiver(mNetworkReceiver, intentFilter);
    }

    protected void unregisterNetworkChanges() {
        try {
            if (mNetworkReceiver != null) {
                unregisterReceiver(mNetworkReceiver);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isOnline) {
        if (isOnline) {
            textView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_orange_light));
            textView.setText("Connected to Network");
            if (internetCheckTask.getStatus() == AsyncTask.Status.RUNNING || internetCheckTask.getStatus() == AsyncTask.Status.PENDING) {
                internetCheckTask.cancel(true);
            }
            InternetCheckTask internetCheckTask = new InternetCheckTask(this);
            internetCheckTask.execute();
        } else {
            textView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
            textView.setText("Disconnected from Network");
        }
    }

    @Override
    public void isInternetAvailable(boolean connectivityStatus) {
        if(connectivityStatus){
            textView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_dark));
            textView.setText("Connected to Internet");
        }
    }
}
