package id.merahmuda.bcd.activity;

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
import id.merahmuda.bcd.adapter.GalleryAdapter;
import id.merahmuda.bcd.adapter.ProdukAdapter;
import id.merahmuda.bcd.helper.Constant;
import id.merahmuda.bcd.model.Produk;
import id.merahmuda.bcd.model.response.ResponseProduk;
import id.merahmuda.bcd.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ProdukAdapter produkAdapter;
    Context context;
    ApiInterface apiInterface;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);
        setTitle("Produk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
    }

    @Override
    public void initViews() {
        apiInterface = Constant.getAPIService();
        loadData();
    }

    @Override
    public void initListeners() {

    }

    private void loadData() {
        Call<ResponseProduk> call = apiInterface.produk();
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseProduk>() {
            @Override
            public void onResponse(Call<ResponseProduk> call, Response<ResponseProduk> response) {
                if (response.isSuccessful()){
                    generateListTechnology(response.body().getData());
                }else {

                    Toast.makeText(ProdukActivity.this, "Gagal mengambil data.", Toast.LENGTH_SHORT).show();
                }
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseProduk> call, Throwable t) {
                Toasty.error(ProdukActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    public void generateListTechnology(List<Produk> produkList){
        try{
            int resId = R.anim.layout_animation_from_down;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
            produkAdapter = new ProdukAdapter(this, produkList);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(produkAdapter);
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
