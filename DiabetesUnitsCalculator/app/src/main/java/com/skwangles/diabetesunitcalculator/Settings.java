package com.skwangles.diabetesunitcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    public String SHAREDPREF = "Values";
    private Button save;
    private Button clear;
    private Button cancel;
    private EditText mmolThreshold;//Apply measurements after this value
    private EditText mmolPerUnit;//How many mmols over threshold to apply 1 measure of Units
    private EditText unitPerMmol;//How many Units per measure of mmols over threshold
    private EditText carbsThreshold; //Apply measurements after this value - carbs
    private EditText carbsPerUnit;  //How many mg over threshold to apply 1 measure of Units
    private EditText unitPerCarbs;  //How many units per measure of Carbs over threshold

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String[] values = new String[]{"mmol", "mmolPerUnit", "unitPerMmol", "carbs", "carbsPerUnit", "unitPerCarb"};
        SharedPreferences.Editor editor = this.getSharedPreferences(SHAREDPREF, MODE_PRIVATE).edit();//Saves values between sessions

        //Mmols
        mmolThreshold = findViewById(R.id.number_mmolthreshold);
        mmolPerUnit = findViewById(R.id.number_overmmol);
        unitPerMmol = findViewById(R.id.number_unitspermmol);
        //Carbs
        carbsThreshold = findViewById(R.id.number_threshold_optional);
        carbsPerUnit = findViewById(R.id.number_carbsperunit);
        unitPerCarbs = findViewById(R.id.number_unitspercarb);
        //Buttons
        save = findViewById(R.id.settings_save);
        cancel = findViewById(R.id.settings_cancel);
        clear = findViewById(R.id.settings_clear);
        EditText[] editTexts = new EditText[]{mmolThreshold, mmolPerUnit, unitPerMmol, carbsThreshold, carbsPerUnit, unitPerCarbs};
        //Checks if there are values to fill
        if(!getSharedPreferences(SHAREDPREF, MODE_PRIVATE).getString(values[0], "").equals("")){
            for(int i = 0; i < values.length; i++){//Sets up the values defined
                editTexts[i].setText(getSharedPreferences(SHAREDPREF, MODE_PRIVATE).getString(values[i], ""));
            }
        }
        //Mmol eventlistener
        save.setOnClickListener(view -> {
            //Checks if boxes all have values
            if (carbsThreshold.getText().toString().equals("")) {
                carbsThreshold.setText("0");
            }
            for (EditText e : editTexts) {
                if (e.getText().toString().equals("")) {
                    Toast.makeText(this, "Please make sure all boxes have a value!", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            editor.putString(values[0], mmolThreshold.getText().toString());

            editor.putString(values[1], mmolPerUnit.getText().toString());

            editor.putString(values[2], unitPerMmol.getText().toString());

            editor.putString(values[3], carbsThreshold.getText().toString());

            editor.putString(values[4], carbsPerUnit.getText().toString());
            editor.putString(values[5], unitPerCarbs.getText().toString());
            editor.apply();
            finish();//Go back to main activity
        });

        clear.setOnClickListener(view -> {
            //Sets all text boxes to empty
            for (EditText e : editTexts) {
                e.setText("");
            }
        });

        cancel.setOnClickListener(view -> {
            if (this.getSharedPreferences(SHAREDPREF, MODE_PRIVATE).getString(values[0], "").equals("")) {
                Toast.makeText(this, "Please enter values for first-time setup!", Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
        });
    }


}