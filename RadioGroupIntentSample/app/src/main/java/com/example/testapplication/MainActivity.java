package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private RadioGroup rg;
    private RadioButton rb;
    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editTextName);
        rg=findViewById(R.id.rg);
        sub=findViewById(R.id.submitButton);


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameEntered=name.getText().toString();

                StringBuffer newName=new StringBuffer();

                int i=rg.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(i);

                if(rb.getText().toString().equals("Male")){
                    newName.append("Mr. ");
                }
                else{
                    newName.append("Ms. ");
                }

                String arr_name[]=nameEntered.split(" ");

                for(int j=0;j<arr_name.length-1;j++){
                    newName.append(arr_name[j].charAt(0)+". ");
                }

                newName.append(arr_name[arr_name.length-1]);

                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("finalName",newName.toString());
                startActivity(intent);
            }
        });
    }
}
