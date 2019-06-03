package com.youngman.mop.view.map.presenter;

import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by YoungMan on 2019-06-03.
 */

public class MapPresenter implements MapContract.Presenter{

    private MapContract.View mapView;
//    private InfoModel infoModel;
    private LatLng latLng;
    private Location location;

    public MapPresenter(@NonNull MapContract.View mapView) {
        this.mapView = mapView;
    }

    @Override
    public void setDefaultLocationOnMap(@NonNull GoogleMap googleMap) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.setMaxZoomPreference(20);
        googleMap.setMinZoomPreference(10);
    }
}
