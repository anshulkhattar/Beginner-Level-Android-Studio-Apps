package satyajitnets.thequizapp;

import android.app.Activity;

import android.content.Intent;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;


import android.view.View;

import android.view.animation.Animation;

import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



public class game extends Activity implements Animation.AnimationListener {


String keys="";
    TextView tv1,timer;
    Button optn1;
    Animation animOUT;
    Animation animIN,animCOR,animVert,animWRG,animST;
    Button optn2;
    Button optn3;
    int answer;
    Button optn4;
    Intent i;
    int upd;
    NodeList nList;
    int time;
    public int counter;

    static MediaPlayer player=null;
    static CountDownTimer TimeC;


    private static final int NUMBER_OF_ANIMATIONS = 1;
    private int animationFinishedCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
       tv1=findViewById(R.id.questionTextView);
        optn1=findViewById(R.id.answerAButton);
optn2=findViewById(R.id.answerBButton);
optn3=findViewById(R.id.answerCButton);
                                optn4=        (Button)findViewById(R.id.answerDButton);

        Typeface fn = Typeface.createFromAsset(getAssets(), "fonts/fnt.ttf");

         i=new Intent(this,results.class);


        upd=0;

        animOUT= AnimationUtils.loadAnimation(this, R.anim.anim_translate_out);

        animIN= AnimationUtils.loadAnimation(this, R.anim.anim_translate_in);

        animVert=AnimationUtils.loadAnimation(this, R.anim.anim_translate_vertical);


        animST= AnimationUtils.loadAnimation(this, R.anim.anim_rotate_and_scale);


        animWRG= AnimationUtils.loadAnimation(this, R.anim.anim_wrong);




        try {
            InputStream is = null;
is=openFileInput("q.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            nList = doc.getElementsByTagName("question");

        }
        catch (Exception e) {Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show();}

























timer=(TextView)findViewById(R.id.timerTextView);
timer.setTypeface(fn);
        tv1.setTypeface(fn);
        optn1.setTypeface(fn);
        optn2.setTypeface(fn);
        optn3.setTypeface(fn);
        optn4.setTypeface(fn);




        animStart();





showQ(0);

CountTimer();
    }

    @Override
    public void onBackPressed()
    {





        CustomDialogClass cdd=new CustomDialogClass(this,"Exit","Exiting will permanently remove this question set , Are You sure?",2); //back is pressed
        cdd.show();



    }


    @Override
    public void onResume() {
        // TODO LC: preliminary support for views transitions
        super.onResume();

    }



    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    void showQ(int a){

player=null;

if(upd>=nList.getLength()) {
   // Toast.makeText(this,"show q"+keys,Toast.LENGTH_LONG).show();
keys=keys+'0';
    i.putExtra("keys",keys);
    finish();
    startActivity(i);

}




        try {
         /*   InputStream is = getAssets().open("file.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            nList = doc.getElementsByTagName("question");
            */

            //for (int i=0; i<nList.getLength(); i++) {

            final Node node = nList.item(a); //i

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element2 = (Element) node;
                tv1.setText(getValue("que", element2));

                tv1.setAnimation(animVert);

                optn1.setText(getValue("option1", element2));
                optn2.setText(getValue("option2", element2));
                optn3.setText(getValue("option3", element2));
                optn4.setText(getValue("option4", element2));

                answer=Integer.parseInt(getValue("correct", element2));
                counter=Integer.parseInt(getValue("time", element2));
if(a>0) CountTimer();


            }





listen();





        } catch (Exception e) {e.printStackTrace();
        }



    }



void animStart(){
    optn1.startAnimation(animIN);
    optn2.startAnimation(animIN);
    optn3.startAnimation(animIN);
    optn4.startAnimation(animIN);
    tv1.startAnimation(animVert);
    timer.startAnimation(animST);
}

void ChkAns(int option,View v){


    optn1.setClickable(false);
    optn2.setClickable(false);
    optn3.setClickable(false);
    optn4.setClickable(false);


keys=keys+String.valueOf(option);



        if(option==answer) {

            TimeC.cancel();

            startAnimations(1,v);

                    v.setBackgroundResource(R.drawable.correct_anim_btn);


        }



else {

            TimeC.cancel();
            startAnimations(0,v);
            v.setBackgroundResource(R.drawable.wrong_anim_btn);

        }




}


void listen(){
    optn1.setOnClickListener( new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            ChkAns(1,v);


        }
    });


    optn4.setOnClickListener( new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            ChkAns(4,v);


        }
    });

    optn3.setOnClickListener( new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            ChkAns(3,v);


        }
    });

    optn2.setOnClickListener( new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            ChkAns(2,v);


        }
    });




}





    private void startAnimations(int flag,View v) {




if(flag==1){ //correct option
clickPlay(1);
        animCOR= AnimationUtils.loadAnimation(this, R.anim.anim_correct);
}
else if(flag!=3) {
    clickPlay(0);
    //wrong
    animCOR = AnimationUtils.loadAnimation(this, R.anim.anim_wrong);



}


        animCOR.setAnimationListener(this);

        v.startAnimation(animCOR);


    }
    @Override
    public void onAnimationEnd(Animation animation) {



//upd+=1;

optn4.setBackgroundResource(R.drawable.normal_btn);
        optn3.setBackgroundResource(R.drawable.normal_btn);
        optn2.setBackgroundResource(R.drawable.normal_btn);
        optn1.setBackgroundResource(R.drawable.normal_btn);

        if(upd<nList.getLength())
            showQ(++upd);


        else{
           // Toast.makeText(this,keys,Toast.LENGTH_LONG).show();
            i.putExtra("keys",keys);
            finish();
            startActivity(i);
        }

        animStart();

        }


    @Override
    public void onAnimationStart(Animation animation) {
        // Nothing
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // Nothing
    }
void clickPlay(int flag) {


    if (flag == 1) {

        stopPlaying();
        player = MediaPlayer.create(game.this, R.raw.cor);
        player.start();

    } else if (flag == 0) {

        stopPlaying();
        player = MediaPlayer.create(game.this, R.raw.wrg);
        player.start();
    } else {




    }

}

    public static MediaPlayer getPlayer(){
        return  player;
    }


    public static CountDownTimer getTimer(){
        return TimeC;
    }

void CountTimer(){

int dur=(counter*1000)+1000;

    timer.setAnimation(animST);

     TimeC = new CountDownTimer(dur, 1000) {

        public void onTick(long millisUntilFinished) {

            if(counter<=9) timer.setText("0"+String.valueOf(counter));
            else timer.setText(String.valueOf(counter));
            if(counter==5) clickPlay(3);
            counter--;
        }

        public void onFinish() {

           calls();
           clickPlay(0);
            timer.setText("end");
        }
    }.start();


}

void calls(){
    if(upd<nList.getLength()){
        showQ(++upd);
        keys=keys+'0';
       // Toast.makeText(this,"calls yes"+keys,Toast.LENGTH_SHORT).show();
    }

    else{

//Toast.makeText(this,"calls else"+keys,Toast.LENGTH_SHORT).show();
        i.putExtra("keys",keys);
            finish();
            startActivity(i);
        }

    animStart();
}



    private void stopPlaying() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }



}


