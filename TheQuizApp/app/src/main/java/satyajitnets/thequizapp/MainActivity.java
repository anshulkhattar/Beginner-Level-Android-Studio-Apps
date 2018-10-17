package satyajitnets.thequizapp;

/*

The App is inspired from diffrent sources and has combined and used some other OpenSource Projects together.
And this is my first project on openSource.
SatyaJit Pradhan
SatyaJiit0@gmail.com

 */



import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import dmax.dialog.SpotsDialog;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    MediaPlayer player;
    SharedPreferences sharedPref;
    String PREFS_NAME = "First_Run";
    LoginReg cddx;
    String link;
    Toast toast;
    TextView text;
    LayoutInflater inflater;
    View layout;
    AlertDialog dialog;
    Context con;
int flag=1;

    String ver = "1"; //app version

    String username;

    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.anim_translate_in, R.anim.anim_translate_out);
        setContentView(R.layout.activity_main);
        cddx = new LoginReg(this, 1);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int run = prefs.getInt("run", 0);

        username = prefs.getString("user", "blank");

        if (run != 1) {


            cddx.setCancelable(false);



            cddx.show();



        }


        con = this;

        AssetFileDescriptor afd = null;
        try {
            afd = getAssets().openFd("sounds/click.mp3");
            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
        } catch (IOException e) {
            Toast.makeText(this, "Background music file failed to load", Toast.LENGTH_LONG).show();

        }


        Typeface fn = Typeface.createFromAsset(getAssets(), "fonts/fnt.ttf");

        Button b = findViewById(R.id.buttonStart);
        Button b2 = findViewById(R.id.buttonStartChallenge);
        Button b3 = findViewById(R.id.buttonLevel);
        Button b4 = findViewById(R.id.buttonMainScore);
        Button b5 = findViewById(R.id.buttonHelp);
        Button b6 = findViewById(R.id.buttonMoreGames);

        b.setTypeface(fn);
        b2.setTypeface(fn);
        b3.setTypeface(fn);
        b4.setTypeface(fn);
        b5.setTypeface(fn);
        b6.setTypeface(fn);


        inflater = getLayoutInflater();

        layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        text = (TextView) layout.findViewById(R.id.TMsg);


        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);


        text.setTypeface(fn);




    }


    public void startGame(View v) {
        clickPlay();


        new ques().execute();



    }

    public void startChallenge(View v) {
        clickPlay();
        Intent i = new Intent(this, chal.class);
        startActivity(i);

    }


    public void chooseLevel(View v) {
        clickPlay();
        new MyTask().execute("Hello");


    }

    public void chooseMainScore(View v) {
        Intent i = new Intent(this, scores.class);
        startActivity(i);

        clickPlay();
    }

    public void onHelp(View v) {
        Intent i = new Intent(this, help.class);
        startActivity(i);

        clickPlay();
    }

    public void moreGames(View v) {

        CustomDialogClass cdd = new CustomDialogClass(this, "Exit", "Are You sure you want to exit the app?", 3); //back is pressed
        cdd.show();


        clickPlay();
    }

    @Override
    public void onBackPressed() {


        CustomDialogClass cdd = new CustomDialogClass(this, "Exit", "Are You sure you want to exit the app?", 3); //back is pressed
        cdd.show();


        clickPlay();


    }

    void clickPlay() {

        if (player.isPlaying())
            player.stop();

        player.start();
    }

    void firstRun() {

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("subject", "computer");
        editor.putInt("run", 1);
        editor.apply();
    }

    public void regis(View v) {

        if (count % 2 != 0) {
            cddx = new LoginReg(this, 0); //new user regis

            count++;
        } else {

            cddx = new LoginReg(this, 1);
            count++;

        }

        cddx.dismiss();

        cddx.setCancelable(false);
        cddx.show();
    }

    void msg(String message) {

        text.setText(message);


        toast.show();
    }


    private class MyTask extends AsyncTask<String, Integer, String> {




        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {


            // Do something like display a progress bar

            dialog = new SpotsDialog.Builder()
                    .setContext(con)
                    .setTheme(R.style.upd)
                    .build();

            dialog.show();


        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {

            StringBuffer sb = new StringBuffer("");
            link = "http://satyajiit.xyz/quiz/chkUpd.php?ver=" + ver;

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




            } catch (Exception e) {
                sb = new StringBuffer("sads");
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
            if (result.equals("NO")) {
                msg("NO UPDATES AVAILABLE FOR NOW!!");
            } else if (result.contains("NEW")) {


                String web = result.substring(3);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(web));
                startActivity(browserIntent);


            } else
                msg("Network Error or Server Down..please Try Again");


            // Do things like hide the progress bar or change a TextView
        }
    }


    void create() {


        try {
            URL url = new URL("http://satyajiit.xyz/quiz/qLoad.php?user=" + dec(dec(username)));//Create Download URl
            HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
            c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
            c.connect();//connect the URL Connection

            //If Connection response is not OK then show Logs
            if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                        + " " + c.getResponseMessage());

            }








        FileOutputStream fos = null;//Get OutputStream for NewFile Location
        fos=openFileOutput("q.xml", Context.MODE_PRIVATE);

        InputStream is = c.getInputStream();//Get InputStream for connection

        byte[] buffer = new byte[1024];//Set buffer type
        int len1 = 0;//init length
        while ((len1 = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len1);//Write new file
        }

        //Close all connection after doing task
        fos.close();
        is.close();

    } catch(Exception e)

    {


        Log.e(TAG, "Download Error Exception " + e.getMessage());
    }

}
//start here


    private class ques extends AsyncTask<String, Integer, String> {




        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {
            // this.dialog.setMessage("Please wait");
            // this.dialog.show();
            //super.onPreExecute();

            // Do something like display a progress bar

            dialog = new SpotsDialog.Builder()
                    .setContext(con)
                    .setTheme(R.style.que)
                    .build();

            dialog.show();


        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {

           create();
            read();

            return "ss";
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

            if(flag==1) {
                Intent i = new Intent(con, game.class);
                startActivity(i);
            }
            else msg("No More Questions Available right Now!");


            // Do things like hide the progress bar or change a TextView
        }
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

    void read(){
        FileInputStream fis;
        String content = "";
        try {
            fis = openFileInput("q.xml");
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {}
            content += new String(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(content.equals("Over")) flag=0;
        else flag=1;
    }


}
