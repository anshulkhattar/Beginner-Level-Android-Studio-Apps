package com.amary.app.data.moviecat.data.networking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageMovieItem implements Parcelable {
    @SerializedName("file_path")
    @Expose
    private String filePath;

    public String getFilePath() {
        return filePath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.filePath);
    }

    private ImageMovieItem(Parcel in) {
        this.filePath = in.readString();
    }

    public static final Parcelable.Creator<ImageMovieItem> CREATOR = new Parcelable.Creator<ImageMovieItem>() {
        @Override
        public ImageMovieItem createFromParcel(Parcel source) {
            return new ImageMovieItem(source);
        }

        @Override
        public ImageMovieItem[] newArray(int size) {
            return new ImageMovieItem[size];
        }
    };
}
