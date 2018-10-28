package com.example.abhishek.twitterclone.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.abhishek.twitterclone.fragments.HomeFragment;
import com.example.abhishek.twitterclone.fragments.MessageFragment;
import com.example.abhishek.twitterclone.fragments.NotificationFragment;
import com.example.abhishek.twitterclone.fragments.SearchFragment;

public class FeedFragmentAdapter extends FragmentPagerAdapter {

    private static final String LOG_TAG = FeedFragmentAdapter.class.getSimpleName();

    private Fragment mHomeFragment;
    private Fragment mSearchFragment;
    private Fragment mNotificationsFragment;
    private Fragment mDirectMessageFragment;

    public FeedFragmentAdapter(FragmentManager fm) {
        super(fm);
        mHomeFragment = new HomeFragment();
        mSearchFragment = new SearchFragment();
        mNotificationsFragment = new NotificationFragment();
        mDirectMessageFragment = new MessageFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mHomeFragment;
            case 1:
                return mSearchFragment;
            case 2:
                return mNotificationsFragment;
            case 3:
                return mDirectMessageFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
