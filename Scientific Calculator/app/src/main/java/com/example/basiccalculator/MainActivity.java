package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, equal,dec, add, minus, div, mult,clean,back,log,sin,cos,tan,ln,exp,pi,per,com,pow,sqr;
    private TextView t1, t2;
    public String a, b, u = "",si="",con="", e = "Math error";
    char ch=' ';
    double exp1=Math.exp(1),v=0.0,pie=Math.PI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        equal =findViewById(R.id.equal);
        dec=findViewById(R.id.dec);
        add = findViewById(R.id.add);
        minus =findViewById(R.id.minus);
        div = findViewById(R.id.div);
        mult =findViewById(R.id.mult);
        clean =findViewById(R.id.clean);
        back =findViewById(R.id.back);
        log =findViewById(R.id.log);
        sin =findViewById(R.id.sin);
        cos=findViewById(R.id.cos);
        tan =findViewById(R.id.tan);
        ln=findViewById(R.id.ln);
        exp=findViewById(R.id.exp);
        pi=findViewById(R.id.pi);
        per=findViewById(R.id.permutation);
        com=findViewById(R.id.combination);
        pow=findViewById(R.id.power);
        sqr=findViewById(R.id.fact);


        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textView2);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "0";
                con=si+u;
                t1.setText(con);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "1";
                con=si+u;
                t1.setText(con);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "2";
                con=si+u;
                t1.setText(con);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "3";
                con=si+u;
                t1.setText(con);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "4";
                con=si+u;
                t1.setText(con);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "5";
                con=si+u;
                t1.setText(con);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "6";
                con=si+u;
                t1.setText(con);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "7";
                con=si+u;
                t1.setText(con);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "8";
                con=si+u;
                t1.setText(con);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "9";
                con=si+u;
                t1.setText(con);
            }
        });
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u=u+"e";
                con=si+u;
                t1.setText(con);

            }
        });
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u=u+"π";
                con=si+u;
                t1.setText(con);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cal();
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u=u+".";
                con=si+u;
                t1.setText(con);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "+";
                con=si+u;
                t1.setText(con);
                ch = '+';

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "-";
                con=si+u;
                t1.setText(con);
                ch = '-';
            }
        });
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "*";
                con=si+u;
                t1.setText(con);
                ch = '*';
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "/";
                con=si+u;
                t1.setText(con);
                ch = '/';
            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t1.setText("");
                u="";
                si="";
                con="";
                t2.setText("");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(u==""){
                    si="";
                    con="";
                    t1.setText("");
                    return;
                }

                String s=t1.getText().toString();
                b=s.substring(0,s.length()-1);
                u=u.substring(0,u.length()-1);
                t1.setText(b);

            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                si="log ";
                u="";
                t1.setText(si);
            }
        });
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                si="sin ";
                u="";
                t1.setText(si);
            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                si="cos ";
                u="";
                t1.setText(si);
            }
        });
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                si="tan ";
                u="";
                t1.setText(si);
            }
        });
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                si="ln ";
                u="";
                t1.setText(si);
            }
        });
        sqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Double x=Double.parseDouble(u);
               u=u+"!";
               t1.setText(u);
               x=fact(x);
               u=""+x;
               t2.setText(u);
            }
        });
        per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "P";
                con=si+u;
                t1.setText(con);
                ch = 'P';

            }
        });
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "C";
                con=si+u;
                t1.setText(con);
                ch = 'C';

            }
        });
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = u + "^";
                con=si+u;
                t1.setText(con);
                ch = '^';

            }
        });
    }
    public void cal() {
        if(ch==' ')
            t2.setText(u);

            b = t1.getText().toString();
            if (si.length() > 0) {
                u = change(u);
                v = Double.parseDouble(u);
                double val = 0.0d;
                if (si.equals("log ")) {
                    val = Math.log10(v);
                }
                if (si.equals("ln ")) {
                    val = Math.log(v);
                }
                if (si.equals("sin ")) {
                    val = Math.sin(Math.toRadians(v));
                }
                if (si.equals("cos "))
                    val = Math.cos(Math.toRadians(v));
                if (si.equals("tan ")) {
                    if (v == 90) {
                        t2.setText(e);
                        return;
                    }
                    if (v == 45) {
                        t2.setText("1");
                        return;
                    }


                    val = Math.tan(Math.toRadians(v));
                }
                a = "" + val;
                t2.setText(a);
                si = "";
                u = "";
                return;


            }
            if (u.equals("e")) {

                String x = "" + exp1;
                t2.setText(x);
                return;
            }
            if (u.equals("π")) {

                String x = "" + pie;
                t2.setText(x);
                return;
            }

            b = change(b);
            double a1 = Double.parseDouble(b.substring(0, b.indexOf(ch)));
            if(u.charAt(u.length()-1)==ch)
                Toast.makeText(getApplicationContext(),"Operand missing", Toast.LENGTH_LONG).show();
            else {
                double a2 = Double.parseDouble(b.substring(b.indexOf(ch) + 1));
                if (ch == '+')
                    a1 = a1 + a2;
                if (ch == '-')
                    a1 = a1 - a2;
                if (ch == '*')
                    a1 = a1 * a2;
                if (ch == '/') {
                    if (a2 == 0) {

                        Toast.makeText(getApplicationContext(), e, Toast.LENGTH_LONG).show();
                        return;
                    } else
                        a1 = a1 / a2;
                }
                if (ch == 'P') {
                   if(a2>a1){
                       Toast.makeText(getApplicationContext(),"Not possible", Toast.LENGTH_LONG).show();
                       return;}

                    if(a1==a2)
                        a1=1.0;
                    else{
                    Double a = fact(a1);
                    Double x = a1 - a2;
                    Double b = fact(x);
                    a1 = a / b;}
                }
                if (ch == 'C') {
                    if(a2>a1){
                        Toast.makeText(getApplicationContext(),"Not possible", Toast.LENGTH_LONG).show();
                        return;}
                    if(a1==a2)
                        a1=1.0;
                    else{
                    Double a = fact(a1);
                    Double c = fact(a2);
                    Double x = a1 - a2;
                    Double b = fact(x);
                    a1 = a / (b * c);}
                }
                if (ch == '^') {
                    a1 = Math.pow(a1, a2);
                }
                a = "" + a1;
                t2.setText(a);
                u = a;
            }
        }

        public String change(String t)
    {
        String v=""+exp1,val="",ti=""+pie;
        int p=0;
        for(int i=0;i<t.length();i++)
        {
            char ch=t.charAt(i);
            if(ch=='e')
            {
                val=val+t.substring(p,i)+v;
                p=i+1;
            }
            if(ch=='π')
            {
                val=val+t.substring(p,i)+ti;
                p=i+1;
            }
        }
        val=val+t.substring(p);
        return val;
    }
    public Double fact(Double a)    {
        if(a==0)
            return 1.0;

        Double i, f=1.0;
        for(i=2.0;i<=a;i++)
            f=f*i;
        return f;
    }
}

