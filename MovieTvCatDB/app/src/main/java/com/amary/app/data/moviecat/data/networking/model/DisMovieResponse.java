package com.amary.app.data.moviecat.data.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DisMovieResponse {

    @SerializedName("results")
    @Expose
    private ArrayList<ResultMovie> results = null;

    public ArrayList<ResultMovie> getResults() {
        return results;
    }

}