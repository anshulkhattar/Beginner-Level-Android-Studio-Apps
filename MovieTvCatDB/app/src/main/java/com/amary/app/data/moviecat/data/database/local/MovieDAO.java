package com.amary.app.data.moviecat.data.database.local;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.amary.app.data.moviecat.data.database.model_db.Movie;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM MOVIE_TB")
    Flowable<List<Movie>> getMovieItem();

    @Query("SELECT * FROM MOVIE_TB")
    List<Movie> getWidgetMovie();

    @Query("SELECT * FROM MOVIE_TB")
    Cursor selectAllMovieFav();

    @Query("SELECT EXISTS (SELECT 1 FROM MOVIE_TB WHERE id_movie=:idMovie)")
    int isMovie(int idMovie);

    @Insert
    void insertItemMovie(Movie...movies);

    @Delete
    void deleteItemMovie(Movie...movies);
}
