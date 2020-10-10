package com.amary.app.data.moviecat.data.database.local;

import android.database.Cursor;

import com.amary.app.data.moviecat.data.database.datasource.IMovieDataSource;
import com.amary.app.data.moviecat.data.database.model_db.Movie;

import java.util.List;

import io.reactivex.Flowable;

public class MovieDataSource implements IMovieDataSource {

    private MovieDAO movieDAO;
    private static MovieDataSource instance;

    private MovieDataSource(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public static MovieDataSource getInstance(MovieDAO movieDAO){
        if (instance == null)
            instance = new MovieDataSource(movieDAO);
        return instance;
    }

    @Override
    public Flowable<List<Movie>> getMovieItem() {
        return movieDAO.getMovieItem();
    }

    @Override
    public List<Movie> getWidgetMovie() {
        return movieDAO.getWidgetMovie();
    }

    @Override
    public Cursor selectAllMovieFav() {
        return movieDAO.selectAllMovieFav();
    }


    @Override
    public void insertItemMovie(Movie... movies) {
        movieDAO.insertItemMovie(movies);
    }

    @Override
    public void deleteItemMovie(Movie... movies) {
        movieDAO.deleteItemMovie(movies);
    }

    @Override
    public int isMovie(int idMovie) {
        return movieDAO.isMovie(idMovie);
    }
}
