/*
 * Coded by justudin <www.github.com/justudin>
 * MIT (c) 2018.
 */

package com.justudin.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.justudin.calculator.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txt_layar, proses_layar;
    int op;
    double angka1, angka2;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // init
        init();
    }

    public void init() {
        //disable keyboad on layar
        binding.txtLayar.setKeyListener(null);
        binding.prosesLayar.setKeyListener(null);

        //setOnclick
        binding.satu.setOnClickListener(this);
        binding.dua.setOnClickListener(this);
        binding.tiga.setOnClickListener(this);
        binding.empat.setOnClickListener(this);
        binding.lima.setOnClickListener(this);
        binding.enam.setOnClickListener(this);
        binding.tuj.setOnClickListener(this);
        binding.delapan.setOnClickListener(this);
        binding.sembilan.setOnClickListener(this);
        binding.nol.setOnClickListener(this);
        binding.koma.setOnClickListener(this);
        binding.plus.setOnClickListener(this);
        binding.min.setOnClickListener(this);
        binding.bagi.setOnClickListener(this);
        binding.kali.setOnClickListener(this);
        binding.c.setOnClickListener(this);
        binding.sd.setOnClickListener(this);
        binding.sd.setEnabled(false);
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View v) {
        switch (v.getId()) {
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
                switch (op) {
                    case 1:
                        txt_layar.setText(n.format(angka1 + angka2));
                        proses_layar.setText(angka1 + "+" + angka2);
                        break;
                    case 2:
                        txt_layar.setText(n.format(angka1 - angka2));
                        proses_layar.setText(n.format(angka1) + "-" + n.format(angka2));
                        break;
                    case 3:
                        txt_layar.setText(n.format(angka1 * angka2));
                        proses_layar.setText(n.format(angka1) + "x" + n.format(angka2));
                        break;
                    case 4:
                        txt_layar.setText(n.format(angka1 / angka2));
                        proses_layar.setText(n.format(angka1) + "÷" + n.format(angka2));
                        break;
                }
                break;
            case R.id.c:
                clear();
                break;

        }
    }


    private void addAngka(String angka) {
        String txt = txt_layar.getText().toString();
        txt += angka;

        txt_layar.setText(txt);

    }

    private void statusBtn(Boolean sts) {
        binding.sd.setEnabled(true);
        binding.kali.setEnabled(sts);
        binding.bagi.setEnabled(sts);
        binding.min.setEnabled(sts);
        binding.plus.setEnabled(sts);
    }

    private void clear() {
        statusBtn(true);
        proses_layar.setText("");
        txt_layar.setText("");
    }
}
