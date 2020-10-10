
package com.amary.app.data.moviecat.data.networking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailMovie implements Parcelable {

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private ArrayList<ProductionCompany> productionCompanies = null;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public String getBackdropPath() {
        return backdropPath;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }


    public static class Genre  {
        @SerializedName("name")
        @Expose
        private String name = null;

        public String getName() {
            return name;
        }
    }

    public static class ProductionCompany {
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
        dest.writeList(this.genres);
        dest.writeString(this.homepage);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeList(this.productionCompanies);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.voteAverage);
    }

    public DetailMovie() {
    }

    private DetailMovie(Parcel in) {
        this.backdropPath = in.readString();
        this.genres = new ArrayList<>();
        in.readList(this.genres, Genre.class.getClassLoader());
        this.homepage = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.productionCompanies = new ArrayList<>();
        in.readList(this.productionCompanies, ProductionCompany.class.getClassLoader());
        this.releaseDate = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<DetailMovie> CREATOR = new Parcelable.Creator<DetailMovie>() {
        @Override
        public DetailMovie createFromParcel(Parcel source) {
            return new DetailMovie(source);
        }

        @Override
        public DetailMovie[] newArray(int size) {
            return new DetailMovie[size];
        }
    };
}
