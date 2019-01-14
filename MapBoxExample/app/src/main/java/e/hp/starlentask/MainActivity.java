package e.hp.starlentask;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements LocationEngineListener, PermissionsListener {


    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapview);
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
                        mapboxMap.moveCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLngZoom(marker.getPosition(),10));
                        mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.zoomIn());
                        mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.zoomTo(10),2000,null);
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
    public void onConnected() {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }
}
