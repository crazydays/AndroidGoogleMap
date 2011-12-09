package org.crazydays.map;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity
    extends MapActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupMapView();
    }

    private void setupMapView() {
        MapView map = (MapView) findViewById(R.id.map);
        map.setBuiltInZoomControls(true);
        map.setClickable(true);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}