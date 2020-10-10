package com.amary.app.data.moviecat.data.database.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.amary.app.data.moviecat.data.database.model_db.Movie;
import com.amary.app.data.moviecat.data.database.model_db.Tv;

@Database(entities = {Movie.class, Tv.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
    public abstract TvDAO tvDAO();
    private static LocalDatabase instance;

    public static LocalDatabase getInstance(Context context){
        if (instance == null)
            instance = Room.databaseBuilder(context, LocalDatabase.class, "MovieTvCat.db")
                    .allowMainThreadQueries()
                    .build();

            return instance;

    }

}
