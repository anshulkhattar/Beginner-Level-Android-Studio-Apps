package id.merahmuda.bcd.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.adapter.SlidingImageAdapter;
import id.merahmuda.bcd.helper.ImageSlider;

public class DetailTenantActivity extends BaseActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private Double latitude = -6.9181555;
    private Double logitude = 107.6046536;
    private GoogleMap gMap;
    private CardView cvProduk,cvGallery, cvFasilitas,cvPhoto360, cvPengaduan, cvReview;

    //Slider
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageSlider> imgList;
    private int[] img = new int[]{R.drawable.slider_b, R.drawable.slider_b};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tenant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Gallery Lukisan Bandung");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        //Informasi
        cvProduk = findViewById(R.id.cv_produk);
        cvGallery = findViewById(R.id.cv_gallery);
        cvFasilitas = findViewById(R.id.cv_fasilitas);
        cvPhoto360 = findViewById(R.id.cv_virtual_reality);
        cvPengaduan = findViewById(R.id.cv_pengaduan);
        cvReview = findViewById(R.id.cv_review);

        //map
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_detail);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void initViews() {
        imgList = new ArrayList<>();
        imgList = dataSlider();
        init();
    }

    @Override
    public void initListeners() {
        //informasi
        cvProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTenantActivity.this, ProdukActivity.class);
                startActivity(intent);
            }
        });

        cvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTenantActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });

        cvFasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTenantActivity.this, FasilitasActivity.class);
                startActivity(intent);
            }
        });

        cvPhoto360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTenantActivity.this, Photo360Activity.class);
                startActivity(intent);
            }
        });

        cvPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTenantActivity.this, PengaduanActivity.class);
                startActivity(intent);
            }
        });

        cvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTenantActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(DetailTenantActivity.this,imgList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(4 * density);

        NUM_PAGES =imgList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private ArrayList<ImageSlider> dataSlider(){

        ArrayList<ImageSlider> list = new ArrayList<>();

        for(int i = 0; i < img.length; i++){
            ImageSlider imageModel = new ImageSlider();
            imageModel.setImage_drawable(img[i]);
            list.add(imageModel);
        }
        return list;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, logitude);
        gMap.addMarker(new MarkerOptions().position(sydney).title("Gallery Lukisan Braga"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        gMap.setMinZoomPreference(15.0f);
        gMap.setMaxZoomPreference(20.0f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
