package com.amary.app.data.moviecat.data.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetImageMovie {
    @SerializedName("backdrops")
    @Expose
    private ArrayList<ImageMovieItem> backdrops = null;

    public ArrayList<ImageMovieItem> getBackdrops() {
        return backdrops;
    }

}
