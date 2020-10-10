package com.amary.app.data.moviecat.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.amary.app.data.moviecat.base.BaseActivity;
import com.amary.app.data.moviecat.ui.adapter.MenuTabAdapter;
import com.amary.app.data.moviecat.utils.DateConvert;
import com.amary.app.data.moviecat.utils.SettingPreference;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.amary.app.data.moviecat.R.id;
import static com.amary.app.data.moviecat.R.layout;

public class MainActivity extends BaseActivity {

    @BindView(id.tab_menu)
    TabLayout tabMenu;
    @BindView(id.fl_container)
    ViewPager viewMenu;
    @BindView(id.toolbar_main)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingPreference preference = new SettingPreference(this);
        if (preference.getPreferences() != null){
            DateConvert.setBhsData(preference.getPreferences().getString(SettingPreference.LANGUAGE, ""));
            Locale locale = new Locale(preference.getPreferences().getString(SettingPreference.LANGUAGE, ""));
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
            getLanguage();
        }else {
            getLanguage();
        }
    }

    private void getLanguage(){
        setContentView(layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        MenuTabAdapter adapter = new MenuTabAdapter(getSupportFragmentManager(), tabMenu.getTabCount());
        viewMenu.setAdapter(adapter);
        viewMenu.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMenu));
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewMenu.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
}
