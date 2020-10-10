package com.amary.app.data.moviecat.utils.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.data.networking.Api;
import com.amary.app.data.moviecat.data.networking.ApiClient;
import com.amary.app.data.moviecat.data.networking.ApiServer;
import com.amary.app.data.moviecat.data.networking.model.DisMovieResponse;
import com.amary.app.data.moviecat.data.networking.model.ResultMovie;
import com.amary.app.data.moviecat.ui.activity.DetailMovieActivity;
import com.amary.app.data.moviecat.utils.DateConvert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReleaseRmReceiver extends BroadcastReceiver {
    private Api service = ApiClient.getRetrofit().create(Api.class);
    private static final int NOTIFICATION_ID = 1;
    private static String CHANNEL_ID = "channel_1";
    private static CharSequence CHANNEL_NAME = "amary_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        service.getMovieRelease(ApiServer.API_KEY,"")
                .enqueue(new Callback<DisMovieResponse>() {
                    @Override
                    public void onResponse(Call<DisMovieResponse> call, Response<DisMovieResponse> response) {
                        if (response.body() != null) {
                            for (int i =0; i< response.body().getResults().size(); i++){
                                String date = DateConvert.convertMonth(response.body().getResults().get(i).getReleaseDate());
                                if (response.body().getResults().get(i).getReleaseDate().equals(getDateNow())){
                                    checkRelease(context, response.body().getResults(), context.getString(R.string.release_check));
                                    break;
                                }else if(date.equals(getMonthNow())){
                                    checkRelease(context, response.body().getResults(), context.getString(R.string.release_month));
                                    break;
                                }
                                else {
                                    checkRelease(context, response.body().getResults(), context.getString(R.string.now_playing_check));
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DisMovieResponse> call, Throwable t) {

                    }
                });
    }

    private void checkRelease(Context context, ArrayList<ResultMovie> resultMovies, String message){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent mainIntent = new Intent(context, DetailMovieActivity.class);
        mainIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, resultMovies.get(0));
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                mainIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(resultMovies.get(0).getTitle())
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (notificationManager != null){
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

    private String getDateNow() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getMonthNow() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
