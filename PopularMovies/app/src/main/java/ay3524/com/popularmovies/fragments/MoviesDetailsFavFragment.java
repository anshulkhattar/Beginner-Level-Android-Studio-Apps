package ay3524.com.popularmovies.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ay3524.com.popularmovies.R;
import ay3524.com.popularmovies.Utils.BitmapUtility;
import ay3524.com.popularmovies.Utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

import static android.view.View.GONE;
import static ay3524.com.popularmovies.Utils.Constants.ARG_POSITION;

/**
 * Created by Ashish on 02-12-2016.
 */

public class MoviesDetailsFavFragment extends Fragment implements View.OnClickListener {
    private static byte[] currentMovieFavPosterPath,currentMovieFavVideoImage;
    private static String currentMovieTrailer,currentMoviePlot,currentMovieReleaseDate,
                          currentMovieRating,currentMovieTitle,currentMovieGenre,currentMovieVoteCount;
    private int currentPosition = -1;
    @BindView(R.id.poster) ImageView imagePoster;
    @BindView(R.id.imageView) ImageView imageView;
    @Nullable @BindView(R.id.backButton) ImageView imageViewBack;
    @BindView(R.id.playicon) ImageView imagePlayIcon;
    @BindView(R.id.shareButton) ImageView shareButton;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.releaseDate) TextView releaseDate;
    @BindView(R.id.votes) TextView voteCount;
    @BindView(R.id.plot) TextView plot;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.genre) TextView genre;
    @BindView(R.id.linearlayoutreviews) LinearLayout linearLayoutReviews;
    @BindView(R.id.review) TextView reviews;
    @BindView(R.id.cast) TextView cast;
    @Nullable @BindView(R.id.synopsisBelowLine) View synoBelowLine;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        View v = inflater.inflate(R.layout.activity_scrolling, container, false);
        ButterKnife.bind(this,v);

        reviews.setVisibility(GONE);
        linearLayoutReviews.setVisibility(GONE);
        fab.setVisibility(GONE);
        if(synoBelowLine!=null)
        synoBelowLine.setVisibility(GONE);
        cast.setVisibility(GONE);

        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (!tabletSize) {
            imageViewBack.setOnClickListener(this);
        }
        imagePlayIcon.setOnClickListener(this);
        imageView.setOnClickListener(this);
        shareButton.setOnClickListener(this);

        return v;
    }

    public void updateArticleView(int position) {
        currentPosition = position;
            updateUi();
    }

    public void updateUi() {
        try{
            imagePoster.setImageBitmap(BitmapUtility.getImage(currentMovieFavPosterPath));
            imageView.setImageBitmap(BitmapUtility.getImage(currentMovieFavVideoImage));

            title.setText(currentMovieTitle);
            String formattedDate = Constants.convertDate(currentMovieReleaseDate);
            releaseDate.setText(formattedDate);
            rating.setText(String.valueOf((int)(Double.parseDouble(currentMovieRating)*10)).concat("%"));
            plot.setText(currentMoviePlot);
            voteCount.setText(currentMovieVoteCount.concat(" votes"));
            genre.setText(currentMovieGenre);
        }catch (Exception e){
            Log.e("MovieDetailsFavFragment","Error while assigning values");
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

    public static void newFavInstance(byte[] poster, String plot, String releaseDate,
                                      String title, String rating, byte[] videoImage,
                                      String genres, String trailer,String voteCount) {
        currentMovieGenre = genres;
        currentMovieFavPosterPath = poster;
        currentMoviePlot = plot;
        currentMovieReleaseDate = releaseDate;
        currentMovieTitle = title;
        currentMovieRating = rating;
        currentMovieFavVideoImage = videoImage;
        currentMovieTrailer = trailer;
        currentMovieVoteCount = voteCount;
    }

    @Optional
    @Override
    public void onClick(View v) {
       switch (v.getId()){
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
               sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, currentMovieTrailer);
               startActivity(Intent.createChooser(sharingIntent, "Share via"));
               break;
       }
    }
    public void launchTrailer(){
        if (currentMovieTrailer != null) {
            Uri vidUri = Uri.parse(currentMovieTrailer);
            // Create a new intent to view the movie URI
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, vidUri);
            // Send the intent to launch a new activity
            startActivity(websiteIntent);
        }
    }
    private void usingBundleUpdateArticleView() {
        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (currentPosition != -1) {
            updateArticleView(currentPosition);
        }
    }
}
