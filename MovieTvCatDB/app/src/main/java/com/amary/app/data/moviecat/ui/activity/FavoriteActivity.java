package com.amary.app.data.moviecat.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.ui.adapter.MenuFavTabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity {

    @BindView(R.id.tab_menu_fav)
    TabLayout tabMenuFav;
    @BindView(R.id.fl_container_fav)
    ViewPager viewMenuFav;
    @BindView(R.id.toolbar_main_fav)
    Toolbar toolbarFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarFav);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Favorite");

        MenuFavTabAdapter adapter =new MenuFavTabAdapter(getSupportFragmentManager(), tabMenuFav.getTabCount());
        viewMenuFav.setAdapter(adapter);
        viewMenuFav.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMenuFav));
        tabMenuFav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewMenuFav.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }


}
