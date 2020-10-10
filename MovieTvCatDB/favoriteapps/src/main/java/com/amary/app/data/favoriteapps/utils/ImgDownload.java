package com.amary.app.data.favoriteapps.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImgDownload {
    private static final String POSTER_SIZE_W342 = "w342";
    private static final String URL_IMAGE ="https://image.tmdb.org/t/p/";

    public static void imgPoster(String URL, ImageView IMG_PATH){
        Picasso.get().load(URL_IMAGE + POSTER_SIZE_W342 + "/"+ URL).into(IMG_PATH);
    }
}
