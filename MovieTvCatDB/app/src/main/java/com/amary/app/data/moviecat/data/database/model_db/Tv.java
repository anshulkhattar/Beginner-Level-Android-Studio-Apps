package com.amary.app.data.moviecat.data.database.model_db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TV_TB")
public class Tv implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "id_tv")
    public int idTv;

    @ColumnInfo(name = "title_tv")
    public String titleTv;

    @ColumnInfo(name = "date_tv")
    public String dateTv;

    @ColumnInfo(name = "rate_tv")
    public Double rateTv;

    @ColumnInfo(name = "poster_tv")
    public String posterTv;

    @ColumnInfo(name = "backdrops_tv")
    public String backdropsTv;

    public Tv(int idTv, String titleTv, String dateTv, Double rateTv, String posterTv, String backdropsTv) {
        this.idTv = idTv;
        this.titleTv = titleTv;
        this.dateTv = dateTv;
        this.rateTv = rateTv;
        this.posterTv = posterTv;
        this.backdropsTv = backdropsTv;
    }

    public int getIdTv() {
        return idTv;
    }

    public String getTitleTv() {
        return titleTv;
    }

    public String getDateTv() {
        return dateTv;
    }

    public Double getRateTv() {
        return rateTv;
    }

    public String getPosterTv() {
        return posterTv;
    }

    public String getBackdropsTv() {
        return backdropsTv;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idTv);
        dest.writeString(this.titleTv);
        dest.writeString(this.dateTv);
        dest.writeValue(this.rateTv);
        dest.writeString(this.posterTv);
        dest.writeString(this.backdropsTv);
    }

    protected Tv(Parcel in) {
        this.idTv = in.readInt();
        this.titleTv = in.readString();
        this.dateTv = in.readString();
        this.rateTv = (Double) in.readValue(Double.class.getClassLoader());
        this.posterTv = in.readString();
        this.backdropsTv = in.readString();
    }

    public static final Parcelable.Creator<Tv> CREATOR = new Parcelable.Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel source) {
            return new Tv(source);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };
}
