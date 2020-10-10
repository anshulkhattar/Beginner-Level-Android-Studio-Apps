package com.amary.app.data.moviecat.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.base.BaseActivity;
import com.amary.app.data.moviecat.data.database.datasource.MovieRepository;
import com.amary.app.data.moviecat.data.database.local.LocalDatabase;
import com.amary.app.data.moviecat.data.database.local.MovieDataSource;
import com.amary.app.data.moviecat.data.database.model_db.Movie;
import com.amary.app.data.moviecat.data.networking.model.DetailMovie;
import com.amary.app.data.moviecat.data.networking.model.GetImageMovie;
import com.amary.app.data.moviecat.data.networking.model.ImageMovieItem;
import com.amary.app.data.moviecat.data.networking.model.ResultMovie;
import com.amary.app.data.moviecat.ui.adapter.DetailMovieAdapter;
import com.amary.app.data.moviecat.utils.DateConvert;
import com.amary.app.data.moviecat.utils.ImgDownload;
import com.amary.app.data.moviecat.utils.LocalData;
import com.amary.app.data.moviecat.view.DetailMovieView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends BaseActivity implements DetailMovieView {
    public static final String EXTRA_MOVIE = "id_movie";
    private ArrayList<ImageMovieItem> imageMovieItems = new ArrayList<>();
    private DetailMovieAdapter detailMovieAdapter;
    private DetailMovie detailDatMovie = new DetailMovie();

    public DetailMovie getDetailDatMovie() {
        return detailDatMovie;
    }
    public void setDetailDatMovie(DetailMovie detailDatMovie) {
        this.detailDatMovie = new DetailMovie();
        this.detailDatMovie = detailDatMovie;
    }

    @BindView(R.id.txt_info_title_movie)
    TextView txtTitleMovie;
    @BindView(R.id.txt_info_tanggal_movie)
    TextView txtTanggalMovie;
    @BindView(R.id.txt_info_score_movie)
    TextView txtScoreMovie;
    @BindView(R.id.txt_info_genre_movie)
    TextView txtGenreMovie;
    @BindView(R.id.txt_info_production_movie)
    TextView txtProductionMovie;
    @BindView(R.id.txt_info_homepage_movie)
    TextView txtHomePageMovie;
    @BindView(R.id.txt_info_overview_movie)
    TextView txtSinopsisMovie;
    @BindView(R.id.img_info_poster_movie)
    ImageView imgPosterMovie;
    @BindView(R.id.img_info_backdroppath_movie)
    ImageView imgBackdroppathMovie;
    @BindView(R.id.tv_genre)
    TextView tvGenre;
    @BindView(R.id.tv_production)
    TextView tvProduction;
    @BindView(R.id.tv_homepage)
    TextView tvHomepage;
    @BindView(R.id.tv_sinopsis)
    TextView tvSinopsis;
    @BindView(R.id.rv_screenshot)
    RecyclerView rvScreenshot;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_images)
    TextView tvImages;
    @BindView(R.id.v_1)
    View vLine1;
    @BindView(R.id.v_2)
    View vLine2;
    @BindView(R.id.v_3)
    View vLine3;
    @BindView(R.id.btn_set_favorite_movie)
    Button btnSetFavoriteMovie;
    @BindView(R.id.tv_detail_error_movie)
    TextView tvDetailErrorMovie;
    @BindView(R.id.progressBar_movie)
    ProgressBar pbLoadingDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        String bhsLocal = getString(R.string.localization);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.movies);

        ResultMovie resultMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        txtTitleMovie.setText(Objects.requireNonNull(resultMovie).getTitle());

        showImage();
        getDB();

        if (savedInstanceState == null){
            getDetailMoviePresenter().getDetailMovie(resultMovie.getId(),bhsLocal,this);
            getDetailMoviePresenter().getImageMovie(resultMovie.getId(),bhsLocal,this);
        }else {
            imageMovieItems = savedInstanceState.getParcelableArrayList(KEY_IMAGE_MOVIE);
            detailDatMovie = savedInstanceState.getParcelable(KEY_DETAIL_MOVIE);
            detailMovieAdapter.refillImage(imageMovieItems);
            setDetailDatMovie(detailDatMovie);
            dataDetail(getDetailDatMovie());
        }

        if (LocalData.movieRepository.isMovie(resultMovie.getId()) == 1){
            btnSetFavoriteMovie.setText(R.string.remove_to_favorite);
        }else {
            btnSetFavoriteMovie.setText(R.string.add_to_favorite);
        }

        btnSetFavoriteMovie.setOnClickListener(v -> {
            if (LocalData.movieRepository.isMovie(resultMovie.getId()) != 1){
                addMovieFavorite(resultMovie, true);
                btnSetFavoriteMovie.setText(R.string.remove_to_favorite);
            }else {
                addMovieFavorite(resultMovie, false);
                btnSetFavoriteMovie.setText(R.string.add_to_favorite);
            }

        });
    }

    private void addMovieFavorite(ResultMovie resultMovie, boolean isAdd) {
        Movie movie = new Movie(resultMovie.getId(),
                resultMovie.getTitle(),
                resultMovie.getReleaseDate(),
                resultMovie.getVoteAverage(),
                resultMovie.getPosterPath(),
                resultMovie.getBackdropPath());


        if (isAdd){
            LocalData.movieRepository.insertItemMovie(movie);
        }else {
            LocalData.movieRepository.deleteItemMovie(movie);
        }
    }

    private void getDB() {
        LocalData.localDatabase = LocalDatabase.getInstance(this);
        LocalData.movieRepository = MovieRepository.getInstance(MovieDataSource.getInstance(LocalData.localDatabase.movieDAO()));
    }


    private void showImage(){
        detailMovieAdapter = new DetailMovieAdapter(imageMovieItems);
        rvScreenshot.setHasFixedSize(true);
        rvScreenshot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvScreenshot.setAdapter(detailMovieAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void dataDetail(DetailMovie detailMovie){
        String[] gnrMovie = new String[detailMovie.getGenres().size()];
        String[] pdrMovie = new String[detailMovie.getProductionCompanies().size()];

        txtTanggalMovie.setText(DateConvert.convert(detailMovie.getReleaseDate()));
        txtScoreMovie.setText(detailMovie.getVoteAverage().toString());
        for (int i=0; i < detailMovie.getGenres().size();i++ ){
            gnrMovie[i] = detailMovie.getGenres().get(i).getName();
            printArray(gnrMovie,txtGenreMovie);
        }
        for (int m = 0;m < detailMovie.getProductionCompanies().size() ;m++){
            pdrMovie[m] = detailMovie.getProductionCompanies().get(m).getName();
            printArray(pdrMovie,txtProductionMovie);
        }
        txtHomePageMovie.setText(detailMovie.getHomepage());
        txtSinopsisMovie.setText(detailMovie.getOverview());

        ImgDownload.imgPoster(detailMovie.getPosterPath(),imgPosterMovie);
        ImgDownload.imgPoster(detailMovie.getBackdropPath(),imgBackdroppathMovie);
    }

    @Override
    public void showLoading() {
        pbLoadingDetail.setVisibility(View.VISIBLE);
        tvGenre.setVisibility(View.GONE);
        tvProduction.setVisibility(View.GONE);
        tvHomepage.setVisibility(View.GONE);
        tvSinopsis.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        pbLoadingDetail.setVisibility(View.GONE);
        tvGenre.setVisibility(View.VISIBLE);
        tvProduction.setVisibility(View.VISIBLE);
        tvHomepage.setVisibility(View.VISIBLE);
        tvSinopsis.setVisibility(View.VISIBLE);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void onErrorData() {
        pbLoadingDetail.setVisibility(View.GONE);
        tvGenre.setVisibility(View.GONE);
        tvProduction.setVisibility(View.GONE);
        tvHomepage.setVisibility(View.GONE);
        tvSinopsis.setVisibility(View.GONE);
        tvDescription.setVisibility(View.GONE);
        tvImages.setVisibility(View.GONE);
        txtTanggalMovie.setVisibility(View.GONE);
        txtGenreMovie.setVisibility(View.GONE);
        txtScoreMovie.setVisibility(View.GONE);
        txtProductionMovie.setVisibility(View.GONE);
        txtHomePageMovie.setVisibility(View.GONE);
        txtSinopsisMovie.setVisibility(View.GONE);
        imgPosterMovie.setVisibility(View.GONE);
        imgBackdroppathMovie.setVisibility(View.GONE);
        btnSetFavoriteMovie.setVisibility(View.GONE);
        rvScreenshot.setVisibility(View.GONE);
        vLine1.setVisibility(View.GONE);
        vLine2.setVisibility(View.GONE);
        vLine3.setVisibility(View.GONE);
        tvDetailErrorMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDetailMovie(DetailMovie detailMovie) {
        setDetailDatMovie(detailMovie);
        dataDetail(getDetailDatMovie());
    }

    @Override
    public void setImageMovie(GetImageMovie imageMovie) {
        imageMovieItems = imageMovie.getBackdrops();
        detailMovieAdapter.refillImage(imageMovieItems);
    }

    private static void printArray(String[] anArray, TextView textView) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(anArray[i]);
        }
        textView.setText(sb.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_IMAGE_MOVIE, imageMovieItems);
        outState.putParcelable(KEY_DETAIL_MOVIE, detailDatMovie);
        super.onSaveInstanceState(outState);
    }
}
