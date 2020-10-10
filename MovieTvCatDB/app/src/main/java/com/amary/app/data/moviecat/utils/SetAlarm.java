package com.amary.app.data.moviecat.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.amary.app.data.moviecat.utils.services.DailyRmReceiver;
import com.amary.app.data.moviecat.utils.services.ReleaseRmReceiver;

import java.util.Calendar;

public class SetAlarm {
    private static final int REQ_CODE_DAILY = 100;
    private static final int REQ_CODE_RELEASE = 110;
    private long repeatTime;
    private AlarmManager alarmManager;
    private Context context;

    public SetAlarm(Context context) {
        this.context = context;
    }

    public void startDailyRm(String title, String message) {
        Intent intent = new Intent(context, DailyRmReceiver.class);
        intent.putExtra(DailyRmReceiver.DAILY_TITLE, title);
        intent.putExtra(DailyRmReceiver.DAILY_MESSAGE, message);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                REQ_CODE_DAILY,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() <= now.getTimeInMillis()){
            repeatTime = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY + 1);
        }else {
            repeatTime = calendar.getTimeInMillis();
        }

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    repeatTime,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent);
    }

    public void stopDailyRm(){
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyRmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(),
                REQ_CODE_DAILY,
                intent,
                PendingIntent.FLAG_NO_CREATE);
        if (pendingIntent != null){
            alarmManager.cancel(pendingIntent);
        }
    }

    public void startReleaseRm() {
        Intent intent = new Intent(context, ReleaseRmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                REQ_CODE_RELEASE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() <= now.getTimeInMillis()){
            repeatTime = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY + 1);
        }else {
            repeatTime = calendar.getTimeInMillis();
        }

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                repeatTime,
                AlarmManager.INTERVAL_DAY,
                pendingIntent);
    }

    public void stopReleaseRm(){
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseRmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(),
                REQ_CODE_RELEASE,
                intent,
                PendingIntent.FLAG_NO_CREATE);
        if (pendingIntent != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}
