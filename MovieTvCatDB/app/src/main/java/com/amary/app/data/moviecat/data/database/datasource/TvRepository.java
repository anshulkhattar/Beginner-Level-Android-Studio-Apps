package com.amary.app.data.moviecat.data.database.datasource;

import com.amary.app.data.moviecat.data.database.model_db.Tv;

import java.util.List;

import io.reactivex.Flowable;

public class TvRepository implements ITvDataSource {
    private ITvDataSource iTvDataSource;
    private static TvRepository instance;

    private TvRepository(ITvDataSource iTvDataSource) {
        this.iTvDataSource = iTvDataSource;
    }

    public static TvRepository getInstance(ITvDataSource iTvDataSource){
        if (instance == null)
            instance = new TvRepository(iTvDataSource);
        return instance;
    }

    @Override
    public Flowable<List<Tv>> getTvItem() {
        return iTvDataSource.getTvItem();
    }

    @Override
    public int isTv(int idTv) {
        return iTvDataSource.isTv(idTv);
    }

    @Override
    public void insertItemTv(Tv...tvs) {
        iTvDataSource.insertItemTv(tvs);
    }

    @Override
    public void deleteItemTv(Tv...tvs) {
        iTvDataSource.deleteItemTv(tvs);
    }
}
