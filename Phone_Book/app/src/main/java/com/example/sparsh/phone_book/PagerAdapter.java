package com.example.sparsh.phone_book;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

public class PagerAdapter extends FragmentStatePagerAdapter
{

    int NoOfTabs=0;

    public PagerAdapter(FragmentManager fm,int noOfTabs) {
        super(fm);
        this.NoOfTabs=noOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
            {
                return (new Calls());
            }
            case 1:
            {
                return (new Contact());
            }
            case 2:
            {
                return (new Searching());
            }
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return NoOfTabs;
    }
}
