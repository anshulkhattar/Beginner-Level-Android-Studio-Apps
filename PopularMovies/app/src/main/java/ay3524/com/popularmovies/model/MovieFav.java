package ay3524.com.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ashish on 30-11-2016.
 */

public class MovieFav implements Parcelable {
    private String name;
    private String genre;
    private String trailer;
    private byte[] posterImage;
    private byte[] videoImage;
    private String releaseDate;
    private String rating;
    private String plot;
    private String voteCount;

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public byte[] getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(byte[] posterImage) {
        this.posterImage = posterImage;
    }

    public byte[] getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(byte[] videoImage) {
        this.videoImage = videoImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.genre);
        dest.writeString(this.trailer);
        dest.writeByteArray(this.posterImage);
        dest.writeByteArray(this.videoImage);
        dest.writeString(this.releaseDate);
        dest.writeString(this.rating);
        dest.writeString(this.plot);
        dest.writeString(this.voteCount);
    }

    public MovieFav() {
    }

    private MovieFav(Parcel in) {
        this.name = in.readString();
        this.genre = in.readString();
        this.trailer = in.readString();
        this.posterImage = in.createByteArray();
        this.videoImage = in.createByteArray();
        this.releaseDate = in.readString();
        this.rating = in.readString();
        this.plot = in.readString();
        this.voteCount = in.readString();
    }

    public static final Parcelable.Creator<MovieFav> CREATOR = new Parcelable.Creator<MovieFav>() {
        @Override
        public MovieFav createFromParcel(Parcel source) {
            return new MovieFav(source);
        }

        @Override
        public MovieFav[] newArray(int size) {
            return new MovieFav[size];
        }
    };
}
