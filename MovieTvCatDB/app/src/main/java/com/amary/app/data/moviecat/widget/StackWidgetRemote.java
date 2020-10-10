package com.amary.app.data.moviecat.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.data.database.datasource.MovieRepository;
import com.amary.app.data.moviecat.data.database.local.LocalDatabase;
import com.amary.app.data.moviecat.data.database.local.MovieDataSource;
import com.amary.app.data.moviecat.data.database.model_db.Movie;
import com.amary.app.data.moviecat.utils.ImgDownload;
import com.amary.app.data.moviecat.utils.LocalData;
import com.bumptech.glide.Glide;

import java.util.List;

public class StackWidgetRemote extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private List<Movie> movieList;

            @Override
            public void onCreate() { }

            @SuppressLint("CheckResult")
            @Override
            public void onDataSetChanged() {
                LocalData.localDatabase = LocalDatabase.getInstance(StackWidgetRemote.this);
                LocalData.movieRepository = MovieRepository.getInstance(MovieDataSource.getInstance(LocalData.localDatabase.movieDAO()));

                List<Movie> movies;
                movies = LocalData.movieRepository.getWidgetMovie();
                movieList = movies;
            }

            @Override
            public void onDestroy() {

            }

            @Override
            public int getCount() {
                return movieList.size();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_item);

                try{
                    Bitmap bitmap = Glide.with(StackWidgetRemote.this)
                            .asBitmap()
                            .load(ImgDownload.getImageUrl(movieList.get(position).getPosterMovie()))
                            .submit()
                            .get();

                    views.setImageViewBitmap(R.id.imageView, bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }

                final Intent fillIntent = new Intent();
                fillIntent.putExtra(WidgetFavoriteMovie.WIDGET_ACTION, position);
                views.setOnClickFillInIntent(R.id.widget_item, fillIntent);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (position < movieList.size()){
                    return movieList.get(position).getIdMovie();
                }
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
