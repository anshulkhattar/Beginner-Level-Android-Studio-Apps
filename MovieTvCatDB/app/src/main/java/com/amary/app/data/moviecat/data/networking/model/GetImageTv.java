package com.amary.app.data.moviecat.data.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetImageTv {
    @SerializedName("backdrops")
    @Expose
    private ArrayList<ImageTvItem> backdrops = null;

    public ArrayList<ImageTvItem> getBackdrops() {
        return backdrops;
    }


}
