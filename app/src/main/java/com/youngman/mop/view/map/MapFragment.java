package com.youngman.mop.view.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.youngman.mop.R;
import com.youngman.mop.util.GpsUtils;
import com.youngman.mop.view.map.presenter.MapContract;
import com.youngman.mop.view.map.presenter.MapPresenter;

import static android.content.Context.LOCATION_SERVICE;

public class MapFragment extends Fragment implements MapContract.View, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Context context;
    private MapView mapView;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private MapContract.Presenter presenter;
    double latitude, longitude;

    public static MapFragment createFragment(Long clubId) {
        MapFragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("EXTRA_CLUB_ID", clubId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);
        initView(view, savedInstanceState);

        Long clubId = getArguments().getLong("EXTRA_CLUB_ID");

        return view;
    }

    private void initView(View view, Bundle savedInstanceState) {
        context = view.getContext();
        presenter = new MapPresenter(this);

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        GpsUtils.checkGpsOn(context, locationManager);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.setMaxZoomPreference(20);
        googleMap.setMinZoomPreference(10);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        mapView.getMapAsync(this);
    }
}
