package com.amary.app.data.moviecat.data.database.model_db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MOVIE_TB")
public class Movie implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id_movie")
    public int idMovie;

    @ColumnInfo(name = "title_movie")
    public String titleMovie;

    @ColumnInfo(name = "date_movie")
    public String dateMovie;

    @ColumnInfo(name = "rate_movie")
    public Double rateMovie;

    @ColumnInfo(name = "poster_movie")
    public String posterMovie;

    @ColumnInfo(name = "backdrops_movie")
    public String backdropsMovie;

    public Movie(int idMovie, String titleMovie, String dateMovie, Double rateMovie, String posterMovie, String backdropsMovie) {
        this.idMovie = idMovie;
        this.titleMovie = titleMovie;
        this.dateMovie = dateMovie;
        this.rateMovie = rateMovie;
        this.posterMovie = posterMovie;
        this.backdropsMovie = backdropsMovie;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public String getDateMovie() {
        return dateMovie;
    }

    public Double getRateMovie() {
        return rateMovie;
    }

    public String getPosterMovie() {
        return posterMovie;
    }

    public String getBackdropsMovie() {
        return backdropsMovie;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idMovie);
        dest.writeString(this.titleMovie);
        dest.writeString(this.dateMovie);
        dest.writeValue(this.rateMovie);
        dest.writeString(this.posterMovie);
        dest.writeString(this.backdropsMovie);
    }

    protected Movie(Parcel in) {
        this.idMovie = in.readInt();
        this.titleMovie = in.readString();
        this.dateMovie = in.readString();
        this.rateMovie = (Double) in.readValue(Double.class.getClassLoader());
        this.posterMovie = in.readString();
        this.backdropsMovie = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
