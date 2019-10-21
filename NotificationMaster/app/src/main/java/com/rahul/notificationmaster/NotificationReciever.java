package com.rahul.notificationmaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra("message");
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }
}
