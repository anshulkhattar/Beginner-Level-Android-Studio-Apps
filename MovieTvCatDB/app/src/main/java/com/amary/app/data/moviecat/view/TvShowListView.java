package com.amary.app.data.moviecat.view;

import com.amary.app.data.moviecat.data.networking.model.DisTvResponse;

public interface TvShowListView {
    void showLoading();
    void hideLoading();
    void setTvList(DisTvResponse tvList);
    void setSearchTv(DisTvResponse searchTv);
    void onErrorLoading(String message);
    void onErrorData();
}
