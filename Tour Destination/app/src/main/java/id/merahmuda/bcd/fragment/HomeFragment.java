package id.merahmuda.bcd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import es.dmoral.toasty.Toasty;
import id.merahmuda.bcd.R;
import id.merahmuda.bcd.adapter.TenantAdapter;
import id.merahmuda.bcd.helper.Constant;
import id.merahmuda.bcd.model.Tenant;
import id.merahmuda.bcd.model.response.ResponseTenant;
import id.merahmuda.bcd.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ujang Wahyu on 03,Oktober,2018
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private TenantAdapter tenantAdapter;
    Context context;
    ApiInterface apiInterface;
    private ShimmerFrameLayout mShimmerViewContainer;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(int tabSelected) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("position", tabSelected);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        getActivity().setTitle("Home");
        findViews(view);
        initViews(view);
        initListeners(view);
        return view;
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
    }

    @Override
    public void initViews(View view) {
        apiInterface = Constant.getAPIService();
        loadData();
    }

    @Override
    public void initListeners(View view) {

    }

    private void loadData() {
        Call<ResponseTenant> call = apiInterface.tenant();
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseTenant>() {
            @Override
            public void onResponse(Call<ResponseTenant> call, Response<ResponseTenant> response) {
                if (response.isSuccessful()){
                    generateListTechnology(response.body().getData());
                }else {

                    Toast.makeText(getActivity(), "Gagal mengambil data.", Toast.LENGTH_SHORT).show();
                }
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseTenant> call, Throwable t) {
                Toasty.error(getContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    public void generateListTechnology(List<Tenant> tenantList){
        try{
            int resId = R.anim.layout_animation_from_down;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
            tenantAdapter = new TenantAdapter(getContext(), tenantList);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(tenantAdapter);
        }catch (Exception e){

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
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
