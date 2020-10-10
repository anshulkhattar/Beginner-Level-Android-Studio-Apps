package com.amary.app.data.moviecat.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.widget.SwitchCompat;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.base.BaseActivity;
import com.amary.app.data.moviecat.utils.DateConvert;
import com.amary.app.data.moviecat.utils.SetAlarm;
import com.amary.app.data.moviecat.utils.SettingPreference;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private Locale locale;
    private SettingPreference preference;
    private SetAlarm setAlarm;

    @BindView(R.id.rb_set_language)
    RadioGroup rbSetLanguage;
    @BindView(R.id.rb_en)
    RadioButton rbEn;
    @BindView(R.id.rb_id)
    RadioButton rbId;
    @BindView(R.id.btn_save_language)
    Button btnSaveLanguage;
    @BindView(R.id.sw_dailiy_reminder)
    SwitchCompat swDailyReminder;
    @BindView(R.id.sw_release_reminder)
    SwitchCompat swReleaseReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.action_settings);

        getSwitchLanguage();
        setLanguage();

        getSwitchRm();
        swDailyReminder.setOnCheckedChangeListener(this);
        swReleaseReminder.setOnCheckedChangeListener(this);

        btnSaveConfig();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void btnSaveConfig() {
        btnSaveLanguage.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            SettingActivity.this.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        });
    }

    private void setLanguage() {
        rbSetLanguage.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = group.findViewById(checkedId);
            if (rb != null) {
                switch (checkedId) {
                    case R.id.rb_en:
                        locale = new Locale("en");
                        rb.setChecked(true);
                        saveLocaleLanguage(locale);
                        setLocaleLanguage();
                        break;
                    case R.id.rb_id:
                        locale = new Locale("in");
                        saveLocaleLanguage(locale);
                        setLocaleLanguage();
                        break;
                    default:
                        locale = new Locale("en");
                        saveLocaleLanguage(locale);
                        setLocaleLanguage();
                }
            }
        });
    }

    private void getSwitchLanguage(){
        String languageLocal = getString(R.string.localization);
        if (languageLocal.equals("en-US")) {
            rbEn.setChecked(true);
            rbId.setChecked(false);
        } else if (languageLocal.equals("id-ID")) {
            rbEn.setChecked(false);
            rbId.setChecked(true);
        }
    }

    private void getSwitchRm(){
        setAlarm = new SetAlarm(this);
        preference = new SettingPreference(this);
        if (preference.getPreferences().getBoolean(SettingPreference.STATS_DAILY, false)){
            swDailyReminder.setChecked(true);
        }else {
            swDailyReminder.setChecked(false);
        }

        if (preference.getPreferences().getBoolean(SettingPreference.STATS_RELEASE, false)){
            swReleaseReminder.setChecked(true);
        }else {
            swReleaseReminder.setChecked(false);
        }
    }

    private void setLocaleLanguage() {
        preference = new SettingPreference(this);
        DateConvert.setBhsData(preference.getPreferences().getString(SettingPreference.LANGUAGE, ""));
        Locale locale = new Locale(preference.getPreferences().getString(SettingPreference.LANGUAGE, ""));
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }

    private void saveLocaleLanguage(Locale language){
        preference = new SettingPreference(this);
        preference.setDataSharedLanguage(language.toString());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.sw_dailiy_reminder:
                if (isChecked){
                    setAlarm.startDailyRm(getString(R.string.app_name), getString(R.string.daily_message));
                    saveDailyRm(true);
                }else {
                    setAlarm.stopDailyRm();
                    saveDailyRm(false);
                }
                break;
            case R.id.sw_release_reminder:
                if (isChecked){
                    setAlarm.startReleaseRm();
                    saveReleaseRm(true);
                }else {
                    setAlarm.stopReleaseRm();
                    saveReleaseRm(false);
                }
                break;
        }
    }

    private void saveDailyRm(boolean data) {
        preference = new SettingPreference(this);
        preference.setDailyReminder(data);
    }

    private void saveReleaseRm(boolean data){
        preference = new SettingPreference(this);
        preference.setReleaseReminder(data);
    }
}
