
package com.amary.app.data.moviecat.data.networking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailTv implements Parcelable {

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private ArrayList<ProductionCompany> productionCompanies = null;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public ArrayList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public class Genre {
        @SerializedName("name")
        @Expose
        private String name = null;

        public String getName() {
            return name;
        }

    }

    public class ProductionCompany {

        @SerializedName("name")
        @Expose
        private String name = null;

        public String getName() {
            return name;
        }

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdropPath);
        dest.writeString(this.firstAirDate);
        dest.writeList(this.genres);
        dest.writeString(this.homepage);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeList(this.productionCompanies);
        dest.writeValue(this.voteAverage);
    }

    public DetailTv() {
    }

    protected DetailTv(Parcel in) {
        this.backdropPath = in.readString();
        this.firstAirDate = in.readString();
        this.genres = new ArrayList<Genre>();
        in.readList(this.genres, Genre.class.getClassLoader());
        this.homepage = in.readString();
        this.name = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.productionCompanies = new ArrayList<ProductionCompany>();
        in.readList(this.productionCompanies, ProductionCompany.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<DetailTv> CREATOR = new Parcelable.Creator<DetailTv>() {
        @Override
        public DetailTv createFromParcel(Parcel source) {
            return new DetailTv(source);
        }

        @Override
        public DetailTv[] newArray(int size) {
            return new DetailTv[size];
        }
    };
}
