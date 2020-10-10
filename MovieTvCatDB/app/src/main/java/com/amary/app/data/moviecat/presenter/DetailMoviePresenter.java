package com.amary.app.data.moviecat.presenter;

import com.amary.app.data.moviecat.data.networking.Api;
import com.amary.app.data.moviecat.data.networking.ApiClient;
import com.amary.app.data.moviecat.data.networking.ApiServer;
import com.amary.app.data.moviecat.data.networking.model.DetailMovie;
import com.amary.app.data.moviecat.data.networking.model.GetImageMovie;
import com.amary.app.data.moviecat.view.DetailMovieView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailMoviePresenter {
    private Api service = ApiClient.getRetrofit().create(Api.class);

    public void getDetailMovie(int id, String bahasa, final DetailMovieView detailMovieView) {
        detailMovieView.showLoading();
        service.getDetailMovie(id, ApiServer.API_KEY, bahasa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DetailMovie movieDetail) {
                        detailMovieView.setDetailMovie(movieDetail);

                    }

                    @Override
                    public void onError(Throwable e) {
                        detailMovieView.onErrorLoading(e.getLocalizedMessage());
                        detailMovieView.onErrorData();
                    }

                    @Override
                    public void onComplete() {
                        detailMovieView.hideLoading();

                    }
                });
    }

    public void getImageMovie(int id, String bahasa, final DetailMovieView detailMovieView){
        detailMovieView.showLoading();
        service.getImageMovie(id, ApiServer.API_KEY, bahasa, "null")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetImageMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetImageMovie getImageMovie) {
                        detailMovieView.setImageMovie(getImageMovie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        detailMovieView.onErrorLoading(e.getLocalizedMessage());
                        detailMovieView.onErrorData();
                    }

                    @Override
                    public void onComplete() {
                        detailMovieView.hideLoading();
                    }
                });
    }
}
