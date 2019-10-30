package com.rahul.notificationmaster;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    public static final String CHANNEL_3_ID = "channel3";
    public static final String CHANNEL_4_ID = "channel4";
    public static final String CHANNEL_5_ID = "channel5";



    @Override
    public void onCreate() {
        super.onCreate();
        CreateNotificationChannels();
    }



    private void CreateNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //Channel 1 Configuration
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,
                    "channel 1",NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Test Channel 1");

            //Channel 2 Configuration
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,
                    "channel 2",NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("Test Channel 2");

            //Channel 3 Configuration
            NotificationChannel channel3 = new NotificationChannel(CHANNEL_3_ID,
                    "channel 3",NotificationManager.IMPORTANCE_DEFAULT);
            channel3.setDescription("Test Channel 3");

            //Channel 4 Configuration
            NotificationChannel channel4 = new NotificationChannel(CHANNEL_4_ID,
                    "channel 4",NotificationManager.IMPORTANCE_HIGH);
            channel4.setDescription("Test Channel 4");

            //Channel 5 Configuration
            NotificationChannel channel5 = new NotificationChannel(CHANNEL_5_ID,
                    "channel 5",NotificationManager.IMPORTANCE_HIGH);
            channel5.setDescription("Test Channel 5");




            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);
            manager.createNotificationChannel(channel4);
            manager.createNotificationChannel(channel5);

        }

    }
}
