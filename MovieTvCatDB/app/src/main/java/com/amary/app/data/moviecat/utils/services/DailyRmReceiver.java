package com.amary.app.data.moviecat.utils.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.ui.activity.MainActivity;

public class DailyRmReceiver extends BroadcastReceiver {
    public static final String DAILY_TITLE = "com.amary.app.data.moviecat.widget.DAILY_TITLE";
    public static final String DAILY_MESSAGE = "com.amary.app.data.moviecat.widget.DAILY_MESSAGE";
    private static final int NOTIFICATION_ID = 1;
    private static String CHANNEL_ID = "channel_1";
    private static CharSequence CHANNEL_NAME = "amary_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(DAILY_TITLE);
        String message = intent.getStringExtra(DAILY_MESSAGE);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                mainIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (notificationManager != null){
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
