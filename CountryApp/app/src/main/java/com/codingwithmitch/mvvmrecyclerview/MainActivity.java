package com.codingwithmitch.mvvmrecyclerview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingwithmitch.mvvmrecyclerview.adapters.CountryModelAdapter;
import com.codingwithmitch.mvvmrecyclerview.models.CountryModel;
import com.codingwithmitch.mvvmrecyclerview.viewmodels.MainActivityViewModel;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private CountryModelAdapter mAdapter;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);


        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();




//        mMainActivityViewModel.getCountry().observe(this, new Observer<List<CountryModel>>() {
//            @Override
//            public void onChanged(@Nullable List<CountryModel> nicePlaces) {
//                mAdapter.notifyDataSetChanged();
//            }
//        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        mAdapter = new CountryModelAdapter(this, mMainActivityViewModel.getCountry().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}




















