package com.amary.app.data.moviecat.data.database.datasource;

import android.database.Cursor;

import com.amary.app.data.moviecat.data.database.model_db.Movie;

import java.util.List;

import io.reactivex.Flowable;

public class MovieRepository implements IMovieDataSource{

    private IMovieDataSource iMovieDataSource;

    private MovieRepository(IMovieDataSource iMovieDataSource) {
        this.iMovieDataSource = iMovieDataSource;
    }

    private static MovieRepository instance;

    public static MovieRepository getInstance(IMovieDataSource iMovieDataSource){
        if (instance == null)
            instance = new MovieRepository(iMovieDataSource);
        return instance;
    }

    @Override
    public Flowable<List<Movie>> getMovieItem() {
        return iMovieDataSource.getMovieItem();
    }

    @Override
    public List<Movie> getWidgetMovie() {
        return iMovieDataSource.getWidgetMovie();
    }

    @Override
    public Cursor selectAllMovieFav() {
        return iMovieDataSource.selectAllMovieFav();
    }


    @Override
    public void insertItemMovie(Movie...movies) {
        iMovieDataSource.insertItemMovie(movies);
    }

    @Override
    public void deleteItemMovie(Movie...movies) {
        iMovieDataSource.deleteItemMovie(movies);
    }

    @Override
    public int isMovie(int idMovie) {
        return iMovieDataSource.isMovie(idMovie);
    }
}
