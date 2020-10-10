package com.amary.app.data.moviecat.data.database.datasource;

import com.amary.app.data.moviecat.data.database.model_db.Tv;

import java.util.List;

import io.reactivex.Flowable;

public interface ITvDataSource {
    Flowable<List<Tv>> getTvItem();
    int isTv(int idTv);
    void insertItemTv(Tv...tvs);
    void deleteItemTv(Tv...tvs);
}
