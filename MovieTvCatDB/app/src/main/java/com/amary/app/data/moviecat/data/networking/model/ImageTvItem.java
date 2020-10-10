package com.amary.app.data.moviecat.data.networking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageTvItem implements Parcelable {
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

    private ImageTvItem(Parcel in) {
        this.filePath = in.readString();
    }

    public static final Parcelable.Creator<ImageTvItem> CREATOR = new Parcelable.Creator<ImageTvItem>() {
        @Override
        public ImageTvItem createFromParcel(Parcel source) {
            return new ImageTvItem(source);
        }

        @Override
        public ImageTvItem[] newArray(int size) {
            return new ImageTvItem[size];
        }
    };
}
