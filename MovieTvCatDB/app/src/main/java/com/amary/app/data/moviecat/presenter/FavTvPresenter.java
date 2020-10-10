package com.amary.app.data.moviecat.presenter;

import com.amary.app.data.moviecat.utils.LocalData;
import com.amary.app.data.moviecat.view.FavTvListView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FavTvPresenter {

    public void getTvLocal(CompositeDisposable compositeDisposable, final FavTvListView favTvListView){
        favTvListView.showLoading();
        compositeDisposable.add(LocalData.tvRepository.getTvItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvs -> {
                    if (tvs.isEmpty()){
                        favTvListView.onErrorData();
                    }else {
                        favTvListView.hideLoading();
                        favTvListView.setMovieList(tvs);
                    }
                }));
    }
}
