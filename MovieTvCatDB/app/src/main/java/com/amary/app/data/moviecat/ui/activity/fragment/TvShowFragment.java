package com.amary.app.data.moviecat.ui.activity.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.base.BaseFragment;
import com.amary.app.data.moviecat.data.networking.model.DisTvResponse;
import com.amary.app.data.moviecat.data.networking.model.ResultTv;
import com.amary.app.data.moviecat.ui.activity.DetailTvActivity;
import com.amary.app.data.moviecat.ui.activity.FavoriteActivity;
import com.amary.app.data.moviecat.ui.activity.SettingActivity;
import com.amary.app.data.moviecat.ui.adapter.TvShowListAdapter;
import com.amary.app.data.moviecat.view.TvShowListView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends BaseFragment implements TvShowListView, SearchView.OnQueryTextListener {
    private ArrayList<ResultTv> itemTv = new ArrayList<>();
    private TvShowListAdapter tvShowListAdapter;

    @BindView(R.id.rv_movies)
    RecyclerView rvTvShows;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_error_get)
    TextView tvErrorGet;

    public TvShowFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        String bhsLocal = getString(R.string.localization);
        setHasOptionsMenu(true);

        showData();

        if (savedInstanceState == null){
            getTvDataPresenter().getTvList(bhsLocal,this);
        }else {
            itemTv = savedInstanceState.getParcelableArrayList(KEY_TV_LIST);
            tvShowListAdapter.refill(itemTv);
        }


    }

    private void showData(){
        tvShowListAdapter = new TvShowListAdapter(itemTv);
        rvTvShows.setHasFixedSize(true);
        rvTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTvShows.setAdapter(tvShowListAdapter);
        tvShowListAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), DetailTvActivity.class);
            intent.putExtra(DetailTvActivity.EXTRA_TV, itemTv.get(position));
            startActivity(intent);
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_app_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null){
            SearchView searchView = (SearchView) MenuItemCompat.getActionView((menu.findItem(R.id.mn_app_search)));
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search));
            searchView.setOnQueryTextListener(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mn_app_setting) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.mn_app_favorite){
            Intent intent = new Intent(getActivity(), FavoriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        rvTvShows.setVisibility(View.GONE);
        tvErrorGet.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
        tvErrorGet.setVisibility(View.GONE);
        rvTvShows.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTvList(DisTvResponse tvList) {
        itemTv = tvList.getResults();
        tvShowListAdapter.refill(itemTv);
    }

    @Override
    public void setSearchTv(DisTvResponse searchTv) {
        itemTv = searchTv.getResults();
        tvShowListAdapter.setFilter(itemTv);
    }


    @Override
    public void onErrorLoading(String message) {
        pbLoading.setVisibility(View.GONE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorData() {
        tvErrorGet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_TV_LIST, itemTv);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        String bhsLocal = getString(R.string.localization);

        if (newText.isEmpty()){
            getTvDataPresenter().getTvList(bhsLocal, this);
        }else {
            getTvDataPresenter().getSearchTv(bhsLocal, newText, this);
        }
        return true;
    }
}
