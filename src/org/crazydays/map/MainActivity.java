package org.crazydays.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity
    extends MapActivity {

    private LocationManager locationManager;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupMapView();
        setupLocationManager();
    }

    private void setupMapView() {
        mapView = (MapView) findViewById(R.id.map);
        mapView.setBuiltInZoomControls(true);
        mapView.setClickable(true);
    }

    private void setupLocationManager() {
        locationManager =
            (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mapView.getController().setZoom(11);

        // update last known location
        locationListener.onLocationChanged(locationManager
            .getLastKnownLocation(LocationManager.GPS_PROVIDER));
    }

    @Override
    protected void onStart() {
        super.onStart();

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            10000L, 0.0F, locationListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateLocation(location.getLatitude(), location.getLongitude());
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private void updateLocation(double latitude, double longitude) {
        mapView.getController().animateTo(
            new GeoPoint(toE6(latitude), toE6(longitude)));
    }

    private int toE6(double x) {
        return (int) (x * 1000000);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}