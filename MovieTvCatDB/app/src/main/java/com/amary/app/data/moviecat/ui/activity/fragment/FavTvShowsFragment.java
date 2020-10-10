package com.amary.app.data.moviecat.ui.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.base.BaseFragment;
import com.amary.app.data.moviecat.data.database.datasource.TvRepository;
import com.amary.app.data.moviecat.data.database.local.LocalDatabase;
import com.amary.app.data.moviecat.data.database.local.TvDataSource;
import com.amary.app.data.moviecat.data.database.model_db.Tv;
import com.amary.app.data.moviecat.ui.adapter.FavTvListAdapter;
import com.amary.app.data.moviecat.utils.LocalData;
import com.amary.app.data.moviecat.view.FavTvListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class FavTvShowsFragment extends BaseFragment implements FavTvListView {

    @BindView(R.id.rv_movies)
    RecyclerView rvTvShows;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_error_get)
    TextView tvErrorGet;

    public FavTvShowsFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        getDB();

        getFavTvPresenter().getTvLocal(compositeDisposable, this);
    }

    private void getDB() {
        LocalData.localDatabase = LocalDatabase.getInstance(getActivity());
        LocalData.tvRepository = TvRepository.getInstance(TvDataSource.getInstance(LocalData.localDatabase.tvDAO()));
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setMovieList(List<Tv> tvs) {
        rvTvShows.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTvShows.setHasFixedSize(true);
        FavTvListAdapter favTvListAdapter = new FavTvListAdapter(tvs);
        rvTvShows.setAdapter(favTvListAdapter);
    }

    @Override
    public void onErrorData() {
        pbLoading.setVisibility(View.GONE);
        tvErrorGet.setVisibility(View.VISIBLE);
        rvTvShows.setVisibility(View.GONE);
    }
}
