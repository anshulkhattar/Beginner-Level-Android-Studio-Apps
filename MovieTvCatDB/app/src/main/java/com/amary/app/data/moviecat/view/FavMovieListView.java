package com.amary.app.data.moviecat.view;

import com.amary.app.data.moviecat.data.database.model_db.Movie;

import java.util.List;

public interface FavMovieListView {
    void showLoading();
    void hideLoading();
    void setMovieList(List<Movie> movieList);
    void onErrorData();
}
