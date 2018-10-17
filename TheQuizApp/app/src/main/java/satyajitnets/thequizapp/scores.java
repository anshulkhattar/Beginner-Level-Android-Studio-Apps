package satyajitnets.thequizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class scores extends Activity {

   String com_cor,com_t; //store the points
     String link;
     AlertDialog dialog;
     String username,password;
    Toast toast;
    LayoutInflater inflater;
    View layout;
Context con;
     TextView text,t,t4,t5,t6,r8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        setContentView(R.layout.activity_highscore);

        Typeface fn = Typeface.createFromAsset(getAssets(), "fonts/fnt.ttf");

con=this;


        inflater = getLayoutInflater();

        layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        text = (TextView) layout.findViewById(R.id.TMsg);
        text.setTypeface(fn);


        toast = new Toast(this);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);






         t=findViewById(R.id.textViewTitleEasy);
         t4=findViewById(R.id.textViewCorrectEasy);
         t5=findViewById(R.id.textViewCorrectEasyRez);
         t6=findViewById(R.id.textViewTotalEasy);
         r8=findViewById(R.id.textViewAccuracyEasy);


        t.setTypeface(fn);

        t4.setTypeface(fn);
        t5.setTypeface(fn);
        t6.setTypeface(fn);

r8.setTypeface(fn);

get();
        new MyTask().execute("my string parameter");

    }


    String dec(String s){
        String text="";
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }



void get(){



    SharedPreferences shared = getSharedPreferences("First_Run", MODE_PRIVATE);

    username = dec(dec((shared.getString("user",""))));
    password = dec(dec(dec((shared.getString("ps","")))));


password=password.trim();
username=username.trim();


}


    void msg(String message){



        text.setText(message);


        toast.show();
    }

 void setV(){
        //set the values to text fields
t4.setText(t4.getText()+" "+com_cor);

    t6.setText(t6.getText()+" "+com_t);


     if(Integer.parseInt(com_t)==0){
         r8.setText(r8.getText()+" 0%");
     }
    else {
         float ac1 = (Float.parseFloat(com_cor) / Float.parseFloat(com_t) * 100);


         r8.setText(r8.getText() +" "+ String.valueOf(ac1) + "%");

     }




}



    private  class MyTask extends AsyncTask<String, Integer, String> {











        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {


            // Do something like display a progress bar

            dialog =new SpotsDialog.Builder()
                    .setContext(con)
                    .setTheme(R.style.Custom3)
                    .build();

            dialog.show();


        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {

            StringBuffer sb=new StringBuffer("");
            link = "http://satyajiit.xyz/quiz/getPts.php?user="+username+"&pass="+password;

            try {


                URL url = new URL(link);
                Log.d("CSE", "back");
                HttpClient client = new DefaultHttpClient();
                Log.d("CSE", "back2");
                HttpGet request = new HttpGet();
                Log.d("CSE", "back3");
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));


                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();




            }
            catch(Exception e){
                sb=new StringBuffer("sads");
                Log.d("CSE", String.valueOf(e));
            }





            return String.valueOf(sb);
        }

        // This is called from background thread but runs in UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // Do things like update the progress bar
        }

        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(result.equals("FAIL")) { msg("Failed to load..error code 100");  }
            else if(result.contains("satya")) {


               String total=result.substring(5,result.indexOf("comp"));

String cor=result.substring((result.lastIndexOf("comp"))+4);

               com_cor=cor;
               com_t=total;




                    setV();

                           }
            else
                msg("Network Error or Server Down..please Try Again");


            // Do things like hide the progress bar or change a TextView
        }
    }








}
