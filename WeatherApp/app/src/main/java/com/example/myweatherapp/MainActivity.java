package com.example.myweatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText tv_city;
    private Button btn_go;
    public String url,key  ;
    public URL myurl ;
    public static TextView tv_temp;
    public static TextView tv_humid;
    public static TextView tv_desc ;
    public CardView cd;
    private ImageView front_image;
    private Animation animFadein;
    public ProgressDialog pd;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Whats the weather?");
        tv_city = findViewById(R.id.tv_city);
        btn_go = findViewById(R.id.btn_go);
        myurl = null;
        tv_temp = findViewById(R.id.tv_temp);
        tv_desc = findViewById(R.id.tv_desc);
        tv_humid = findViewById(R.id.tv_humid);
        cd = (CardView)findViewById(R.id.card_view);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Fetching the weather");
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        front_image = findViewById(R.id.front_image);
        front_image.setAnimation(animFadein);
        cd.setAnimation(animFadein);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ClickableViewAccessibility")
    public void getData(final View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tv_city.getWindowToken(), 0);

        btn_go.setOnTouchListener(new View.OnTouchListener() {



            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        view.setElevation((float) 10.2);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        key="your_key_here";
        url = "https://openweathermap.org/data/2.5/weather?q="+ tv_city.getText().toString() + "&appid=" + key;
        try {
            myurl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("API call",url);

        if (tv_city.getText().toString().isEmpty()){
            Snackbar.make(view,"Please enter a city name first !",Snackbar.LENGTH_SHORT).show();
        }
        else {
            pd.show();
            new getWeatherData(this).execute(myurl);

        }

    }
}



