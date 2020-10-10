package com.amary.app.data.moviecat.view;

import com.amary.app.data.moviecat.data.networking.model.DisMovieResponse;

public interface MovieListView {
    void showLoading();
    void hideLoading();
    void setMovieList(DisMovieResponse movieList);
    void setSearchMovie(DisMovieResponse searchMovie);
    void onErrorLoading(String message);
    void onErrorData();
}
