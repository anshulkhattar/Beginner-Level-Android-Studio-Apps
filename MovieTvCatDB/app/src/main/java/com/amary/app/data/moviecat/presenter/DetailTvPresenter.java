package com.amary.app.data.moviecat.presenter;

import com.amary.app.data.moviecat.data.networking.Api;
import com.amary.app.data.moviecat.data.networking.ApiClient;
import com.amary.app.data.moviecat.data.networking.ApiServer;
import com.amary.app.data.moviecat.data.networking.model.DetailTv;
import com.amary.app.data.moviecat.data.networking.model.GetImageTv;
import com.amary.app.data.moviecat.view.DetailTVView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailTvPresenter {
    private Api service = ApiClient.getRetrofit().create(Api.class);

    public void getDetailTv(int id, String bahasa, final DetailTVView detailTVView) {
        detailTVView.showLoading();
        service.getDetailTv(id, ApiServer.API_KEY, bahasa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailTv>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DetailTv detailTv) {
                        detailTVView.setDetailTv(detailTv);
                    }

                    @Override
                    public void onError(Throwable e) {
                        detailTVView.onErrorLoading(e.getLocalizedMessage());
                        detailTVView.onErrorData();
                    }

                    @Override
                    public void onComplete() {
                        detailTVView.hideLoading();
                    }
                });

    }
    public void getImageTv(int id, String bahasa, final DetailTVView detailTVView){
        detailTVView.showLoading();
        service.getImageTv(id, ApiServer.API_KEY, bahasa, "null")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetImageTv>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetImageTv getImageTv) {
                        detailTVView.setImageTv(getImageTv);
                    }

                    @Override
                    public void onError(Throwable e) {
                        detailTVView.onErrorLoading(e.getLocalizedMessage());
                        detailTVView.onErrorData();
                    }

                    @Override
                    public void onComplete() {
                        detailTVView.hideLoading();
                    }
                });
    }
}
