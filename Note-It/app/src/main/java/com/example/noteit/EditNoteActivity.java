package com.example.noteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class EditNoteActivity extends AppCompatActivity {

    EditText content;
    TextView status;
    Button save;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        content = findViewById(R.id.content);
        status = findViewById(R.id.status);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        String heading = intent.getStringExtra(ShowNotesActivity.HEADER_MSG);
        try {
            FileInputStream fin = openFileInput(heading + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = heading + ".txt";
                if(!content.getText().toString().trim().isEmpty()){

                    File dir = getFilesDir();
                    File file = new File(dir, filename);
                    file.delete();

                    try {
                        FileOutputStream fout = openFileOutput(filename, MODE_PRIVATE);
                        fout.write(content.getText().toString().trim().getBytes());
                        fout.close();

                        status.setText("SAVED!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    content.setError("Content can't be empty");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}