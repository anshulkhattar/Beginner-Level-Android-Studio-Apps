package com.dotplays.customspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner spPart;
    private SeekBar mSeekBar;
    final List<Part> partList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spPart = findViewById(R.id.spPart);
        mSeekBar = findViewById(R.id.seekBar);

        mSeekBar.setMax(21);
        mSeekBar.setProgress(-1);
        // tao ra 1 doi tuong empty de hien thi mac dinh cho spinner

        Part empty = new Part();
        empty.id = -1;
        empty.name = "Select One";
        partList.add(empty);


        for (int i = 0; i < 20; i++) {
            Part part = new Part();
            part.id = i;
            part.name = "Spinner ".concat(String.valueOf(i));

            partList.add(part);

        }


        MySpinnerAdapter mySpinnerAdapter = new MySpinnerAdapter(this, partList);
        spPart.setAdapter(mySpinnerAdapter);


        // su kien chon 1 hang trong spinner
        spPart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // nguoi dung click va chon 1 hang trong danh sach
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Part part = partList.get(position);
                Toast.makeText(MainActivity.this, part.name, Toast.LENGTH_SHORT).show();

            }

            // neu nguoi dung mo spinner ma ko chon gi
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < partList.size() && progress > -1) {
                    spPart.setSelection(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void deleteSelectedItem(View view) {
        int moveToNextPosition = spPart.getSelectedItemPosition();
        if (spPart.getSelectedItemPosition() == spPart.getCount() - 1) {
            moveToNextPosition--;
        }
        Part part = (Part) spPart.getSelectedItem();
        partList.remove(part);
        MySpinnerAdapter mySpinnerAdapter = new MySpinnerAdapter(this, partList);
        spPart.setAdapter(mySpinnerAdapter);
        Toast.makeText(MainActivity.this, "Your item was removed", Toast.LENGTH_SHORT).show();
        spPart.setSelection(moveToNextPosition);
        mSeekBar.setMax(partList.size());
    }

    public void addNewItem(View view) {
        Part newItems = new Part();
        int createNewestId = partList.get(partList.size()-1).id + 1;
        newItems.id = createNewestId;
        newItems.name = "Spinner " + createNewestId;
        partList.add(newItems);
        MySpinnerAdapter mySpinnerAdapter = new MySpinnerAdapter(this, partList);
        spPart.setAdapter(mySpinnerAdapter);
        Toast.makeText(MainActivity.this, "Your item was added", Toast.LENGTH_SHORT).show();
        spPart.setSelection(spPart.getCount()-1);
        mSeekBar.setMax(partList.size());
    }
}
