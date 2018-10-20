package ay3524.com.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 04-12-2016.
 */

public class Casts implements Parcelable {
    @SerializedName("character")
    private String characterName;
    @SerializedName("name")
    private String name;
    @SerializedName("profile_path")
    private String profilePath;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }
/*
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.characterName);
        dest.writeString(this.name);
        dest.writeString(this.profilePath);
    }

    private Casts(Parcel in) {
        this.characterName = in.readString();
        this.name = in.readString();
        this.profilePath = in.readString();
    }

    public static final Parcelable.Creator<Casts> CREATOR = new Parcelable.Creator<Casts>() {
        @Override
        public Casts createFromParcel(Parcel source) {
            return new Casts(source);
        }

        @Override
        public Casts[] newArray(int size) {
            return new Casts[size];
        }
    };
}
