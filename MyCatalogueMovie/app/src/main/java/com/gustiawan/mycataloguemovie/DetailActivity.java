package com.gustiawan.mycataloguemovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRAS_JSON = "";
    private TextView judul, tahun, sinopsis;
    private ImageView header, poster;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        judul = (TextView)findViewById(R.id.tvDetailJudul);
        tahun = (TextView)findViewById(R.id.tvYear);
        sinopsis = (TextView)findViewById(R.id.tvDetailSinopsis);

        header = (ImageView)findViewById(R.id.ivHeader);
        poster = (ImageView)findViewById(R.id.ivPoster);

        EXTRAS_JSON = getIntent().getStringExtra(EXTRAS_JSON);


        if (EXTRAS_JSON.equals("")){
            try {
                json = new JSONObject(EXTRAS_JSON);
                MovieList movieList = new MovieList(json);
                getImage(movieList);

                judul.setText(movieList.getJudulFilm());
                tahun.setText(movieList.getTahunFilm());
                sinopsis.setText(movieList.getDeskripsiFilm());


            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

    private void getImage(MovieList movieList) {

        if (movieList.getUrl_poster() != null){
            String url_poster = "https://image.tmdb.org/t/p/w92/"+movieList.getUrl_poster();
            Glide.with(this).load(url_poster).into(poster);
        }

        if (movieList.getPosterBesar() != null){
            String url_header = "https://image.tmdb.org/t/p/w300/"+movieList.getPosterBesar();
            Glide.with(this).load(url_header).into(header);
        }
    }
}
