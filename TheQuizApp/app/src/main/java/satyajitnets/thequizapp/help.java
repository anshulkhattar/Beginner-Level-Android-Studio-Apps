package satyajitnets.thequizapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class help extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.anim_translate_in, R.anim.anim_translate_out);
        setContentView(R.layout.activity_help);

        Typeface fn = Typeface.createFromAsset(getAssets(), "fonts/fnt.ttf");


        TextView v1=findViewById(R.id.textViewTitle);
        TextView v2=findViewById(R.id.textViewHelp1);
        TextView v3=findViewById(R.id.textViewHelp2);
        TextView v4=findViewById(R.id.textViewHelp3);
        TextView v5=findViewById(R.id.textViewHelp4);
        TextView v6=findViewById(R.id.textViewHelp5);
        TextView v7=findViewById(R.id.textViewHelp6);
        v1.setTypeface(fn);
        v2.setTypeface(fn);
        v3.setTypeface(fn);
        v4.setTypeface(fn);
        v5.setTypeface(fn);
        v6.setTypeface(fn);
        v7.setTypeface(fn);

        v6.setMovementMethod(LinkMovementMethod.getInstance());
        v6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("http://satyajiit.xyz"));
                startActivity(browserIntent);

            }
        });

        v7.setMovementMethod(LinkMovementMethod.getInstance());
        v7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("http://satyajiit.xyz"));
                startActivity(browserIntent);
            }
        });



    }
}
