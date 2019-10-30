package com.example.abhishek.twitterclone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.twitterclone.adapters.FeedFragmentAdapter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity
        implements TabLayout.OnTabSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {
    private int tabSelectedIcon[] = {
            R.drawable.ic_home,
            R.drawable.ic_search,
            R.drawable.ic_notifications,
            R.drawable.ic_direct_message
    };
    private int tabUnselectedIcon[] = {
            R.drawable.ic_home_primary_dark,
            R.drawable.ic_search_primary_dark,
            R.drawable.ic_notifications_primary_dark,
            R.drawable.ic_direct_message_primary_dark
    };
    private int tabTitles[] = {
            R.string.home_tab,
            R.string.search_tab,
            R.string.notification_tab,
            R.string.direct_message_tab
    };

    private DrawerLayout mDrawerLayout;
    private TextView mTabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabTitle = findViewById(R.id.tab_title);

        //Setup viewpager and FeedFragmentAdapter
        ViewPager viewPager = findViewById(R.id.vp);
        FeedFragmentAdapter fragmentAdapter = new FeedFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

        TabLayout tabLayout = findViewById(R.id.main_tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        tabLayout.getTabAt(0).setIcon(tabSelectedIcon[0]);
        for (int i = 1; i < 4; i++) {
            tabLayout.getTabAt(i).setIcon(tabUnselectedIcon[i]);
        }

        tabLayout.addOnTabSelectedListener(this);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ImageView profileImage = findViewById(R.id.user_profile_image);
        Picasso.get()
                .load(R.drawable.twitter_profile_image)
                .transform(new CropCircleTransformation())
                .into(profileImage);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        tab.setIcon(tabSelectedIcon[position]);
        mTabTitle.setText(tabTitles[position]);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        tab.setIcon(tabUnselectedIcon[position]);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void openDrawer(View view) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
}
