package com.example.sparsh.phone_book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNewRecord extends AppCompatActivity

{
    ImageButton save ;
    ImageView img;
    EditText name,phone,email,address,nick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewrecord);
        save = findViewById(R.id.btsave);
        name = findViewById(R.id.ename);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        nick = findViewById(R.id.nickname);
        img = findViewById(R.id.camera_img);


    }
    public void click(View v )
    {
        String s1="",s2="",s3="",s4="",s5="",s6="";
        switch (v.getId())
        {
            case R.id.btsave:
            {
                if (name.getText().length() >=2) {
                    s1 = name.getText().toString();
                    s2 = phone.getText().toString();
                    s3 = email.getText().toString();
                    s4 = address.getText().toString();
                    s5 = nick.getText().toString();

                    DbHelper obj = new DbHelper(this);
                    obj.saveNewRecord(s1, s2, s3, s4, s5);

                    name.setText(null);
                    phone.setText(null);
                    email.setText(null);
                    address.setText(null);
                    nick.setText(null);

                    finish();
                    startActivity(new Intent(this, MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Contact Saved", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "please enter name", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case R.id.img_back:
            {
                finish();
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Contact Not Saved", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

}
