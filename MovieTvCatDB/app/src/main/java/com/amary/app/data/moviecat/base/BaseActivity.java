package com.amary.app.data.moviecat.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amary.app.data.moviecat.presenter.DetailMoviePresenter;
import com.amary.app.data.moviecat.presenter.DetailTvPresenter;
import com.androidnetworking.AndroidNetworking;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    public static final String KEY_DETAIL_MOVIE = "movie_det";
    public static final String KEY_IMAGE_MOVIE = "image_movie";

    public static final String KEY_DETAIL_TV = "tv_det";
    public static final String KEY_IMAGE_TV = "image_tv";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(this);
    }

    public DetailMoviePresenter getDetailMoviePresenter(){
        return new DetailMoviePresenter();
    }

    public DetailTvPresenter getDetailTvPresenter(){
        return new DetailTvPresenter();
    }
}
