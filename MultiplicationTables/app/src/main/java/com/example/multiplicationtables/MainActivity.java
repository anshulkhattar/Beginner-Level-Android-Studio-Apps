package com.example.multiplicationtables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> tableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void changeTable(View view) {
        tableList = new ArrayList<>();
        ListView table  = findViewById(R.id.table);
        SeekBar numbers = findViewById(R.id.numbers);
        int num = numbers.getProgress();
        for(int i = 1; i <= 10; i++) {
            tableList.add(num + " * " + i + " = " + (num * i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tableList);
        table.setAdapter(arrayAdapter);
    }
}