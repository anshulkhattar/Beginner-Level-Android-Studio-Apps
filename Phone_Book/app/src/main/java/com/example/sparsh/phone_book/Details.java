package com.example.sparsh.phone_book;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.soundcloud.android.crop.Crop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Details extends AppCompatActivity {

    static int x=0;
    Button save ;
    ImageButton bt2;
    ImageView img;
     EditText name,phone,email,address,nick;
    DbHelper obj = new DbHelper(this);
    static String nam="";
    int RequestCode =0;
    boolean granted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        x = (int)(Math.random()*999+1);
        save = findViewById(R.id.bt2);
        name = findViewById(R.id.ename2);
        phone = findViewById(R.id.phone2);
        email = findViewById(R.id.email2);
        address = findViewById(R.id.address2);
        nick = findViewById(R.id.nickname2);
        img = findViewById(R.id.imgbutton1);
        bt2 = findViewById(R.id.imgbutton2);

        Intent i = getIntent();
        String uname = i.getStringExtra("id");
        ArrayList<String> alist = obj.onContactSearch(uname);

        name.setText(alist.get(0));
        phone.setText(alist.get(1));
        email.setText(alist.get(2));
        address.setText(alist.get(3));
        nick.setText(alist.get(4));

        name.setFocusable(false);
        phone.setFocusable(false);
        email.setFocusable(false);
        address.setFocusable(false);
        nick.setFocusable(false);


    }
    public void action (View view)
    {
        switch (view.getId())
        {
            case R.id.bt2:
            {
                if(save.getText().toString().equalsIgnoreCase("save"))
                {
                    obj.update(name.getText().toString(), phone.getText().toString(), email.getText().toString(), address.getText().toString(), nick.getText().toString());
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    Toast.makeText(getApplicationContext(), "Contact Updated Succesfully", Toast.LENGTH_SHORT).show();

                }
                else if(save.getText().toString().equalsIgnoreCase("edit"))
                {
                    save.setText("SAVE");
                    nam = name.getText().toString();
                    name.setFocusableInTouchMode(true);
                    phone.setFocusableInTouchMode(true);
                    email.setFocusableInTouchMode(true);
                    address.setFocusableInTouchMode(true);
                    nick.setFocusableInTouchMode(true);
                }

                break;
            }
            case R.id.imgbutton1:
            {
                Crop.pickImage(this);
                break;
            }
            case R.id.imgbutton2:
            {
                startActivity(new Intent (this,MainActivity.class));
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK )
        {
            Uri u = null;
            if (requestCode == Crop.REQUEST_PICK)
            {
              Uri source = data.getData();
              Uri destination = Uri.fromFile(new File(getCacheDir(),x+"cropped"));
              Crop.of(source,destination).asSquare().start(this);
               u = Crop.getOutput(data);
              img.setImageURI(u);
                Toast.makeText(this, u+"", Toast.LENGTH_SHORT).show();

                x++;
            }
            else if (requestCode == Crop.REQUEST_CROP)
            {
                u = Crop.getOutput(data);
                img.setImageURI(u);
                obj.imageSave(name.getText().toString(),u);
                Toast.makeText(this, u+"", Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
