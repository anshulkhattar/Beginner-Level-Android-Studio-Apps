package com.amary.app.data.moviecat.view;

import com.amary.app.data.moviecat.data.networking.model.DetailTv;
import com.amary.app.data.moviecat.data.networking.model.GetImageTv;

public interface DetailTVView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void onErrorData();
    void setDetailTv(DetailTv detailTv);
    void setImageTv(GetImageTv imageTv);
}
