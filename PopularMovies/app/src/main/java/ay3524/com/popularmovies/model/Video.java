package ay3524.com.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 12-11-2016.
 */

public class Video implements Parcelable {
    @SerializedName("key")
    private String youtubeKey;
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoutubeKey() {
        return youtubeKey;
    }
/*
    public void setYoutubeKey(String youtubeKey) {
        this.youtubeKey = youtubeKey;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.youtubeKey);
        dest.writeString(this.name);
    }

    private Video(Parcel in) {
        this.youtubeKey = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}