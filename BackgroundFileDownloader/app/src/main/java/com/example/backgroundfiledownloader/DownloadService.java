package com.example.backgroundfiledownloader;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.koushikdutta.ion.Ion;

import java.io.File;

public class DownloadService extends Service {

    public static int[] progress = new int[3];
    private final String[] links = {
            "https://hubblesite.org/uploads/image_file/image_attachment/31866/STSCI-H-p1942a-f-3191x3161.png", //18.85
            "https://hubblesite.org/uploads/image_file/image_attachment/30065/STSCI-H-p1721a-f-5290x5290.png", //23.47
            "https://hubblesite.org/uploads/image_file/image_attachment/28652/xlarge_web.jpg"//30mb
    };
    public static boolean shouldRun = true;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        for (int i = 0; i < links.length; i++) {
            final int finalI = i;
            Ion.with(this)
                    .load(links[i])
                    .progress((downloaded, total) -> {
                        progress[finalI] = (int) (((double) downloaded / total) * 100);
                        if (!shouldRun) {
                            DownloadService.this.stopSelf();
                        }
                        notify(progress[finalI], finalI);
                    })
                    .write(new File(
                            Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + System.currentTimeMillis() + ".png"))
                    .setCallback((e, result) -> {
                        new Handler(Looper.getMainLooper())
                                .post(() -> {
                                    Toast.makeText(this, finalI + " th file done!", Toast.LENGTH_SHORT).show();
                                });
                    });
        }
        return START_STICKY;
    }

    void notify(int progress, int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Downloading..")
                .setProgress(100, progress, false)
                .setOngoing(true)
                .setAutoCancel(false);

        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(id, builder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
