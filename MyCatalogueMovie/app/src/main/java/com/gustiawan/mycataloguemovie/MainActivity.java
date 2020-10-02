package com.gustiawan.mycataloguemovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText cariFilm;
    private TextView status;
    private Button cari;
    private String API_KEY = "dccb92d950a772c4485c695ab81e5358";
    private ListView listView;
    private ProgressDialog progressDialog;
    private MovieAdapter adapter;
    private JSONObject json;
    String TAG = "CatalogueMovie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cariFilm = findViewById(R.id.edt_judulFilm);
        cari = findViewById(R.id.btn_cari);
        status = findViewById(R.id.status_movie);
        listView = findViewById(R.id.Lv_movie);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Catalogue Movie");
        progressDialog.setMessage("Loading..");

        adapter = new MovieAdapter(this);

        cari.setOnClickListener(this);

        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&sort_by=popularity.desc";
        progressDialog.show();
        getMovie(url);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                String send = "";

                try {
                    send = json.getJSONArray("result").getJSONObject(position).toString();
                }catch (JSONException e){
                    e.printStackTrace();
                }

                intent.putExtra(DetailActivity.EXTRAS_JSON, send);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cari){
            String cari = cariFilm.getText().toString();
            if (!TextUtils.isEmpty(cari)){
                String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY;
                progressDialog.show();
                getMovie(url, cari);
            }
        }
    }

    private void getMovie(String alamat, final String cari) { //async
        adapter.clearData();
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieList> listMovie = new ArrayList<>();
        Log.d(TAG, "getMovie : Running");
        String url = alamat + "&language=en-US&query=" + cari;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String result = new String(responseBody);
                    Log.d(TAG, "result :" +result);

                    JSONObject responseObject = new JSONObject();
                    JSONArray getResult = responseObject.getJSONArray("results");
                    json = responseObject;

                    for (int i = 0; i < getResult.length(); i++ ){
                        JSONObject json = getResult.getJSONObject(i);
                        MovieList movieList = new MovieList(json);
                        listMovie.add(movieList);
                        Log.d(TAG, "tahun : " +listMovie.get(i).getTahunFilm());
                        Log.d(TAG, "header : " +listMovie.get(i).getPosterBesar());

                    }

                    adapter.setData(listMovie);
                    Log.d(TAG,"Size Adapter : " +adapter.getCount());
                    listView.setAdapter(adapter);
                    status.setText("Menmpilkan Hasil untuk " +cari);
                    progressDialog.dismiss();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void getMovie(String url) {
        adapter.clearData();
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieList> listMovie = new ArrayList<>();
        Log.d(TAG, "getMovie : Running");

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d(TAG, "result : " + result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray getResult = jsonObject.getJSONArray("result");
                    json = jsonObject;

                    for (int i = 0; i < getResult.length(); i++) {
                        JSONObject json = getResult.getJSONObject(i);

                        MovieList movieList = new MovieList(json);
                        listMovie.add(movieList);
                        Log.d(TAG, "tahun : " + listMovie.get(i).getTahunFilm());
                        Log.d(TAG, "header: " + listMovie.get(i).getPosterBesar());

                    }

                    adapter.setData(listMovie);
                    Log.d(TAG, "size adapter : " + adapter.getCount());
                    listView.setAdapter(adapter);
                    progressDialog.dismiss();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //donothing
            }
        });


    }}
