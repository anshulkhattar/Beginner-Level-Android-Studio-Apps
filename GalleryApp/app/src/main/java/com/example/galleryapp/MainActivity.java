package com.example.galleryapp;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView boj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

public class GalleryProvider extends AsyncTask<Void,Void, ArrayList<Integer>>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected ArrayList<Integer> doInBackground(Void... voids) {
        ArrayList<Integer> data=new ArrayList<>();
        return null;




    }

    @Override
    protected void onPostExecute(ArrayList<Integer> strings) {
        super.onPostExecute(strings);
    }


}
