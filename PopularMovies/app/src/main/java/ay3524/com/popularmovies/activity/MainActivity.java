package ay3524.com.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ay3524.com.popularmovies.R;
import ay3524.com.popularmovies.Utils.Constants;
import ay3524.com.popularmovies.fragments.MoviesDetailsFragment;
import ay3524.com.popularmovies.fragments.MoviesPopularListFragment;

public class MainActivity extends AppCompatActivity implements MoviesPopularListFragment.OnHeadLineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        if (findViewById(R.id.content) != null) {
            if (savedInstanceState != null) {
                return;
            }
            //Create an instance of Fragment Class which has the list i.e. HeadlinesFragment
            MoviesPopularListFragment moviesPopularListFragment = new MoviesPopularListFragment();
            //In the case Activity was started with special instruction from an intent.
            //Pass the Intent's extras to the fragment as arguments
            moviesPopularListFragment.setArguments(getIntent().getExtras());
            //Ask the FragmentManager to add it to main activity's FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, moviesPopularListFragment)
                    .commit();
        }
    }

    @Override
    public void onArticleSelected(int position) {
        //Capture the article's fragment from the activity's dual-pane layout
        MoviesDetailsFragment articleFragment = (MoviesDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.articlesfragment);
        //if we dont find one we must not be in two pane mode
        //lets swap the fragments intead
        if (articleFragment != null) {
            //we must be in two pane layout
            articleFragment.updateArticleView(position);
        } else {
            //we must be in one pane layout
            //Create fragment and give it an argument for the selected article
            MoviesDetailsFragment swapFragment = new MoviesDetailsFragment();
            Bundle args = new Bundle();
            args.putInt(Constants.ARG_POSITION, position);
            swapFragment.setArguments(args);
            //now fragment is prepared swap it
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, swapFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
