package com.amary.app.data.moviecat.data.database.local;

import com.amary.app.data.moviecat.data.database.datasource.ITvDataSource;
import com.amary.app.data.moviecat.data.database.model_db.Tv;

import java.util.List;

import io.reactivex.Flowable;

public class TvDataSource implements ITvDataSource {

    private TvDAO tvDAO;
    private static TvDataSource instance;

    private TvDataSource(TvDAO tvDAO) {
        this.tvDAO = tvDAO;
    }

    public static TvDataSource getInstance(TvDAO tvDAO){
        if (instance == null)
            instance = new TvDataSource(tvDAO);
        return instance;
    }

    @Override
    public Flowable<List<Tv>> getTvItem() {
        return tvDAO.getTvItem();
    }

    @Override
    public int isTv(int idTv) {
        return tvDAO.isTv(idTv);
    }

    @Override
    public void insertItemTv(Tv...tvs) {
        tvDAO.insertItemTv(tvs);
    }

    @Override
    public void deleteItemTv(Tv...tvs) {
        tvDAO.deleteItemTv(tvs);
    }
}
