package id.merahmuda.bcd.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import id.merahmuda.bcd.activity.BuatEventActivity;
import id.merahmuda.bcd.adapter.EventAdapter;
import id.merahmuda.bcd.adapter.TenantAdapter;
import id.merahmuda.bcd.helper.Constant;
import id.merahmuda.bcd.model.Event;
import id.merahmuda.bcd.model.response.ResponseEvent;
import id.merahmuda.bcd.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ujang Wahyu on 03,Oktober,2018
 */
public class EventFragment extends BaseFragment{
    private static final String TAG = EventFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    Context context;
    ApiInterface apiInterface;
    FloatingActionButton fab;
    private ShimmerFrameLayout mShimmerViewContainer;

    public EventFragment() {
    }

    public static EventFragment newInstance(int tabSelected) {
        EventFragment fragment = new EventFragment();
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
        View view = inflater.inflate(R.layout.fragment_event, null);
        getActivity().setTitle("Event");
        findViews(view);
        initViews(view);
        initListeners(view);
        return view;
    }

    @Override
    public void findViews(View view) {
        fab = view.findViewById(R.id.fab_add_event);

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BuatEventActivity.class);
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

                    Toast.makeText(getActivity(), "Gagal mengambil data.", Toast.LENGTH_SHORT).show();
                }
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {
                Toasty.error(getContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    public void generateListTechnology(List<Event> eventList){
        try{
            int resId = R.anim.layout_animation_from_down;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
            eventAdapter = new EventAdapter(getContext(), eventList);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(eventAdapter);
        }catch (Exception e){

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
