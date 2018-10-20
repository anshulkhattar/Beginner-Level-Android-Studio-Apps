package com.ay3524.connectionchange.di;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.ay3524.connectionchange.MainActivity;
import com.ay3524.connectionchange.NetworkChangeReceiver;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Provides
    public BroadcastReceiver networkChangeReceiver(){
        return new NetworkChangeReceiver(mainActivity);
    }

    @Provides
    public IntentFilter intentFilter(){
        return new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }
}
