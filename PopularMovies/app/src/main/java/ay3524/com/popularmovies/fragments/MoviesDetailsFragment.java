package ay3524.com.popularmovies.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import ay3524.com.popularmovies.R;
import ay3524.com.popularmovies.Utils.BitmapUtility;
import ay3524.com.popularmovies.Utils.Constants;
import ay3524.com.popularmovies.adapter.RecyclerAdapterCasts;
import ay3524.com.popularmovies.api.ApiClient;
import ay3524.com.popularmovies.api.ApiInterface;
import ay3524.com.popularmovies.data.MovieContract;
import ay3524.com.popularmovies.data.MovieDbHelper;
import ay3524.com.popularmovies.model.Casts;
import ay3524.com.popularmovies.model.Review;
import ay3524.com.popularmovies.model.Video;
import ay3524.com.popularmovies.response.MovieResponseCasts;
import ay3524.com.popularmovies.response.MovieResponseReviews;
import ay3524.com.popularmovies.response.MovieResponseVideos;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ay3524.com.popularmovies.Utils.Constants.API_KEY;
import static ay3524.com.popularmovies.Utils.Constants.ARG_POSITION;

/**
 * Created by Ashish on 27-10-2016.
 */
public class MoviesDetailsFragment extends Fragment implements View.OnClickListener {

    private int currentPosition = -1;
    private static String currentMoviePosterPath, currentMoviePlot, currentMovieGenre,
            currentMovieReleaseDate, currentMovieRating, currentMovieTitle,
            currentMovieId, currentMovieVideoImage, currentMovieVoteCount;
    @BindView(R.id.poster)
    ImageView imagePoster;
    @BindView(R.id.imageView)
    ImageView imageView;
    @Nullable @BindView(R.id.backButton) ImageView imageViewBack;
    @BindView(R.id.playicon)
    ImageView imagePlayIcon;
    @BindView(R.id.shareButton)
    ImageView shareButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.releaseDate)
    TextView releaseDate;
    @BindView(R.id.votes)
    TextView voteCount;
    @BindView(R.id.plot)
    TextView plot;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.genre)
    TextView genre;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.linearlayoutreviews)
    LinearLayout linearLayout;
    @BindView(R.id.castList)
    RecyclerView recyclerViewCast;
    private String youtubeUrl;
    private List<Video> moviesTrailerList;
    private List<Review> moviesReviewList;
    private List<Casts> moviesCastList;
    private int z;
    private SQLiteDatabase database;
    private RecyclerAdapterCasts adapterCasts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        MovieDbHelper dbHelper = new MovieDbHelper(getActivity());
        View v = inflater.inflate(R.layout.activity_scrolling, container, false);
        ButterKnife.bind(this, v);
        linearLayout.removeAllViews();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCast.setLayoutManager(layoutManager);

        imageView.setOnClickListener(this);
        fab.setOnClickListener(this);
        imagePlayIcon.setOnClickListener(this);
        shareButton.setOnClickListener(this);

        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (!tabletSize) {
            if (imageViewBack != null) {
                imageViewBack.setOnClickListener(this);
            }
        }
        database = dbHelper.getWritableDatabase();

        if (matchWithDataBase()) {
            fab.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_star_10));
        }
        return v;
    }

    private boolean matchWithDataBase() {
        String[] projection = {
                MovieContract.MovieEntry._ID,
                MovieContract.MovieEntry.COLUMN_MOVIE_NAME};

        Cursor cursor = database.query(MovieContract.MovieEntry.TABLE_NAME, projection, null, null, null, null, null);
        //cursor.moveToNext();
        int columnIndexMovieName = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_NAME);
        while (cursor.moveToNext()) {
            String movieName = cursor.getString(columnIndexMovieName);
            if (movieName.equalsIgnoreCase(currentMovieTitle)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public void updateArticleView(int position) {
        currentPosition = position;
        updateUi();
        getYoutubeUrl();
        getCast();
        getReviews();
    }

    private void getCast() {
        ApiInterface apiService =
                ApiClient.getClient(getActivity()).create(ApiInterface.class);
        Call<MovieResponseCasts> call = apiService.getMovieCast(Integer.parseInt(currentMovieId), API_KEY);
        call.enqueue(new Callback<MovieResponseCasts>() {
            @Override
            public void onResponse(Call<MovieResponseCasts> call, Response<MovieResponseCasts> response) {
                if (moviesCastList != null)
                    moviesCastList.clear();
                moviesCastList = response.body().getCasts();
                adapterCasts = new RecyclerAdapterCasts(moviesCastList, getActivity());
                recyclerViewCast.setAdapter(adapterCasts);
            }

            @Override
            public void onFailure(Call<MovieResponseCasts> call, Throwable t) {
                // Log error here since request failed
                Log.e("TAG", t.toString());
            }
        });
    }

    private void getYoutubeUrl() {
        ApiInterface apiService =
                ApiClient.getClient(getActivity()).create(ApiInterface.class);
        Call<MovieResponseVideos> call = apiService.getMovieTrailers(Integer.parseInt(currentMovieId), API_KEY);
        call.enqueue(new Callback<MovieResponseVideos>() {
            @Override
            public void onResponse(Call<MovieResponseVideos> call, Response<MovieResponseVideos> response) {
                if (moviesTrailerList != null)
                    moviesTrailerList.clear();
                moviesTrailerList = response.body().getVideos();
                try {
                    youtubeUrl = Constants.BASE_YOUTUBE_URI + moviesTrailerList.get(0).getYoutubeKey();
                } catch (IndexOutOfBoundsException e) {
                    z = 1;
                    Toast.makeText(getActivity(), "No trailer for movie", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponseVideos> call, Throwable t) {
                // Log error here since request failed
                Log.e("TAG", t.toString());
            }
        });
    }

    private void getReviews() {
        ApiInterface apiService =
                ApiClient.getClient(getActivity()).create(ApiInterface.class);
        Call<MovieResponseReviews> call = apiService.getMovieReviews(Integer.parseInt(currentMovieId), API_KEY);
        call.enqueue(new Callback<MovieResponseReviews>() {
            @Override
            public void onResponse(Call<MovieResponseReviews> call, Response<MovieResponseReviews> response) {

                if (response.body().getTotalResults() != 0) {
                    linearLayout.removeAllViews();
                    if (moviesReviewList != null)
                        moviesReviewList.clear();
                    moviesReviewList = response.body().getReview();
                    makeReviewsTextviews();
                } else {
                    linearLayout.removeAllViews();
                    noReviewsTextViews();
                }
            }

            @Override
            public void onFailure(Call<MovieResponseReviews> call, Throwable t) {
                // Log error here since request failed
                Log.e("TAG", t.toString());
            }
        });
    }

    private void noReviewsTextViews() {
        try {
            TextView textView1 = new TextView(getActivity());
            textView1.setTextSize(20);
            textView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView1.setText(getResources().getString(R.string.noreview));
            textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
            linearLayout.addView(textView1);
        } catch (NullPointerException ignored) {
            Log.e("MovieDetailsFragment", "Error while defining no reviews values");
        }
    }

    private void makeReviewsTextviews() {
        TextView[] textViews = new TextView[moviesReviewList.size()];
        TextView[] textViewsAuthors = new TextView[moviesReviewList.size()];
        for (int i = 0; i < moviesReviewList.size(); i++) {
            try {
                textViewsAuthors[i] = new TextView(getActivity());
                textViews[i] = new TextView(getActivity());

                textViewsAuthors[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                textViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                textViewsAuthors[i].setText(moviesReviewList.get(i).getAuthor());
                textViewsAuthors[i].setTextSize(18);
                textViews[i].setText(moviesReviewList.get(i).getContent());

                textViewsAuthors[i].setTypeface(null, Typeface.BOLD);
                if (getResources().getBoolean(R.bool.isTablet)) {
                    textViewsAuthors[i].setPadding(40, 5, 10, 5);
                    textViews[i].setPadding(60, 5, 60, 10);// in pixels (left, top, right, bottom )
                } else {
                    textViewsAuthors[i].setPadding(10, 5, 10, 5);
                    textViews[i].setPadding(20, 5, 20, 10);// in pixels (left, top, right, bottom )
                }
                Linkify.addLinks(textViews[i], Linkify.ALL);
                //textViews[i].setLinksClickable(true);

                linearLayout.addView(textViewsAuthors[i]);
                linearLayout.addView(textViews[i]);

            } catch (NullPointerException ignored) {
                Log.e("MovieDetailsFragment", "Error while defining reviews values");
            }
        }
    }

    public void updateUi() {

        try {
            Glide.clear(imageView);
            Glide.with(getActivity())
                    .load(Constants.FINAL_VIDEO_POSTER_URI + currentMovieVideoImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model,
                                                       Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache, boolean isFirstResource) {

                            return false;
                        }
                    })
                    .into(imageView);
            //Picasso.with(getActivity()).load(Constants.FINAL_VIDEO_POSTER_URI + currentMovieVideoImage).into(imageView);
            Glide.clear(imagePoster);
            Glide.with(getActivity())
                    .load(Constants.FINAL_POSTER_URI + currentMoviePosterPath)
                    .placeholder(R.drawable.icon)
                    .error(R.drawable.icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model,
                                                       Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache, boolean isFirstResource) {

                            return false;
                        }
                    })
                    .into(imagePoster);
            //Picasso.with(getActivity()).load(Constants.FINAL_POSTER_URI + currentMoviePosterPath).into(imagePoster);
            title.setText(currentMovieTitle);
            String formattedDate = Constants.convertDate(currentMovieReleaseDate);
            releaseDate.setText(formattedDate);
            rating.setText(String.valueOf((int) (Double.parseDouble(currentMovieRating) * 10)).concat("%"));
            plot.setText(currentMoviePlot);
            voteCount.setText(currentMovieVoteCount.concat(" votes"));
            genre.setText(currentMovieGenre);
        } catch (Exception e) {
            Log.e("MovieDetailsFragment", "Error while defining reviews values");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        usingBundleUpdateArticleView();
    }

    @Override
    public void onStart() {
        super.onStart();
        usingBundleUpdateArticleView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        usingBundleUpdateArticleView();
        outState.putInt(ARG_POSITION, currentPosition);
    }

    private void usingBundleUpdateArticleView() {
        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (currentPosition != -1) {
            updateArticleView(currentPosition);
        }
    }

    public static void newInstance(String posterPath, String plot, String releaseDate,
                                   String id, String title, String rating,
                                   String videoImage, String genres, String voteCount) {
        currentMovieGenre = genres;
        currentMoviePosterPath = posterPath;
        currentMoviePlot = plot;
        currentMovieReleaseDate = releaseDate;
        currentMovieId = id;
        currentMovieTitle = title;
        currentMovieRating = rating;
        currentMovieVideoImage = videoImage;
        currentMovieVoteCount = voteCount;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                launchTrailer();
                break;
            case R.id.playicon:
                launchTrailer();
                break;
            case R.id.backButton:
                getActivity().onBackPressed();
                break;
            case R.id.shareButton:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, youtubeUrl);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case R.id.fab:

                if (matchWithDataBase()) {
                    //Toast.makeText(getActivity(), "Already Added as favorite", Toast.LENGTH_SHORT).show();
                    if (deleteTitle(currentMovieTitle)) {
                        Toast.makeText(getActivity(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_star_0));
                    } else {
                        Toast.makeText(getActivity(), "Unable to remove from favorites", Toast.LENGTH_SHORT).show();
                        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_star_10));
                    }
                } else {
                    imageView.invalidate();
                    imagePoster.invalidate();
                    imageView.buildDrawingCache();
                    imagePoster.buildDrawingCache();

                    if (currentMovieTitle != null && currentMovieGenre != null
                            && youtubeUrl != null && imageView.getDrawingCache() != null
                            && imagePoster.getDrawingCache() != null && currentMovieReleaseDate != null
                            && currentMovieRating != null && currentMoviePlot != null) {

                        long id = insertMovieInDatabase();
                        if (id == -1) {
                            fab.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_star_0));
                            Toast.makeText(getActivity(), "Unable to save.Press once again", Toast.LENGTH_SHORT).show();
                        } else {
                            fab.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_action_star_10));
                            Toast.makeText(getActivity(), "Movie added as favorite", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                break;
        }
    }

    private long insertMovieInDatabase() {
        ContentValues cv = new ContentValues();

        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_NAME, currentMovieTitle);
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_GENRE, currentMovieGenre);
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_TRAILER, youtubeUrl);
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER, BitmapUtility.getBytes(imagePoster.getDrawingCache()));
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_VIDEO_POSTER, BitmapUtility.getBytes(imageView.getDrawingCache()));
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE, currentMovieReleaseDate);
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_RATING, currentMovieRating);
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_PLOT, currentMoviePlot);
        cv.put(MovieContract.MovieEntry.COLUMN_MOVIE_VOTE_COUNT, currentMovieVoteCount);

        return database.insert(MovieContract.MovieEntry.TABLE_NAME, null, cv);
    }

    public boolean deleteTitle(String name) {
        return database.delete(MovieContract.MovieEntry.TABLE_NAME, MovieContract.MovieEntry.COLUMN_MOVIE_NAME + "=?", new String[]{name}) > 0;
    }

    private void launchTrailer() {
        if (youtubeUrl != null) {
            Uri vidUri = Uri.parse(youtubeUrl);
            // Create a new intent to view the movie URI
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, vidUri);
            // Send the intent to launch a new activity
            startActivity(websiteIntent);
        } else if (z == 1) {
            Toast.makeText(getActivity(), "Sorry No Trailer for this movie", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Click again when the link is loaded!", Toast.LENGTH_SHORT).show();
        }
    }
}
