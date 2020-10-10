package com.amary.app.data.moviecat.data.database.datasource;

import android.database.Cursor;

import com.amary.app.data.moviecat.data.database.model_db.Movie;

import java.util.List;

import io.reactivex.Flowable;

public interface IMovieDataSource {
    Flowable<List<Movie>> getMovieItem();
    List<Movie> getWidgetMovie();
    Cursor selectAllMovieFav();
    void insertItemMovie(Movie...movies);
    void deleteItemMovie(Movie...movies);
    int isMovie(int idMovie);
}
