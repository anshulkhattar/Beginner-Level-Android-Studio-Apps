package id.merahmuda.bcd.tenant;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import es.dmoral.toasty.Toasty;
import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.BaseActivity;
import id.merahmuda.bcd.activity.BuatEventActivity;
import id.merahmuda.bcd.adapter.EventAdapter;
import id.merahmuda.bcd.helper.Constant;
import id.merahmuda.bcd.model.Event;
import id.merahmuda.bcd.model.response.ResponseEvent;
import id.merahmuda.bcd.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class EventTenantActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    Context context;
    ApiInterface apiInterface;
    FloatingActionButton fab;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_tenant);
    }

    @Override
    public void findViews() {
        fab = findViewById(R.id.fab_add_event);

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventTenantActivity.this, TambahEventActivity.class);
                startActivity(intent);
            }

        });
    }

    private void loadData() {
        Call<ResponseEvent> call = apiInterface.event();
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                if (response.isSuccessful()){
                    generateListTechnology(response.body().getData());
                }else {

                    Toast.makeText(EventTenantActivity.this, "Gagal mengambil data.", Toast.LENGTH_SHORT).show();
                }
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(GONE);
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {
                Toasty.error(EventTenantActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    public void generateListTechnology(List<Event> eventList){
        try{
            int resId = R.anim.layout_animation_from_down;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(EventTenantActivity.this, 1);
            eventAdapter = new EventAdapter(this, eventList);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(eventAdapter);
        }catch (Exception e){

        }

    }

}
