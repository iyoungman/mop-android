package com.youngman.mop.view.map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.youngman.mop.R;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.map.adapter.MemberLocationsAdapter;
import com.youngman.mop.view.map.presenter.MapContract;
import com.youngman.mop.view.map.presenter.MapPresenter;

import java.util.List;

import im.delight.android.location.SimpleLocation;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private SupportMapFragment mapFragment;
    private Button btnLocationRefresh;
    private MemberLocationsAdapter memberLocationsAdapter;
    private MapContract.Presenter presenter;

    private Long clubId;
    private GoogleMap mGoogleMap;
    private SimpleLocation simpleLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();
    }

    private void init() {
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv_member_locations);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        btnLocationRefresh = (Button) findViewById(R.id.btn_location_refresh);

        memberLocationsAdapter = new MemberLocationsAdapter(context);
        recyclerView.setAdapter(memberLocationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 1);
        simpleLocation = new SimpleLocation(context, false, false, 60 * 1000, true);
        mapFragment.getMapAsync(this);

        presenter = new MapPresenter(this);
        presenter.setMemberLocationsAdapterView(memberLocationsAdapter);
        presenter.setMemberLocationsAdapterModel(memberLocationsAdapter);

        checkLocationSetting();

        btnLocationRefresh.setOnClickListener(v -> {
            presenter.callMapRefresh(clubId, SignUtils.readUserIdFromPref(context),
                    new LatLng(simpleLocation.getLatitude(), simpleLocation.getLongitude())
            );
        });
    }

    private void checkLocationSetting() {
        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(context);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.setMaxZoomPreference(20);
        mGoogleMap.setMinZoomPreference(10);

        setSimpleLocationListener();
    }

    private void setSimpleLocationListener() {
        simpleLocation.setListener(() -> {
            btnLocationRefresh.performClick();
        });
    }

    @Override
    public void mapRefresh(List<MemberLocation> otherLocations, MemberLocation myLocation) {
        otherLocations.forEach(m -> mGoogleMap.addMarker(markLocation(m)));
        mGoogleMap.addMarker(markLocation(myLocation));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation.getLatLng(), 16));
    }

    private MarkerOptions markLocation(MemberLocation memberLocation) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(memberLocation.getLatLng());
        markerOptions.title(memberLocation.getEmail());
        markerOptions.icon(decideBitmapDescriptor(memberLocation.getEmail()));

        return markerOptions;
    }

    private BitmapDescriptor decideBitmapDescriptor(String email) {
        return email.equals(SignUtils.readUserIdFromPref(context)) ?
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED) :
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
    }

    @Override
    public void moveLocation(MemberLocation memberLocation) {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(memberLocation.getLatLng(), 16));
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapFragment.onResume();
        simpleLocation.beginUpdates();
    }

    @Override
    protected void onPause() {
        simpleLocation.endUpdates();
        mapFragment.onPause();
        super.onPause();
    }
}
