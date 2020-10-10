package com.amary.app.data.moviecat.presenter;

import com.amary.app.data.moviecat.utils.LocalData;
import com.amary.app.data.moviecat.view.FavMovieListView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FavMoviePresenter {

    public void getMovieLocal(CompositeDisposable compositeDisposable, final FavMovieListView favMovieListView){
        favMovieListView.showLoading();
        compositeDisposable.add(LocalData.movieRepository.getMovieItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    if (movies.isEmpty()){
                        favMovieListView.onErrorData();
                    }else {
                        favMovieListView.hideLoading();
                        favMovieListView.setMovieList(movies);
                    }
                }));
    }
}
