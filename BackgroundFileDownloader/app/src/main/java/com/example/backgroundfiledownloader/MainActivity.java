package com.example.backgroundfiledownloader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    Button download;
    ProgressBar cB, cB1, cB2;
    boolean isDownloading = false;
    int permAll;

    private final String[] perms = {Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download = findViewById(R.id.download);
        cB = findViewById(R.id.cB);
        cB1 = findViewById(R.id.cB1);
        cB2 = findViewById(R.id.cB2);

        for (String perm : perms) {
            if (Build.VERSION.SDK_INT >= M) {
                permAll += checkSelfPermission(perm);
            }
        }

        if (Build.VERSION.SDK_INT >= M) {
            if (permAll == PackageManager.PERMISSION_GRANTED) {
                doAll();
            } else {
                requestPermissions(perms, 23);
            }
        }
    }

    void doAll() {
        download.setOnClickListener(isDownloading ? v -> {
            isDownloading = false;
            download.setText("Download");
            DownloadService.shouldRun = false;
            DownloadService.progress = new int[3]; //reset
        } : vx -> {
            download.setText("Stop");
            isDownloading = true;
            DownloadService.shouldRun = true;
            startService(new Intent(this, DownloadService.class));
        });

        Handler h = new Handler(Looper.getMainLooper());
        h.post(new Runnable() {
            @Override
            public void run() {
                if (isDownloading) {
                    cB.setProgress(DownloadService.progress[0]);
                    cB1.setProgress(DownloadService.progress[1]);
                    cB2.setProgress(DownloadService.progress[2]);
                }
                h.postDelayed(this, 500);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int i = 0;
        for (int x : grantResults) {
            i += x;
        }
        if (i != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            doAll();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
