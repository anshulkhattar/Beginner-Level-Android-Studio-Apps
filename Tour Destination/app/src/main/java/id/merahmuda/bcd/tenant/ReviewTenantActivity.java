package id.merahmuda.bcd.tenant;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import es.dmoral.toasty.Toasty;
import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.BaseActivity;
import id.merahmuda.bcd.activity.ReviewActivity;
import id.merahmuda.bcd.adapter.ReviewAdapter;
import id.merahmuda.bcd.helper.Constant;
import id.merahmuda.bcd.model.Review;
import id.merahmuda.bcd.model.response.ResponseReview;
import id.merahmuda.bcd.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewTenantActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    Context context;
    ApiInterface apiInterface;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_tenant);
        setTitle("Ulasan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findViews();
        initViews();
        initListeners();
        loadData();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
    }

    @Override
    public void initViews() {
        apiInterface = Constant.getAPIService();
    }

    @Override
    public void initListeners() {

    }

    private void loadData() {
        Call<ResponseReview> call = apiInterface.review();
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseReview>() {
            @Override
            public void onResponse(Call<ResponseReview> call, Response<ResponseReview> response) {
                if (response.isSuccessful()){
                    generateListReview(response.body().getData());
                }else {

                    Toast.makeText(ReviewTenantActivity.this, "Gagal mengambil data.", Toast.LENGTH_SHORT).show();
                }
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseReview> call, Throwable t) {
                Toasty.error(ReviewTenantActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    public void generateListReview(List<Review> reviewList){
        try{
            int resId = R.anim.layout_animation_from_down;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);

            reviewAdapter = new ReviewAdapter(this, reviewList);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(reviewAdapter);
        }catch (Exception e){

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        mShimmerViewContainer.startShimmerAnimation();
        super.onResume();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}
