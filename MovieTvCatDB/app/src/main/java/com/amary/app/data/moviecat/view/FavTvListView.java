package com.amary.app.data.moviecat.view;

import com.amary.app.data.moviecat.data.database.model_db.Tv;

import java.util.List;

public interface FavTvListView {
    void showLoading();
    void hideLoading();
    void setMovieList(List<Tv> tvs);
    void onErrorData();
}
