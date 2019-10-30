package e.hp.starlentask;

import android.annotation.SuppressLint;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener, MapboxMap.OnMapClickListener {

    private MapView mapView;
    private PermissionsManager permissionsManager;

    public MapboxMap mapboxMap;
    private LocationComponent locationComponent;
    private GoogleApiClient mGoogleApiClient;


    private static final int REQUEST_AUTOCOMPLETE_CODE = 1;
    private static final String TAG = "MapD";
    DirectionsRoute currentRoute;
    private Marker destinationMarker;
    private Point destinationPostion;
    public NavigationMapRoute navigationMapRoute;
    private Location originLocation;
    private Point originPosition;
    private Marker gMarker;
    private String placeType;
    //    FloatingActionButton searchFAB;
    Button start_btn;
    ToggleButton toggle_btn;

    Double currLAT, currLONG;
    private BottomSheetBehavior bottomSheetBehavior;
    private CoordinatorLayout bottomSheetLayout;
    private RecyclerView placesRecyclerView;
    TextView btm_placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapView);


        start_btn = findViewById(R.id.button);
        mapView.getMapAsync(mapboxMap -> MainActivity.this.mapboxMap = mapboxMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        start_btn.setEnabled(false);
        start_btn.setOnClickListener(view -> {

            NavigationLauncher.startNavigation(this, NavigationLauncherOptions.builder().directionsRoute(currentRoute).shouldSimulateRoute(false).build());


        });
        mapView.onCreate(savedInstanceState);

        OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                IconFactory iconFactory = IconFactory.getInstance(MainActivity.this);
                Icon icon = iconFactory.fromResource(R.drawable.icon);
                MarkerOptions options = new MarkerOptions();
                MarkerOptions options1 = new MarkerOptions();
                MarkerOptions options2 = new MarkerOptions();
                MarkerOptions options3 = new MarkerOptions();
                MarkerOptions options4 = new MarkerOptions();
                MarkerOptions options5 = new MarkerOptions();
                //mapboxMap.addMarker(new MarkerOptions()
                options.position(new LatLng(22.6111, 75.6773));
                options.icon(icon);
                //mapboxMap.addMarker(new MarkerOptions()
                options1.position(new LatLng(17.3850, 78.4867));
                options1.icon(icon);
                //mapboxMap.addMarker(new MarkerOptions()
                options2.position(new LatLng(17.5272, 78.2321));
                options2.icon(icon);
                //mapboxMap.addMarker(new MarkerOptions()
                options3.position(new LatLng(17.7462, 78.1797));
                options3.icon(icon);
                //mapboxMap.addMarker(new MarkerOptions()
                options4.position(new LatLng(17.6612, 78.6687));
                options4.icon(icon);
                //mapboxMap.addMarker(new MarkerOptions()
                options5.position(new LatLng(17.9389, 78.8464));
                options5.icon(icon);
                mapboxMap.addMarker(options);
                mapboxMap.addMarker(options1);
                mapboxMap.addMarker(options3);
                mapboxMap.addMarker(options2);
                mapboxMap.addMarker(options4);
                mapboxMap.addMarker(options5);
                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        mapboxMap.moveCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 3));
                        mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.zoomIn());
                        mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.zoomTo(3), 2000, null);


                        destinationPostion = Point.fromLngLat(marker.getPosition().getLongitude(), marker.getPosition().getLatitude());
                        originPosition = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(), locationComponent.getLastKnownLocation().getLatitude());
                        getRoute(originPosition, destinationPostion);
                        start_btn.setEnabled(true);

                        return true;
                    }
                });
            }
        };
        mapView.getMapAsync(onMapReadyCallback);


    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "Permission Required!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {

            enableLocationComponent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        return false;
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

        mapboxMap.setStyle(Style.TRAFFIC_NIGHT, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {

                enableLocationComponent();


            }
        });
//        mapboxMap.setStyle(Style.LIGHT);

        this.mapboxMap = mapboxMap;
        mapboxMap.addOnMapClickListener(this);


//        if (locationComponent == null) {
//            mapboxMap.setStyle(Style.LIGHT);
//            enableLocationComponent();
//        }


    }


    private void getRoute(Point origin, Point destination) {
        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if (response.body() == null) {
                            Log.d(TAG, "onResponse: No ROUTES FOUND");
                            return;
                        } else if (response.body().routes().size() == 0) {
                            Toast.makeText(MainActivity.this, "No Routes Found!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        currentRoute = response.body().routes().get(0);
                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap);
                        }

                        navigationMapRoute.addRoute(currentRoute);

                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @SuppressLint("WrongConstant")
    private void enableLocationComponent() {

        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            this.locationComponent = this.mapboxMap.getLocationComponent();
            this.locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, mapboxMap.getStyle()).build());
            this.locationComponent.setLocationComponentEnabled(true);
            this.locationComponent.setCameraMode(24);
            this.locationComponent.setRenderMode(4);

            Log.d(TAG, "enableLocationComponent:  LocationComponent SET!");
            return;
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }
}
