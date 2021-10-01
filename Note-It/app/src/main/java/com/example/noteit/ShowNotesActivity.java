package com.example.noteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class ShowNotesActivity extends AppCompatActivity {

    public static final String HEADER_MSG = "com.example.noteit.MESSAGE";
    public static  String FILE_NAME;

    TextView heading;
    TextView content;
    Button edit;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        content = findViewById(R.id.Content);
        heading = findViewById(R.id.Heading);
        heading.setText(message);

        FILE_NAME = message + ".txt";

        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            String whole = "";

            while((line = reader.readLine()) != null){

                if(whole == ""){

                    whole = whole + line;
                }else{

                    whole = whole + "\n" + line;
                }
            }
            reader.close();

            content.setText(whole);
        }catch (Exception e){
            e.printStackTrace();
        }

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = getFilesDir();
                File file = new File(dir, FILE_NAME);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                file.delete();
                startActivity(intent);
            }
        });

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
                intent.putExtra(HEADER_MSG, message);
                startActivity(intent);
            }
        });
    }
}