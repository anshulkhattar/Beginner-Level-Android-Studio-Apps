package satyajitnets.thequizapp;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public int value;
    CountDownTimer ct;
    public Button yes, no,cancel;
    public TextView message,Tview;
    public String msg,title;
public ImageView Img;
public  MediaPlayer mp;
    public MediaPlayer player=null;
    public CustomDialogClass(Activity a,String t,String m,int type) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.value=type;
        this.msg=m;
        this.title=t;
        //value is 2 - back is pressed
        //val
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_dialog);

        Typeface fn = Typeface.createFromAsset(c.getApplicationContext().getAssets(), "fonts/fnt.ttf");




message= findViewById(R.id.title) ;
        Tview= findViewById(R.id.message) ;

        Img= findViewById(R.id.imageViewIcon);

        yes = findViewById(R.id.buttonPossitive);
        no =  findViewById(R.id.buttonNegative);
        cancel=findViewById(R.id.buttonCancel);
message.setTypeface(fn);
        Tview.setTypeface(fn);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        cancel.setOnClickListener(this);


        yes.setTypeface(fn);
        no.setTypeface(fn);
        cancel.setTypeface(fn); //understand in the typeface class

message.setText(title);
Tview.setText(msg);

        if(value==2){
            //back is pressed
            Img.setImageResource(R.drawable.sad);
     ct=game.getTimer();
mp=game.getPlayer();
            cancel.setVisibility(View.GONE);

        }
        else if(value==3){
            //exit the app
            Img.setImageResource(R.drawable.warning);
            cancel.setVisibility(View.GONE);
        }
else if(value==1){

no.setText("Okay");
    yes.setVisibility(View.GONE);
    cancel.setVisibility(View.GONE);
}
else if(value==4){
            //subjects screen
            no.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            yes.setText("Ok");
        }

    }

    @Override
    public void onClick(View v) {

        play();

        switch (v.getId()) {
            case R.id.buttonPossitive:
            {
if(value==3){
    android.os.Process.killProcess(android.os.Process.myPid());
    System.exit(1); //user clicked exit ..end the app
}


               else if(value==2){
                    //Yes is clicked a after back is clicked
                    ct.cancel();  //cancel the game timer
                    if(mp!= null) { //stop the bg music
                        mp.stop();  mp.release();
                        mp = null;
                    }
                }

                c.finish();
            }
                break;
            case R.id.buttonNegative:
            {

                dismiss();
            }
                break;
            default:
                break;
        }
        dismiss();
    }


    private void stopPlaying() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
void play(){
    stopPlaying();
    player = MediaPlayer.create(c, R.raw.click);
    player.start();
}



}
