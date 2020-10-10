package com.amary.app.data.moviecat.view;

import com.amary.app.data.moviecat.data.networking.model.DetailMovie;
import com.amary.app.data.moviecat.data.networking.model.GetImageMovie;

public interface DetailMovieView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void onErrorData();
    void setDetailMovie(DetailMovie detailMovie);
    void setImageMovie(GetImageMovie imageMovie);
}
