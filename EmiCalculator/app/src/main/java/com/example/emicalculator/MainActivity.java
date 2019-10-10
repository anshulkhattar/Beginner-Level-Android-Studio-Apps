package com.example.emicalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
EditText loan,months;
Button calculate;
TextView answer;
 float RATE=10;
 float intrest=RATE/12/100;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        loan=findViewById(R.id.loan);
        calculate=findViewById(R.id.cal);
        months=findViewById(R.id.number);
        answer=findViewById(R.id.answer);
        calculate.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(MainActivity.this, String.valueOf(loan.getText()), Toast.LENGTH_SHORT).show();
                   //     float a=Float.valueOf(String.valueOf(loan.getText()))*intrest;
                        float a= (float) (Float.valueOf(String.valueOf(loan.getText()))*intrest*Math.pow(1+intrest,Float.valueOf(String.valueOf(months.getText())))/(Math.pow(1+intrest,Float.valueOf(String.valueOf(months.getText())))-1));
                        Toast.makeText(MainActivity.this, String.valueOf(a), Toast.LENGTH_SHORT).show();
                        answer.setText("Your Emi Calculated  for "+String.valueOf(months.getText())+" months is "+" INR "+String.valueOf(a));
                        //Toast.makeText(MainActivity.this, String.valueOf(intrest), Toast.LENGTH_SHORT).show();
                             }
        });
       // toolbar.setSubtitle("Made By Nilesh Teji");
        toolbar.setTitle("Emi Calculator");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Made By Nilesh Teji");
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


         }


}
