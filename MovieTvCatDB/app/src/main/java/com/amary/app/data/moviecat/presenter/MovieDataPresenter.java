package com.amary.app.data.moviecat.presenter;

import com.amary.app.data.moviecat.data.networking.Api;
import com.amary.app.data.moviecat.data.networking.ApiClient;
import com.amary.app.data.moviecat.data.networking.ApiServer;
import com.amary.app.data.moviecat.data.networking.model.DisMovieResponse;
import com.amary.app.data.moviecat.view.MovieListView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDataPresenter {
    private Api service = ApiClient.getRetrofit().create(Api.class);

    public void getMovieList(String bahasa,final MovieListView movieListView){
        movieListView.showLoading();
        service.getDisMovie(ApiServer.API_KEY,bahasa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DisMovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DisMovieResponse disMovieResponse) {
                        movieListView.setMovieList(disMovieResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieListView.onErrorLoading(e.getLocalizedMessage());
                        movieListView.onErrorData();
                    }

                    @Override
                    public void onComplete() {
                        movieListView.hideLoading();
                    }
                });
    }

    public void getSearchMovie(String bahasa,String value, final MovieListView movieListView){
        movieListView.showLoading();
        service.getSearchMovie(ApiServer.API_KEY, bahasa, value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DisMovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DisMovieResponse disMovieResponse) {
                        movieListView.setSearchMovie(disMovieResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieListView.onErrorLoading(e.getLocalizedMessage());
                        movieListView.onErrorData();
                    }

                    @Override
                    public void onComplete() {
                        movieListView.hideLoading();
                    }
                });
    }
}
