package satyajitnets.thequizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;

import static android.content.Context.MODE_PRIVATE;

class LoginReg extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public int type;
    CountDownTimer ct;
    Toast toast;
    LayoutInflater inflater;
    View layout;

    String username,password,out;
    public Button yes, no,cancel;
    public TextView message;
    public String msg,title,link,link2;
    public ImageView Img;
    public  MediaPlayer mp;
    TextView t1,t2,t3,t4,head,text;
    EditText b1,b2;
    AlertDialog dialog;


    String cor="0";


    public LoginReg(Activity a,int type) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.type=type;

        //value is 2 - back is pressed
        //val
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        Typeface fn = Typeface.createFromAsset(c.getApplicationContext().getAssets(), "fonts/fnt.ttf");



        b1=findViewById(R.id.userName);
        b2=findViewById(R.id.paswrd);
        b1.setTypeface(fn);
        b2.setTypeface(fn);

        inflater = getLayoutInflater();

        layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        text = (TextView) layout.findViewById(R.id.TMsg);
        text.setTypeface(fn);



        toast = new Toast(c);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);



        t1=findViewById(R.id.userName);

        t2=findViewById(R.id.passText);

        t4=findViewById(R.id.reg);

        head=findViewById(R.id.title2);

        t3=findViewById(R.id.newTit);

        t1.setTypeface(fn);
        t2.setTypeface(fn);
        t4.setTypeface(fn);
        t3.setTypeface(fn);
        head.setTypeface(fn);





        yes =  findViewById(R.id.rts);



        yes.setOnClickListener(this);
yes.setTypeface(fn);

        if(type==0){

            head.setText("Sign Up Easy ");
            yes.setText("Sign Up");
            t4.setText("Login");
            t4.setTextColor(c.getResources().getColor(R.color.green));

        }




    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.rts:
            {
                username = String.valueOf(b1.getText());

                password = String.valueOf(b2.getText());





                if(TextUtils.isEmpty(b1.getText().toString())&&TextUtils.isEmpty(b2.getText().toString()))
                {
                    msg("Please Fill all the fields!");
                    return;
                }
                else {


                    if(username.contains(" ")) { msg("Username cannot have spaces"); return; }

                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(username);
                    boolean b = m.find();

                    if (b)
                        msg("Username cannot contain special characters!");

                    else {


                        if(username.length()<5) { msg("Username must be atleast of 5 letters long!"); return; }
                        if(password.length()<5) { msg("Password must be atleast of 5 letters long!"); return; }
if(username.contains("satyajit")||username.contains("SATYAJIT")||username.toLowerCase().contains("Satyajit")){ msg("Sorry Cannot use that name"); return; }

if(password.contains(" ")) {

                            msg("Sorry You cannot use space in password"); return;

                        }



                        if(type!=0) {



                            InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);

                            new MyTask().execute("my string parameter");

                        }
                   else Register();

                    }

                    }

            }
            break;

            default:
                break;
        }
        //dismiss();
    }


    private class MyTask extends AsyncTask<String, Integer, String> {

      // private AlertDialog dialog = new SpotsDialog(c,"",true);









        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {


            // Do something like display a progress bar

            dialog =new SpotsDialog.Builder()
                    .setContext(c)
                    .setTheme(R.style.Custom)
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
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
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
if(result.equals("FAIL")) {msg("Invalid UserId or Password");  }
else if(result.contains("satya")) {
                out=result.substring(result.indexOf("comp")+4);

                msg("Logged In..You have "+out+" points");        save();   Intent intent = c.getIntent();   c.finish();  c.startActivity(intent);        }
else
    msg("Network Error or Server Down..please Try Again");


            // Do things like hide the progress bar or change a TextView
        }
    }

    void msg(String message){



        text.setText(message);


        toast.show();
    }


    void save(){





        //save the the details to the preferances

        SharedPreferences.Editor editor = c.getSharedPreferences("First_Run", MODE_PRIVATE).edit();


        editor.putString("user", enc(enc(username)));   //encrypting with base64


        editor.putString("ps", enc(enc(enc(password))));

        editor.putInt("run", 1);
        editor.apply();

    }
    void Register(){
        //msg(username);
        new Regis().execute("my string parameter");
    }



    private class Regis extends AsyncTask<String, Integer, String> {


        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {


            dialog =new SpotsDialog.Builder()
                    .setContext(c)
                    .setTheme(R.style.regis)
                    .build();

            dialog.show();


        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {

            StringBuffer sb2=new StringBuffer("");

            link2 = "http://satyajiit.xyz/quiz/newUser.php?user="+username+"&pass="+password+"&cor="+cor;

            try {

                URL url2 = new URL(link2);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link2));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));


                String line2 = "";

                while ((line2 = in.readLine())!=null) {
                    sb2.append(line2);
                    break;
                }

                in.close();



            }
            catch(Exception e){
                sb2=new StringBuffer("sads");

            }





            return String.valueOf(sb2);
        }

        // This is called from background thread but runs in UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // Do things like update the progress bar
        }

        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result2) {


            super.onPostExecute(result2);

            if (dialog.isShowing()) dialog.dismiss();



            if(result2.contains("Exist")) { msg("UserID already Exist..Please Choose new");  }
            else if(result2.contains("Success")) {



                msg("Successfully Created...Welcome!");        save();       Intent intent = c.getIntent();   c.finish();  c.startActivity(intent);


            }
            else msg("Network Error or Server Down..please Try Again");




            // Do things like hide the progress bar or change a TextView
        }
    }
String enc(String s){
    byte[] data = new byte[0];
    try {
        data = s.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    String base64 = Base64.encodeToString(data, Base64.DEFAULT);
    return base64;
}



}
