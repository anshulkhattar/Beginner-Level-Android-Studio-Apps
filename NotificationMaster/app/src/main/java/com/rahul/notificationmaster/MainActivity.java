package com.rahul.notificationmaster;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;

import static com.rahul.notificationmaster.App.CHANNEL_1_ID;
import static com.rahul.notificationmaster.App.CHANNEL_2_ID;
import static com.rahul.notificationmaster.App.CHANNEL_3_ID;
import static com.rahul.notificationmaster.App.CHANNEL_4_ID;

public class MainActivity extends AppCompatActivity {

    private Button button1,button2,button3,button4,button5;
    private EditText editTextTitle,editTextMessage;
    private NotificationManagerCompat notificationManager;
    private MediaSessionCompat mediaSessionCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);
        button1 = (Button) findViewById(R.id.channel1Button);
        button2 = (Button) findViewById(R.id.channel2Button);
        button3 = (Button) findViewById(R.id.channel3Button);
        button4 = (Button) findViewById(R.id.channel4Button);
        button5 = (Button) findViewById(R.id.channel5Button);
        editTextTitle = (EditText) findViewById(R.id.titleTextID);
        editTextMessage = (EditText) findViewById(R.id.messageTextID);
        mediaSessionCompat = new MediaSessionCompat(MainActivity.this,"Media");


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = editTextTitle.getText().toString();
                String message = editTextMessage.getText().toString();

                Notification notification = new NotificationCompat.Builder(MainActivity.this,CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.one_24dp)
                        .setContentText(title)
                        .setContentTitle(message)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setColor(Color.GREEN)
                        .build();

                notificationManager.notify(1,notification);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = editTextTitle.getText().toString();
                String message = editTextMessage.getText().toString();

                Intent activityIntent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,activityIntent,0);

                Intent broadcastIntent = new Intent(MainActivity.this,NotificationReciever.class);
                broadcastIntent.putExtra("message",message);
                PendingIntent actionIntent = PendingIntent.getBroadcast(MainActivity.this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(MainActivity.this,CHANNEL_2_ID)
                        .setSmallIcon(R.drawable.one_24dp)
                        .setContentText(title)
                        .setContentTitle(message)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setColor(Color.GREEN)
                        .addAction(R.mipmap.ic_launcher,"Remove",pendingIntent)
                        .addAction(R.mipmap.ic_launcher,"Toast",actionIntent)
                        .setAutoCancel(true)
                        .build();

                notificationManager.notify(2,notification);

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap = null;
                String title = editTextTitle.getText().toString();
                String message = editTextMessage.getText().toString();

                bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image45);

                Notification notification = new NotificationCompat.Builder(MainActivity.this,CHANNEL_3_ID)
                        .setSmallIcon(R.drawable.two_24dp  )
                        .setContentText(title)
                        .setContentTitle(message)
                        .setLargeIcon(bitmap)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(bitmap)
                                    .bigLargeIcon(bitmap))
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .setColor(Color.GREEN)
                        .setAutoCancel(true)
                        .build();

                notificationManager.notify(3,notification);

            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap artwork = null;
                String title = editTextTitle.getText().toString();
                String message = editTextMessage.getText().toString();
                artwork = BitmapFactory.decodeResource(getResources(),R.drawable.image45);

                Notification notification = new NotificationCompat.Builder(MainActivity.this,CHANNEL_4_ID)
                        .setSmallIcon(R.drawable.two_24dp  )
                        .setContentText(title)
                        .setContentTitle(message)
                        .setLargeIcon(artwork)
                        .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                                    .setShowActionsInCompactView(1,2,3)
                                    .setMediaSession(mediaSessionCompat.getSessionToken()))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setCategory(NotificationCompat.EXTRA_MEDIA_SESSION)
                        .setColor(Color.GREEN)
                        .setAutoCancel(true)
                        .addAction(R.drawable.off_24dp,"Mute",null)
                        .addAction(R.drawable.previous_24dp,"Previous",null)
                        .addAction(R.drawable.play_24dp,"Play",null)
                        .addAction(R.drawable.next_24dp,"Next",null)
                        .addAction(R.drawable.stop_24dp,"Stop",null)
                        .setSubText("Playing")
                        .build();

                notificationManager.notify(4,notification);

            }
        });



        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int progressMax = 100;

                final NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this,CHANNEL_2_ID)
                        .setSmallIcon(R.drawable.one_24dp)
                        .setContentText("Download in Progress")
                        .setContentTitle("Download")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                        .setColor(Color.GREEN)
                        .setOngoing(true)
                        .setOnlyAlertOnce(true)
                        .setProgress(progressMax,0,false)
                        .setAutoCancel(true);

                notificationManager.notify(5,notification.build());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        for (int progress =  0; progress <= progressMax ; progress +=10) {
                            notification.setProgress(progressMax,progress,false);
                            notificationManager.notify(5,notification.build());
                            SystemClock.sleep(1000);
                        }

                        notification.setContentText("Download Finished")
                                .setProgress(0,0,false)
                                .setOngoing(false);
                        notificationManager.notify(5,notification.build());
                    }
                }).start();

            }
        });





    }




}
