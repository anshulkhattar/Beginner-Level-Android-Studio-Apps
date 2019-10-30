package id.merahmuda.bcd.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import id.merahmuda.bcd.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ujang Wahyu on 02,Oktober,2018
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION = "id.merahmuda.bcd.FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION";
    private BaseActivityReceiver baseActivityReceiver = new BaseActivityReceiver();
    public static final IntentFilter INTENT_FILTER = createIntentFilter();

    protected ProgressDialog dialog;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBaseActivityReceiver();
    }

    public abstract void findViews();

    public abstract void initViews();

    public abstract void initListeners();

    @Override
    protected void onDestroy() {
        unRegisterBaseActivityReceiver();
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void showDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage(getString(R.string.progress_dialog_message));
            dialog.setCancelable(false);
        }
        if (!dialog.isShowing())
            dialog.show();
    }

    protected void showDialog(String message) {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage(message);
            dialog.setCancelable(false);
        }
        if (!dialog.isShowing())
            dialog.show();
    }

    protected void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    private static IntentFilter createIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION);
        return filter;
    }

    protected void registerBaseActivityReceiver() {
        registerReceiver(baseActivityReceiver, INTENT_FILTER);
    }

    protected void unRegisterBaseActivityReceiver() {
        unregisterReceiver(baseActivityReceiver);
    }

    public class BaseActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION)) {
                finish();
            }
        }
    }

    public void closeAllActivities() {
        sendBroadcast(new Intent(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION));
    }
}
