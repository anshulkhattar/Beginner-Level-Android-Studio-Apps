package com.basicwebview.webviewimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loadURL;
    EditText editTextURL;
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadURL = findViewById(R.id.loadurlbt);
        editTextURL = findViewById(R.id.edurl);
        myWebView = (WebView) findViewById(R.id.mywebview);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(editTextURL.getText().toString());

        loadURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextURL.getText().toString().matches("")) {
                    myWebView.loadUrl(editTextURL.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Please enter URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

