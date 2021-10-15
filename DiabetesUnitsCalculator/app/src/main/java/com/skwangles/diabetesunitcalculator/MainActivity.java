package com.skwangles.diabetesunitcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public String SHAREDPREF = "Values";
    private Button calulate;
    private Button setup;
    private EditText mmol;
    private EditText carbs;
    private TextView outCarb;
    private TextView outMmol;
    private TextView calcMmol;
    private TextView calcCarb;
    String[] values = new String[]{"mmol", "mmolPerUnit", "unitPerMmol", "carbs", "carbsPerUnit", "unitPerCarb"};//Entry names
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(SHAREDPREF, MODE_PRIVATE);
        if (sharedPreferences.getString(values[0], "").equals("")) {
            startSettings();
        }
        else {
            carbs = findViewById(R.id.home_entercarbs_edittext);
            mmol = findViewById(R.id.home_entermmol_edittext);
            outCarb = findViewById(R.id.home_carboutput);
            outMmol = findViewById(R.id.home_mmoloutput);
            calcCarb = findViewById(R.id.home_carbcalc);
            calcMmol = findViewById(R.id.home_mmolcalc);

            //Gets values from settings

            calulate = findViewById(R.id.home_calculate);
            calulate.setOnClickListener(view -> {
                if(sharedPreferences.getString(values[0],"").equals("")){//Checks for empty calculation values6
                    Toast.makeText(this, "Please enter some setup values", Toast.LENGTH_SHORT).show();
                    startSettings();
                    return;
                }

                //Calculations Start
                double[] ratios = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    ratios[i] = Double.parseDouble(sharedPreferences.getString(values[i], "0").equals("")?"0":sharedPreferences.getString(values[i], "0"));
                }
                //Checks if values are empty
                if (mmol.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter a mmol/I value", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (carbs.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter a carbs value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double mmolVal = Double.parseDouble(mmol.getText().toString());
                double carbVal = Double.parseDouble(carbs.getText().toString());
                double unitMeasureMmol = (mmolVal - ratios[0]) / ratios[1];
                double unitsFromMmol = unitMeasureMmol * ratios[2]; //Gets value over threshold, finds how many units worth to use, multiplies by units to find the Output Unit value
                double unitMeasureCarb = (carbVal - ratios[3]) / ratios[4];
                double unitsFromCarbs = unitMeasureCarb * ratios[5];
                //Check fro unneeded
                if (unitsFromMmol <= 0) {
                    outMmol.setText(R.string.youdontneedtotake);
                    String calcM = mmolVal - ratios[0] + "mmol/I is under the threshold. Assuming you have no negative settings.";
                    calcMmol.setText(calcM);
                } else {
                    String outM = "You need to take " + unitsFromMmol + " units!";
                    outMmol.setText(outM);
                    String calcM = mmolVal - ratios[0] + "mmol/I over threshold. So that is " + (unitMeasureMmol) + " measures of Units. Which makes " + unitsFromMmol + " units!";
                    calcMmol.setText(calcM);
                }
                //Check for unneeded
                if (unitsFromCarbs <= 0) {
                    outCarb.setText(R.string.youdontneedtotake);
                    String calcC = mmolVal - ratios[3] + "mg is under the threshold. Assuming you have no negative settings";
                    calcCarb.setText(calcC);
                } else {
                    String outCarbe = "You need to take " + unitsFromCarbs + " units!";
                    outCarb.setText(outCarbe);
                    String calcC = carbVal - ratios[3] + "mg over threshold. So that is " + unitMeasureCarb + " measures of Units. Which makes " + unitsFromCarbs + " units!";
                    calcCarb.setText(calcC);
                }
            });

            setup = findViewById(R.id.home_settings);
            setup.setOnClickListener(view -> startSettings());
        }
    }

    private void startSettings(){
        Intent intent = new Intent(this, Settings.class);
        this.startActivity(intent);
    }

}
