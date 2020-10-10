package com.amary.app.data.favoriteapps;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amary.app.data.favoriteapps.adapter.MovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String AUTHORITY = "com.amary.app.data.moviecat.provider";
    public static final Uri URI_MOVIE = Uri.parse("content://" + AUTHORITY + "/" + "MOVIE_TB");
    private static final int LOADER = 1;
    private MovieAdapter movieAdapter;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        movieAdapter = new MovieAdapter(this);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvMovies.setAdapter(movieAdapter);

        getSupportLoaderManager().initLoader(LOADER,null, mLoaderCallbacks);

    }


    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @NonNull
                @Override
                public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                    if (id == LOADER) {
                        return new CursorLoader(
                                getApplicationContext(),
                                URI_MOVIE,
                                null,
                                null,
                                null,
                                null);
                    }else {
                        throw new IllegalArgumentException();
                    }

                }

                @Override
                public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                    if (loader.getId() == LOADER) {
                        movieAdapter.refill(data);
                    }
                }

                @Override
                public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                    if (loader.getId() == LOADER) {
                        movieAdapter.refill(null);
                    }
                }
            };

}
