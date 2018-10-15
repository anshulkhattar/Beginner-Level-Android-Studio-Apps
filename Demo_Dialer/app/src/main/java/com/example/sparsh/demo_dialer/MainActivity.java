package com.example.sparsh.demo_dialer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,star,backspace,call;
    EditText e1;
    int REQUEST_CALL=1;
    Vibrator vb;
    static TextToSpeech t1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b0 = findViewById(R.id.bt0);
        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        b3 = findViewById(R.id.bt3);
        b4 = findViewById(R.id.bt4);
        b5 = findViewById(R.id.bt5);
        b6 = findViewById(R.id.bt6);
        b7 = findViewById(R.id.bt7);
        b8 = findViewById(R.id.bt8);
        b9 = findViewById(R.id.bt9);
        star = findViewById(R.id.b_star);
        call = findViewById(R.id.b_call);
        backspace = findViewById(R.id.b_backspace);
        e1 = findViewById(R.id.et1);

        createTTS();

        backspace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                e1.setText(null);
                return false;
            }
        });



    }
    public void createTTS()
    {
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS)
                {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }
    public void onPress(View v) {
        switch (v.getId()) {
            case R.id.bt0: {
                e1.setText(e1.getText().toString() + "0");
                String str = "0";
                speak();
                t1.speak(str,TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt1: {
                e1.setText(e1.getText().toString() + "1");
                speak();
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                t1.speak("1",TextToSpeech.QUEUE_FLUSH,null,null);
                break;
            }
            case R.id.bt2: {
                e1.setText(e1.getText().toString() + "2");
                speak();
                t1.speak("2",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt3: {
                e1.setText(e1.getText().toString() + "3");
                speak();
                t1.speak("3",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt4: {
                e1.setText(e1.getText().toString() + "4");
                speak();
                t1.speak("4",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt5: {
                e1.setText(e1.getText().toString() + "5");
                t1.speak("5",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt6: {
                e1.setText(e1.getText().toString() + "6");
                speak();
                t1.speak("6",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt7: {
                e1.setText(e1.getText().toString() + "7");
                speak();
                t1.speak("7",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt8: {
                e1.setText(e1.getText().toString() + "8");
                speak();
                t1.speak("8",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.bt9: {
                e1.setText(e1.getText().toString() + "9");
                speak();
                t1.speak("9",TextToSpeech.QUEUE_FLUSH,null,null);
                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                break;
            }
            case R.id.b_star: {
                e1.setText(e1.getText().toString() + "*");
                speak();
                t1.speak("star",TextToSpeech.QUEUE_FLUSH,null,null);
                break;
            }
            case R.id.b_hash: {
                e1.setText(e1.getText().toString() + "#");
                speak();
                t1.speak("hash",TextToSpeech.QUEUE_FLUSH,null,null);
                break;
            }
            case R.id.b_backspace:{
                speak();
                if(e1.getText().length()!=0)
                {
                    String y = e1.getText().toString().substring(0,e1.getText().length()-1);
                    e1.setText(y);
                }
                break;
            }

            case R.id.b_call: {
                t1.speak("calling",TextToSpeech.QUEUE_FLUSH,null,null);
                speak();
                makePhoneCall();
                break;
            }
        }
    }
    public void speak()
    {
        if (t1==null)
        {
            Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();
        }
        else {
            while (t1.isSpeaking()) {

            }
        }
    }

        public void makePhoneCall()
        {
        String number = e1.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(MainActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        if (t1!=null) {
        t1.stop();
        t1.shutdown();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        createTTS();
        super.onResume();
    }
}

