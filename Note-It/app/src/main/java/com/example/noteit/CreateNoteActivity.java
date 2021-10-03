package com.example.noteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateNoteActivity extends AppCompatActivity {

    EditText ETHeading;
    EditText ETContent;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        Intent intent1 = getIntent();

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ETHeading = findViewById(R.id.ETHeading);
                ETContent = findViewById(R.id.ETContent);

                String heading = ETHeading.getText().toString().trim();
                String content = ETContent.getText().toString().trim();

                if(!heading.isEmpty()){

                    if(!content.isEmpty()){

                        try {

                            FileOutputStream fileOutputStream = openFileOutput(heading + ".txt", Context.MODE_PRIVATE);
                            fileOutputStream.write(content.getBytes());
                            fileOutputStream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        MainActivity.adapter.add(heading);
                    }else{
                        ETContent.setError("Content can't be empty");
                    }
                }else{
                    ETHeading.setError("Heading can't be empty");
                }

                ETContent.setText("");
                ETHeading.setText("");

                Toast.makeText(getApplicationContext(), "Note Saved!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}