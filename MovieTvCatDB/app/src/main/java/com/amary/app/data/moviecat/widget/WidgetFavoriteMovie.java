package com.amary.app.data.moviecat.widget;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.ui.activity.FavoriteActivity;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetFavoriteMovie extends AppWidgetProvider {
    private static final String TOAST_ACTION = "com.amary.app.data.moviecat.widget.TOAST_ACTION";
    public static final String WIDGET_ACTION = "com.amary.app.data.moviecat.widget.WIDGET_ACTION";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_favorite_movie);

        Intent intent = new Intent(context, StackWidgetRemote.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.stack_view, intent);

        Intent clickWidget = new Intent(context, FavoriteActivity.class);
        PendingIntent clickPendingWidget = TaskStackBuilder.create(context)
                .addNextIntentWithParentStack(clickWidget)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stack_view, clickPendingWidget);

        Intent updateWidget = new Intent(context, WidgetFavoriteMovie.class);
        updateWidget.setAction(WidgetFavoriteMovie.TOAST_ACTION);
        updateWidget.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent updatePendingWidget =PendingIntent.getBroadcast
                (context, 0, updateWidget, PendingIntent.FLAG_UPDATE_CURRENT );
        views.setOnClickPendingIntent(R.id.btn_refresh_widget, updatePendingWidget);

        views.setEmptyView(R.id.stack_view, R.id.empty_view);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() != null){
            if (intent.getAction().equals(TOAST_ACTION)){
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisWidget = new ComponentName(context, WidgetFavoriteMovie.class);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);
            }
        }
    }
}