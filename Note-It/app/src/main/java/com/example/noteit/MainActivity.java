package com.example.noteit;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.noteit.MESSAGE";
    Button addNote;
    static ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File files = getFilesDir();
        String[] fileArray = files.list();

        ArrayList<String> arrayList = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        for(String filename : fileArray){

            filename = filename.replace(".txt", "");
            System.out.println(filename);
            adapter.add(filename);
        }

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        addNote = findViewById(R.id.addNote);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = listview.getItemAtPosition(i).toString();
                Intent intent2 = new Intent(getApplicationContext(), ShowNotesActivity.class);
                intent2.putExtra(EXTRA_MESSAGE, item);
                startActivity(intent2);
            }
        });
    }

    public void launchCreateActivity(View view) {

        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }
}