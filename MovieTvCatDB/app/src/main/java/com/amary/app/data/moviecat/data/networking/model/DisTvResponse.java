package com.amary.app.data.moviecat.data.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DisTvResponse {
    @SerializedName("results")
    @Expose
    private ArrayList<ResultTv> results = null;

    public ArrayList<ResultTv> getResults() {
        return results;
    }

}