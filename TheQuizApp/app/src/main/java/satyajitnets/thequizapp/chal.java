package satyajitnets.thequizapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class chal extends Activity{
    TextView text,t,rx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.anim_translate_in, R.anim.anim_translate_out);
        setContentView(R.layout.chal);

        Typeface fn = Typeface.createFromAsset(getAssets(), "fonts/fnt.ttf");
        t=findViewById(R.id.textViewTitleEasy);
       rx=findViewById(R.id.textViewCorrectEasy);


        t.setTypeface(fn);
rx.setTypeface(fn);



    }

}
