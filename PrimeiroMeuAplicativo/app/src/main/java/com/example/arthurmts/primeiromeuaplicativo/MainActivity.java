package com.example.arthurmts.primeiromeuaplicativo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private Button butao;
    private CheckBox c1;
    private CheckBox c2;
    private CheckBox c3;
    private Button butao2;
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = findViewById(R.id.checkBox1);
        switch1 = findViewById(R.id.id_cc);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        result = findViewById(R.id.id_result);
        butao = findViewById(R.id.id_button);

        butao2 = findViewById(R.id.id_button2);
        butao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomico  = new Random();
                int numa = randomico.nextInt(10);
                String res = "Numero gerado:"+numa;
                //result.setText("Botao funfando");
                boolean win = false;
                if(c1.isChecked()&& !c2.isChecked()&& !c3.isChecked()) {
                    if(numa<4){
                        win = true;
                    }

                }
                else if (c2.isChecked()&& !c1.isChecked()&& !c3.isChecked()){
                    if(numa>3 && numa<7){
                        win = true;
                    }

                }
                else if (c3.isChecked()&& !c2.isChecked()&& !c1.isChecked()){
                    if(numa>6){
                        win = true;
                    }
                }
                res = "Numero gerado:"+numa;
                if(win){
                    res+="acertou";
                }
                else{
                    res+="erou";
                }

                result.setText(res);
            }
        });
        butao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela2 = new Intent(MainActivity.this, ctela2.class);
                startActivity(tela2);
            }
        });
    }

}
