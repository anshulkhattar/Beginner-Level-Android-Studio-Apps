package com.amary.app.data.moviecat.utils;

import android.widget.ImageView;

import com.amary.app.data.moviecat.data.networking.ApiServer;
import com.squareup.picasso.Picasso;

public class ImgDownload {
    private static final String POSTER_SIZE_W342 = "w342";

    public static void imgPoster(String URL, ImageView IMG_PATH){
        Picasso.get().load(ApiServer.COVER_IMAGE + POSTER_SIZE_W342 + "/"+ URL).into(IMG_PATH);
    }

    public static String getImageUrl(String IMG_PATH) {
        return ApiServer.COVER_IMAGE + POSTER_SIZE_W342 + "/" + IMG_PATH;
    }
}
