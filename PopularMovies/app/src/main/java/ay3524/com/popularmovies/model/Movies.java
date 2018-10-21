package ay3524.com.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ay3524.com.popularmovies.Utils.Constants;

/**
 * Created by Ashish on 21-10-2016.
 */

public class Movies implements Parcelable {
    @SerializedName(Constants.JSON_OBJECT_TITLE)
    private String title;
    @SerializedName(Constants.JSON_OBJECT_ID)
    private String id;
    @SerializedName(Constants.JSON_OBJECT_POSTER_PATH)
    private String posterURL;
    @SerializedName(Constants.JSON_OBJECT_OVERVIEW)
    private String plot;
    @SerializedName(Constants.JSON_OBJECT_VOTE)
    private String rating;
    @SerializedName(Constants.JSON_OBJECT_RELEASE_DATE)
    private String date;
    @SerializedName(Constants.JSON_ARRAY_GENRE_ID)
    private List<Integer> genreIds = new ArrayList<>();
    @SerializedName(Constants.JSON_OBJECT_BACKDROP_PATH)
    private String videoImage;
    @SerializedName("vote_count")
    private String voteCount;

    public Movies(){

    }

    public Movies(List<Integer> genreIds1,String posterURL1, String plot1, String date1, String id1, String title1, String rating1,String videoImage1) {
        genreIds = genreIds1;
        posterURL = posterURL1;
        plot = plot1;
        date = date1;
        id = id1;
        title = title1;
        rating = rating1;
        videoImage = videoImage1;
    }

    public String getVideoImage() {
        return videoImage;
    }

    /*public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }*/

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    /*public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterURL() {
        return posterURL;
    }
/*
    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }*/

    public String getPlot() {
        return plot;
    }
/*
    public void setPlot(String plot) {
        this.plot = plot;
    }*/

    public String getRating() {
        return rating;
    }
/*
    public void setRating(String rating) {
        this.rating = rating;
    }*/

    public String getDate() {
        return date;
    }
/*
    public void setDate(String date) {
        this.date = date;
    }*/

    public String getVoteCount() {
        return voteCount;
    }
/*
    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }*/


    public static String changeGenreToString(List<Integer> genreIds) {
        String st = "";
        for(int  i = 0 ; i < genreIds.size() ; i++){
            int genreId = genreIds.get(i);
            if (i != genreIds.size() - 1)
                st += getGenreTitle(genreId) + ", ";
            else
                st += getGenreTitle(genreId);
        }
        return st;
    }
    private static String getGenreTitle(int x) {
        switch (x) {
            case Constants.ACTION_NUMBER:
                return Constants.ACTION_STRING;
            case Constants.ADVENTURE_NUMBER:
                return Constants.ADVENTURE_STRING;
            case Constants.ANIMATION_NUMBER:
                return Constants.ANIMATION_STRING;
            case Constants.COMEDY_NUMBER:
                return Constants.COMEDY_STRING;
            case Constants.CRIME_NUMBER:
                return Constants.CRIME_STRING;
            case Constants.DOCUMENTARY_NUMBER:
                return Constants.DOCUMENTARY_STRING;
            case Constants.DRAMA_NUMBER:
                return Constants.DRAMA_STRING;
            case Constants.FAMILY_NUMBER:
                return Constants.FAMILY_STRING;
            case Constants.FANTASY_NUMBER:
                return Constants.FANTASY_STRING;
            case Constants.HISTORY_NUMBER:
                return Constants.HISTORY_STRING;
            case Constants.HORROR_NUMBER:
                return Constants.HORROR_STRING;
            case Constants.MUSIC_NUMBER:
                return Constants.MUSIC_STRING;
            case Constants.MYSTERY_NUMBER:
                return Constants.MYSTERY_STRING;
            case Constants.ROMANCE_NUMBER:
                return Constants.ROMANCE_STRING;
            case Constants.SCIENCE_FICTION_NUMBER:
                return Constants.SCIENCE_FICTION_STRING;
            case Constants.TV_MOVIE_NUMBER:
                return Constants.TV_MOVIE_STRING;
            case Constants.THRILLER_NUMBER:
                return Constants.THRILLER_STRING;
            case Constants.WAR_NUMBER:
                return Constants.WAR_STRING;
            case Constants.WESTERN_NUMBER:
                return Constants.WESTERN_STRING;
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.id);
        dest.writeString(this.posterURL);
        dest.writeString(this.plot);
        dest.writeString(this.rating);
        dest.writeString(this.date);
        dest.writeList(this.genreIds);
        dest.writeString(this.videoImage);
        dest.writeString(this.voteCount);
    }

    protected Movies(Parcel in) {
        this.title = in.readString();
        this.id = in.readString();
        this.posterURL = in.readString();
        this.plot = in.readString();
        this.rating = in.readString();
        this.date = in.readString();
        this.genreIds = new ArrayList<>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.videoImage = in.readString();
        this.voteCount = in.readString();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
