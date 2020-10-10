package com.amary.app.data.moviecat.data.networking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  ResultTv implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    public String getName() {
        return name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public Integer getId() {
        return id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.firstAirDate);
        dest.writeValue(this.id);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
    }

    private ResultTv(Parcel in) {
        this.name = in.readString();
        this.firstAirDate = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
    }

    public static final Creator<ResultTv> CREATOR = new Creator<ResultTv>() {
        @Override
        public ResultTv createFromParcel(Parcel source) {
            return new ResultTv(source);
        }

        @Override
        public ResultTv[] newArray(int size) {
            return new ResultTv[size];
        }
    };
}
