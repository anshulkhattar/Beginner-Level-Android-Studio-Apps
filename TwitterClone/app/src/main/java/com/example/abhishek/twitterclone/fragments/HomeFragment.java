package com.example.abhishek.twitterclone.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishek.twitterclone.R;
import com.example.abhishek.twitterclone.adapters.FeedAdapter;
import com.example.abhishek.twitterclone.models.TweetModel;
import com.example.abhishek.twitterclone.utils.QueryUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        // Extract tweets
        ArrayList<TweetModel> tweets = QueryUtils.extractTweets();


        // Setup Home Page Feed Adapter
        FeedAdapter adapter = new FeedAdapter(container.getContext(), tweets);

        //Setup RecyclerView from layout
        RecyclerView recyclerView = homeView.findViewById(R.id.recyclerview_feed);
        recyclerView.setHasFixedSize(true);
        //  Setting up linear layout for Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return homeView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}