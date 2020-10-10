package com.amary.app.data.moviecat.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingPreference {
    private static final String PREFS_NAME = "movie_tv_cat_setting";
    public static final String LANGUAGE = "language";
    public static final String STATS_DAILY = "daily_reminder";
    public static final String STATS_RELEASE = "release_reminder";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SettingPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setDataSharedLanguage(String value){
        editor = preferences.edit();
        editor.putString(LANGUAGE, value);
        editor.apply();
    }

    public void setDailyReminder(Boolean value){
        editor = preferences.edit();
        editor.putBoolean(STATS_DAILY, value);
        editor.apply();
    }

    public void setReleaseReminder(Boolean value){
        editor = preferences.edit();
        editor.putBoolean(STATS_RELEASE, value);
        editor.apply();
    }

    public SharedPreferences getPreferences(){
        return preferences;
    }
}
