package ay3524.com.popularmovies.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ay3524.com.popularmovies.R;
import ay3524.com.popularmovies.Utils.Constants;
import ay3524.com.popularmovies.Utils.SpacesItemDecoration;
import ay3524.com.popularmovies.adapter.RecyclerAdapterFav;
import ay3524.com.popularmovies.data.MovieContract;
import ay3524.com.popularmovies.data.MovieDbHelper;
import ay3524.com.popularmovies.model.MovieFav;
import butterknife.BindView;
import butterknife.ButterKnife;

import static ay3524.com.popularmovies.Utils.Constants.MOVIES_LOADER_ID_ONE;
import static ay3524.com.popularmovies.data.MovieContract.MovieEntry.TABLE_NAME;

/**
 * Created by Ashish on 02-11-2016.
 */

public class MoviesFavoriteListFragment extends Fragment implements RecyclerAdapterFav.ClickListener {

    GridLayoutManager layoutManager;
    RecyclerAdapterFav adapter;
    List<MovieFav> itemsModels = new ArrayList<>();
    SQLiteDatabase database;
    MovieDbHelper dbHelper;
    OnHeadLineSelectedListener callback;
    @BindView(R.id.empty_view) View emptyView;
    @BindView(R.id.toolbar) Toolbar tb;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_fav_layout, container, false);
        ButterKnife.bind(this,v);
        ((AppCompatActivity) getActivity()).setSupportActionBar(tb);
        emptyView.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getActivity(), Constants.GRID_TWO);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            layoutManager = new GridLayoutManager(getActivity(), Constants.GRID_TRHEE);
            recyclerView.setLayoutManager(layoutManager);
        }
        recyclerView.addItemDecoration(new SpacesItemDecoration(MOVIES_LOADER_ID_ONE, MOVIES_LOADER_ID_ONE, false));

        dbHelper = new MovieDbHelper(getActivity());
        database = dbHelper.getReadableDatabase();
        itemsModels = getListFromDb(database);

        adapter = new RecyclerAdapterFav(itemsModels, getActivity());
        adapter.setClickListener(MoviesFavoriteListFragment.this);
        recyclerView.setAdapter(adapter);

        if (itemsModels.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
        }
        return v;
    }

    public interface OnHeadLineSelectedListener {
        void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            callback = (MoviesFavoriteListFragment.OnHeadLineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must Implement OnHeadLineSelectedListener");
        }
    }

    @Override
    public void onItemClick(View v, int pos) {
        MovieFav currentMovies = itemsModels.get(pos);
        String genre = currentMovies.getGenre();
        byte[] posterPath = currentMovies.getPosterImage();
        String plot = currentMovies.getPlot();
        String releaseDate = currentMovies.getReleaseDate();
        String title = currentMovies.getName();
        String rating = currentMovies.getRating();
        byte[] videoImage = currentMovies.getVideoImage();
        String trailer = currentMovies.getTrailer();
        String voteCount = currentMovies.getVoteCount();
        MoviesDetailsFavFragment.newFavInstance(posterPath, plot, releaseDate, title, rating, videoImage, genre, trailer,voteCount);
        callback.onArticleSelected(pos);
    }

    private List<MovieFav> getListFromDb(SQLiteDatabase database) {
        List<MovieFav> list = new ArrayList<>();
        String[] projection = {
                MovieContract.MovieEntry._ID,
                MovieContract.MovieEntry.COLUMN_MOVIE_NAME,
                MovieContract.MovieEntry.COLUMN_MOVIE_GENRE,
                MovieContract.MovieEntry.COLUMN_MOVIE_TRAILER,
                MovieContract.MovieEntry.COLUMN_MOVIE_POSTER,
                MovieContract.MovieEntry.COLUMN_MOVIE_VIDEO_POSTER,
                MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE,
                MovieContract.MovieEntry.COLUMN_MOVIE_RATING,
                MovieContract.MovieEntry.COLUMN_MOVIE_PLOT,
                MovieContract.MovieEntry.COLUMN_MOVIE_VOTE_COUNT};
        Cursor cursor = database.query(TABLE_NAME, projection, null, null, null, null, null);

        //cursor.moveToNext();
        int columnIndexMovieName = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_NAME);
        int columnIndexMovieGenre = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_GENRE);
        int columnIndexMovieTrailer = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_TRAILER);
        int columnIndexMoviePoster = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER);
        int columnIndexMovieVideoPoster = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_VIDEO_POSTER);
        int columnIndexMovieReleaseDate = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE);
        int columnIndexMovieRating = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_RATING);
        int columnIndexMoviePlot = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_PLOT);
        int columnIndexMovieVoteCount = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_VOTE_COUNT);

        while (cursor.moveToNext()) {
            MovieFav movieFav = new MovieFav();
            movieFav.setName(cursor.getString(columnIndexMovieName));
            movieFav.setGenre(cursor.getString(columnIndexMovieGenre));
            movieFav.setTrailer(cursor.getString(columnIndexMovieTrailer));
            movieFav.setPosterImage(cursor.getBlob(columnIndexMoviePoster));
            movieFav.setVideoImage(cursor.getBlob(columnIndexMovieVideoPoster));
            movieFav.setReleaseDate(cursor.getString(columnIndexMovieReleaseDate));
            movieFav.setRating(cursor.getString(columnIndexMovieRating));
            movieFav.setPlot(cursor.getString(columnIndexMoviePlot));
            movieFav.setVoteCount(cursor.getString(columnIndexMovieVoteCount));
            list.add(movieFav);
        }
        cursor.close();
        return list;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fav, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_delete) {
            database = dbHelper.getReadableDatabase();
            long cnt = DatabaseUtils.queryNumEntries(database, TABLE_NAME);
            if (cnt > 0) {
                showDeleteDialog();
            }else{
                    Toast.makeText(getActivity(), "No Movies to delete :(", Toast.LENGTH_SHORT).show();
            }
            database.close();
            return true;
        }


        return false;
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("This action will delete all your favorite movies");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //deletePet();
                long rows = deleteAllMovies();
                if (rows != 0) {
                    Toast.makeText(getActivity(), rows + " Movies Deleted :)", Toast.LENGTH_SHORT).show();
                }
                itemsModels.clear();
                adapter.notifyDataSetChanged();
                if (adapter == null) {
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private long deleteAllMovies() {
        database = dbHelper.getWritableDatabase();
        long rows = database.delete(TABLE_NAME, null, null);
        emptyView.setVisibility(View.VISIBLE);
        return rows;
    }
}