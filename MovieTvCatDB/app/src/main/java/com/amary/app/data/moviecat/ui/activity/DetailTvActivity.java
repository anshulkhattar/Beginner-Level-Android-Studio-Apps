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
import com.amary.app.data.moviecat.data.database.datasource.TvRepository;
import com.amary.app.data.moviecat.data.database.local.LocalDatabase;
import com.amary.app.data.moviecat.data.database.local.TvDataSource;
import com.amary.app.data.moviecat.data.database.model_db.Tv;
import com.amary.app.data.moviecat.data.networking.model.DetailTv;
import com.amary.app.data.moviecat.data.networking.model.GetImageTv;
import com.amary.app.data.moviecat.data.networking.model.ImageTvItem;
import com.amary.app.data.moviecat.data.networking.model.ResultTv;
import com.amary.app.data.moviecat.ui.adapter.DetailTvAdapter;
import com.amary.app.data.moviecat.utils.DateConvert;
import com.amary.app.data.moviecat.utils.ImgDownload;
import com.amary.app.data.moviecat.utils.LocalData;
import com.amary.app.data.moviecat.view.DetailTVView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTvActivity extends BaseActivity implements DetailTVView {
    public static final String EXTRA_TV = "id_tv";
    private ArrayList<ImageTvItem> imageTvItems = new ArrayList<>();
    private DetailTvAdapter detailTvAdapter;
    private DetailTv detailTvItem = new DetailTv();

    public DetailTv getDetailTvItem() {
        return detailTvItem;
    }

    public void setDetailTvItem(DetailTv detailTvItem) {
        this.detailTvItem = new DetailTv();
        this.detailTvItem = detailTvItem;
    }

    @BindView(R.id.txt_info_title_tv)
    TextView txtTitleTv;
    @BindView(R.id.txt_info_tanggal_tv)
    TextView txtTanggalTv;
    @BindView(R.id.txt_info_score_tv)
    TextView txtScoreTv;
    @BindView(R.id.txt_info_genre_tv)
    TextView txtGenreTv;
    @BindView(R.id.txt_info_production_tv)
    TextView txtProductionTv;
    @BindView(R.id.txt_info_homepage_tv)
    TextView txtHomePageTv;
    @BindView(R.id.txt_info_overview_tv)
    TextView txtSinopsisTv;
    @BindView(R.id.img_info_poster_tv)
    ImageView imgPosterTv;
    @BindView(R.id.img_info_backdroppath_tv)
    ImageView imgbackdropPathTv;
    @BindView(R.id.rv_screenshot)
    RecyclerView rvScreenshot;
    @BindView(R.id.tv_genre)
    TextView tvGenre;
    @BindView(R.id.tv_production)
    TextView tvProduction;
    @BindView(R.id.tv_homepage)
    TextView tvHomepage;
    @BindView(R.id.tv_sinopsis)
    TextView tvSinopsis;
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
    @BindView(R.id.btn_set_favorite_tv)
    Button btnSetFavoriteTv;
    @BindView(R.id.tv_detail_error_tv)
    TextView tvDetailErrorMovie;
    @BindView(R.id.progressBar_tv)
    ProgressBar pbLoadingDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        ButterKnife.bind(this);
        String bhsLocal = getString(R.string.localization);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ResultTv resultTv = getIntent().getParcelableExtra(EXTRA_TV);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.tvshow);
        txtTitleTv.setText(resultTv.getName());

        showImage();
        getDB();

        if (savedInstanceState == null) {
            getDetailTvPresenter().getDetailTv(resultTv.getId(), bhsLocal, this);
            getDetailTvPresenter().getImageTv(resultTv.getId(), bhsLocal, this);
        } else {
            imageTvItems = savedInstanceState.getParcelableArrayList(KEY_IMAGE_TV);
            detailTvItem = savedInstanceState.getParcelable(KEY_DETAIL_TV);
            detailTvAdapter.refillImage(imageTvItems);
            setDetailTvItem(detailTvItem);
            dataDetail(getDetailTvItem());
        }

        if (LocalData.tvRepository.isTv(resultTv.getId()) == 1) {
            btnSetFavoriteTv.setText(R.string.remove_to_favorite);
        } else {
            btnSetFavoriteTv.setText(R.string.add_to_favorite);
        }

        btnSetFavoriteTv.setOnClickListener(v -> {
            if (LocalData.tvRepository.isTv(resultTv.getId()) != 1) {
                addTvFavorite(resultTv, true);
                btnSetFavoriteTv.setText(R.string.remove_to_favorite);
            } else {
                addTvFavorite(resultTv, false);
                btnSetFavoriteTv.setText(R.string.add_to_favorite);
            }

        });

    }

    private void addTvFavorite(ResultTv resultTv, boolean isAdd) {
        Tv tv = new Tv(resultTv.getId(),
                resultTv.getName(),
                resultTv.getFirstAirDate(),
                resultTv.getVoteAverage(),
                resultTv.getPosterPath(),
                resultTv.getBackdropPath());

        if (isAdd) {
            LocalData.tvRepository.insertItemTv(tv);
        } else {
            LocalData.tvRepository.deleteItemTv(tv);
        }
    }

    private void getDB() {
        LocalData.localDatabase = LocalDatabase.getInstance(this);
        LocalData.tvRepository = TvRepository.getInstance(TvDataSource.getInstance(LocalData.localDatabase.tvDAO()));
    }

    private void showImage() {
        detailTvAdapter = new DetailTvAdapter(imageTvItems);
        rvScreenshot.setHasFixedSize(true);
        rvScreenshot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvScreenshot.setAdapter(detailTvAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void dataDetail(DetailTv detailTv) {
        String[] gnrTv = new String[detailTv.getGenres().size()];
        String[] pdrTv = new String[detailTv.getProductionCompanies().size()];

        txtTanggalTv.setText(DateConvert.convert(detailTv.getFirstAirDate()));
        txtScoreTv.setText(detailTv.getVoteAverage().toString());
        for (int i = 0; i < detailTv.getGenres().size(); i++) {
            gnrTv[i] = detailTv.getGenres().get(i).getName();
            printArray(gnrTv, txtGenreTv);
        }
        for (int m = 0; m < detailTv.getProductionCompanies().size(); m++) {
            pdrTv[m] = detailTv.getProductionCompanies().get(m).getName();
            printArray(pdrTv, txtProductionTv);
        }
        txtHomePageTv.setText(detailTv.getHomepage());
        txtSinopsisTv.setText(detailTv.getOverview());
        ImgDownload.imgPoster(detailTv.getPosterPath(), imgPosterTv);
        ImgDownload.imgPoster(detailTv.getBackdropPath(), imgbackdropPathTv);
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
        txtTanggalTv.setVisibility(View.GONE);
        txtGenreTv.setVisibility(View.GONE);
        txtScoreTv.setVisibility(View.GONE);
        txtProductionTv.setVisibility(View.GONE);
        txtHomePageTv.setVisibility(View.GONE);
        txtSinopsisTv.setVisibility(View.GONE);
        imgPosterTv.setVisibility(View.GONE);
        imgbackdropPathTv.setVisibility(View.GONE);
        btnSetFavoriteTv.setVisibility(View.GONE);
        rvScreenshot.setVisibility(View.GONE);
        vLine1.setVisibility(View.GONE);
        vLine2.setVisibility(View.GONE);
        vLine3.setVisibility(View.GONE);
        tvDetailErrorMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDetailTv(DetailTv detailTv) {
        setDetailTvItem(detailTv);
        dataDetail(getDetailTvItem());
    }

    @Override
    public void setImageTv(GetImageTv imageTv) {
        imageTvItems = imageTv.getBackdrops();
        detailTvAdapter.refillImage(imageTvItems);
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
        outState.putParcelableArrayList(KEY_IMAGE_TV, imageTvItems);
        outState.putParcelable(KEY_DETAIL_TV, detailTvItem);
        super.onSaveInstanceState(outState);
    }
}
