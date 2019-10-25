package com.example.myweatherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getWeatherData extends AsyncTask<URL, Void, Void> implements Animation.AnimationListener {
    public String data,temp,desc,icon,humid;
    Context context ;
    public ImageView imv;
    public Animation animFadein;
    private ImageView front_image ;
    private ProgressDialog p;

    public getWeatherData(MainActivity mainActivity) {
        this.context = mainActivity.getApplicationContext();
        imv = (ImageView) mainActivity.findViewById(R.id.weather_icon);
        front_image = mainActivity.findViewById(R.id.front_image);
        p = mainActivity.pd ;

    }

    @Override
    protected Void doInBackground(URL... urls) {


        try {
            HttpURLConnection con = (HttpURLConnection) urls[0].openConnection();
            InputStream io = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(io));
            String line = "";
            data = "";
            while(line!=null){
                line = br.readLine();
                data = data+line ;

            }

        } catch (IOException e) {
            Log.d("Exception aaya",e.getMessage());
        }

        return null ;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
        p.dismiss();
            animFadein = AnimationUtils.loadAnimation(context,
                    R.anim.fade_in);

            String icon_url ;
            JSONArray jar;
            JSONObject jar1 ;
            JSONObject obj = new JSONObject(data);
            jar = obj.getJSONArray("weather");
            jar1 = (JSONObject)obj.getJSONObject("main");


            JSONObject weather = (JSONObject) jar.get(0);
                temp = jar1.getString("temp");
                MainActivity.tv_temp.setAnimation(animFadein);
                MainActivity.tv_temp.setText( context.getResources().getString(R.string.curr_temp) +" "+ temp + (char) 0x00B0 + "C");
                Log.d("Temp" ,temp);
                desc = weather.get("description").toString();

                if (desc.contains("rain")){

                    front_image.setImageResource(R.drawable.rainy);
                    front_image.setAnimation(animFadein);

                }
                else if(desc.contains("cloud")){
                    front_image.setImageResource(R.drawable.scatter_clouds);
                    front_image.setAnimation(animFadein);

                }
                else if(desc.contains("mist")){
                    front_image.setImageResource(R.drawable.mist);
                    front_image.setAnimation(animFadein);

                }
                else if(desc.contains("sky")){
                    front_image.setImageResource(R.drawable.clear_sky);
                    front_image.setAnimation(animFadein);

                }

                else if(desc.contains("sun")){
                    front_image.setImageResource(R.drawable.sunny);
                    front_image.setAnimation(animFadein);

                }
                else if(desc.contains("haze")){
                    front_image.setImageResource(R.drawable.hazeee);
                    front_image.setAnimation(animFadein);

                }
                else {
                    front_image.setImageResource(R.drawable.final_img);
                    front_image.setAnimation(animFadein);
                }
                desc = desc.substring(0,1).toUpperCase() + desc.substring(1);
            MainActivity.tv_desc.setAnimation(animFadein);
                MainActivity.tv_desc.setText(context.getResources().getString(R.string.tv_desc) +" "+ desc);
                Log.d("Desc" ,desc);
                icon = weather.get("icon").toString();
            icon_url = "https://openweathermap.org/img/wn/" + icon + "@2x.png" ;
            Log.d("Icon url " ,icon_url);
            imv.setAnimation(animFadein);
            Picasso.get().load(icon_url).into(imv);

                Log.d("Icon" ,icon);
                humid = jar1.getString("humidity");
            MainActivity.tv_humid.setAnimation(animFadein);
            MainActivity.tv_humid.setText(context.getResources().getString(R.string.tv_humid) +" "+ humid + "%");
                Log.d("Humidity" ,humid);

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + data + "\"");
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {


    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
