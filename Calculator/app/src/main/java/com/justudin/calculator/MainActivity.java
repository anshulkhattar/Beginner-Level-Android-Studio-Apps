/*
 * Coded by justudin <www.github.com/justudin>
 * MIT (c) 2018.
 */

package com.justudin.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.text.NumberFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button satu,dua,tiga,empat,lima,enam,tujuh,delapan,sembilan,nol,samadengan,bagi,kali,tambah,kurang,koma,clear;
    EditText txt_layar,proses_layar;
    int op;
    double angka1, angka2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init
        init();
    }

    public void init()
    {
        //layar
        txt_layar = (EditText) findViewById(R.id.txt_layar);
        proses_layar = (EditText) findViewById(R.id.proses_layar);
        //angka
        satu = (Button) findViewById(R.id.satu);
        dua = (Button) findViewById(R.id.dua);
        tiga = (Button) findViewById(R.id.tiga);
        empat = (Button) findViewById(R.id.empat);
        lima = (Button) findViewById(R.id.lima);
        enam = (Button) findViewById(R.id.satu);
        tujuh = (Button) findViewById(R.id.tuj);
        delapan = (Button) findViewById(R.id.delapan);
        sembilan = (Button) findViewById(R.id.sembilan);
        nol = (Button) findViewById(R.id.nol);
        koma = (Button) findViewById(R.id.koma);

        //operasi
        tambah = (Button) findViewById(R.id.plus);
        bagi = (Button) findViewById(R.id.bagi);
        kurang = (Button) findViewById(R.id.min);
        kali = (Button) findViewById(R.id.kali);
        samadengan = (Button) findViewById(R.id.sd);
        clear = (Button) findViewById(R.id.c);

        //disable keyboad on layar
        txt_layar.setKeyListener(null);
        proses_layar.setKeyListener(null);

        //setOnclick
        satu.setOnClickListener(this);
        dua.setOnClickListener(this);
        tiga.setOnClickListener(this);
        empat.setOnClickListener(this);
        lima.setOnClickListener(this);
        enam.setOnClickListener(this);
        tujuh.setOnClickListener(this);
        delapan.setOnClickListener(this);
        sembilan.setOnClickListener(this);
        nol.setOnClickListener(this);
        koma.setOnClickListener(this);
        tambah.setOnClickListener(this);
        kurang.setOnClickListener(this);
        bagi.setOnClickListener(this);
        kali.setOnClickListener(this);
        clear.setOnClickListener(this);
        samadengan.setOnClickListener(this);
        samadengan.setEnabled(false);
    }

    public void onClick(View v)
    {
        switch(v.getId()){
            case R.id.satu:
                addAngka("1");
                break;
            case R.id.dua:
                addAngka("2");
                break;
            case R.id.tiga:
                addAngka("3");
                break;
            case R.id.empat:
                addAngka("4");
                break;
            case R.id.lima:
                addAngka("5");
                break;
            case R.id.enam:
                addAngka("6");
                break;
            case R.id.tuj:
                addAngka("7");
                break;
            case R.id.delapan:
                addAngka("8");
                break;
            case R.id.sembilan:
                addAngka("9");
                break;
            case R.id.nol:
                addAngka("0");
                break;
            case R.id.koma:
                addAngka(".");
                break;
            case R.id.plus:
                angka1 = Double.parseDouble(txt_layar.getText().toString());
                op = 1;
                txt_layar.setText("");
                statusBtn(false);
                break;

            case R.id.min:
                angka1 = Double.parseDouble(txt_layar.getText().toString());
                op = 2;
                txt_layar.setText("");
                statusBtn(false);
                break;
            case R.id.kali:
                angka1 = Double.parseDouble(txt_layar.getText().toString());
                op = 3;
                txt_layar.setText("");
                statusBtn(false);
                break;
            case R.id.bagi:
                angka1 = Double.parseDouble(txt_layar.getText().toString());
                op = 4;
                txt_layar.setText("");
                statusBtn(false);
                break;
            case R.id.sd:
                angka2 = Double.parseDouble(txt_layar.getText().toString());
                NumberFormat n = NumberFormat.getInstance();
                n.setMaximumFractionDigits(2);
                statusBtn(true);
                switch(op){
                    case 1:
                        txt_layar.setText(n.format(angka1+angka2));
                        proses_layar.setText(Double.toString(angka1)+"+"+Double.toString(angka2));
                        break;
                    case 2:
                        txt_layar.setText(n.format(angka1-angka2));
                        proses_layar.setText(n.format(angka1)+"-"+n.format(angka2));
                        break;
                    case 3:
                        txt_layar.setText(n.format(angka1*angka2));
                        proses_layar.setText(n.format(angka1)+"x"+n.format(angka2));
                        break;
                    case 4:
                        txt_layar.setText(n.format(angka1/angka2));
                        proses_layar.setText(n.format(angka1)+"÷"+n.format(angka2));
                        break;
                }
                break;
            case R.id.c:
                clear();
                break;

        }
    }


    private void addAngka(String angka)
    {
        String txt = txt_layar.getText().toString();
        txt+=angka;

        txt_layar.setText(txt);

    }
    private void statusBtn(Boolean sts)
    {
        samadengan.setEnabled(true);
        kali.setEnabled(sts);
        bagi.setEnabled(sts);
        kurang.setEnabled(sts);
        tambah.setEnabled(sts);
    }

    private void clear()
    {
        statusBtn(true);
        proses_layar.setText("");
        txt_layar.setText("");
    }
}
