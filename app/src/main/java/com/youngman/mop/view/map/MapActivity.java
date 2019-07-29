package com.youngman.mop.view.map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.config.GoogleDirectionConfiguration;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.common.collect.Iterables;
import com.google.maps.android.ui.IconGenerator;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.youngman.mop.R;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.util.DateUtils;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.map.adapter.MemberLocationsAdapter;
import com.youngman.mop.view.map.presenter.MapContract;
import com.youngman.mop.view.map.presenter.MapPresenter;
import com.youngman.mop.view.mapmemberadd.MapMemberAddActivity;

import java.util.ArrayList;
import java.util.List;

import im.delight.android.location.SimpleLocation;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapContract.View, DirectionCallback {

    private Context context;
    private RecyclerView recyclerView;
    private SupportMapFragment mapFragment;
    private LinearLayout llMapRefresh, llMapRoute, llMapAddMember, llMapOut;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private TextView tvMapOtherName, tvDistanceFromMe, tvRecentUpdate;
    private LinearLayout llRequestDirection;
    private MemberLocationsAdapter memberLocationsAdapter;
    private MapContract.Presenter presenter;

    private Long clubId;
    private GoogleMap mGoogleMap;
    private SimpleLocation simpleLocation;
    private boolean isPossibleRefreshMap = true;
    private boolean isDrawPolylines = true;
    private List<LatLng> myRoutes = new ArrayList<>();

    private int count = 0;

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
        llMapRefresh = (LinearLayout) findViewById(R.id.ll_menu_refresh);
        llMapRoute = (LinearLayout) findViewById(R.id.ll_menu_route);
        llMapAddMember = (LinearLayout) findViewById(R.id.ll_menu_add_member);
        llMapOut = (LinearLayout) findViewById(R.id.ll_menu_out);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel_layout);
        tvMapOtherName = (TextView) findViewById(R.id.tv_map_other_name);
        tvDistanceFromMe = (TextView) findViewById(R.id.tv_distance_from_me);
        tvRecentUpdate = (TextView) findViewById(R.id.tv_recent_update);
        llRequestDirection = (LinearLayout) findViewById(R.id.ll_request_direction);
        memberLocationsAdapter = new MemberLocationsAdapter(context);
        recyclerView.setAdapter(memberLocationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        presenter = new MapPresenter(this);
        presenter.setMemberLocationsAdapterView(memberLocationsAdapter);
        presenter.setMemberLocationsAdapterModel(memberLocationsAdapter);
        simpleLocation = new SimpleLocation(this, false, false, 10 * 1000, true);//30초
        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 1);

        checkValidateMapAndMember();

        //Menu
        llMapRefresh.setOnClickListener(v -> callMapRefresh());
        llMapRoute.setOnClickListener(v -> drawMyRoute());
        llMapAddMember.setOnClickListener(v -> startMapMemberAddActivity(clubId));
        llMapOut.setOnClickListener(v -> showMapOutDialog());
    }

    private void checkValidateMapAndMember() {
        presenter.callIsValidateMapAndMember(clubId, PrefUtils.readMemberEmailFrom(context));
    }

    @Override
    public void isValidate() {
        mapFragment.getMapAsync(this);
        settingLocation();
        setPanelSlideListener();
    }

    private void settingLocation() {
        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(context);
        }
    }

    private void setPanelSlideListener() {
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState.equals(SlidingUpPanelLayout.PanelState.DRAGGING)
                        && previousState.equals(SlidingUpPanelLayout.PanelState.EXPANDED)) {
                    isPossibleRefreshMap = true;
                }
            }
        });
    }

    @Override
    public void isUnValidate(String message) {
        ToastUtils.showToast(context, message);
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.setMaxZoomPreference(20);
        mGoogleMap.setMinZoomPreference(10);

        simpleLocation.setListener(() -> llMapRefresh.performClick());
    }

    private void callMapRefresh() {
        ToastUtils.showToast(context, String.valueOf(++count));

        presenter.callMapRefresh(clubId,
                PrefUtils.readMemberEmailFrom(context),
                new LatLng(simpleLocation.getLatitude(), simpleLocation.getLongitude()),
                PrefUtils.readMemberNameFrom(context),
                DateUtils.convertDateTimeFormatNow()
        );
    }

    @Override
    public void mapRefresh(List<MemberLocation> otherLocations, MemberLocation myLocation) {
        if (!isPossibleRefreshMap) {
            return;
        }
        mGoogleMap.clear();
        mGoogleMap.addMarker(markLocation(myLocation));
        otherLocations.forEach(m -> mGoogleMap.addMarker(markLocation(m)));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation.getLocationInfo().getLatLng(), 16));
        myRoutes.add(myLocation.getLocationInfo().getLatLng());
    }

    private MarkerOptions markLocation(MemberLocation memberLocation) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(memberLocation.getLocationInfo().getLatLng());
        markerOptions.title(memberLocation.getLocationInfo().getName());
        markerOptions.icon(decideBitmapDescriptor(memberLocation.getEmail()));

        return markerOptions;
    }

    private BitmapDescriptor decideBitmapDescriptor(String email) {
        return email.equals(PrefUtils.readMemberEmailFrom(context)) ?
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED) ://자기 자신
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);//다른 멤버들
    }

    private void drawMyRoute() {
        if (myRoutes.size() < 2) {
            ToastUtils.showToast(context, "누적된 경로가 없습니다.");
            return;
        }

        if (isDrawPolylines) {
            ToastUtils.showToast(context, "Draw My Route");
            drawMyRouteDetail();
        } else {
            ToastUtils.showToast(context, "Remove My Route");
            isPossibleRefreshMap = true;
            isDrawPolylines = true;
            llMapRefresh.performClick();
        }
    }

    private void drawMyRouteDetail() {
        isPossibleRefreshMap = false;
        isDrawPolylines = false;
        mGoogleMap.clear();

        CameraUpdate center = CameraUpdateFactory.newLatLng(myRoutes.get(0));
        mGoogleMap.moveCamera(center);

        addPolylineOptionsToMap();
        IconGenerator iconGenerator = MapHelper.createIconGenerator(context);
        for (int i = 0; i < myRoutes.size(); i++) {
            if (i == 0 || i == myRoutes.size() - 1) {
                String title = (i == 0) ? "start" : "end";
                addTextMarkerToMap(iconGenerator, title, myRoutes.get(i));
                continue;
            }
            addTextMarkerToMap(iconGenerator, String.valueOf(i), myRoutes.get(i));
        }
    }

    private void addPolylineOptionsToMap() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(5);
        polylineOptions.color(Color.GREEN);
        polylineOptions.addAll(myRoutes);
        mGoogleMap.addPolyline(polylineOptions);
    }

    private void addTextMarkerToMap(IconGenerator iconFactory, String title, LatLng position) {
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(title)))
                .position(position)
                .anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
        mGoogleMap.addMarker(markerOptions);
    }

    private void startMapMemberAddActivity(Long clubId) {
        Intent intent = new Intent(context, MapMemberAddActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }

    private void showMapOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("단체 지도방을 나가시겠습니까?");
        builder.setPositiveButton("예", (d, w) -> {
            presenter.callMapOut(clubId, PrefUtils.readMemberEmailFrom(context));
        });
        builder.setNegativeButton("아니오", (d, w) -> {
            ToastUtils.showToast(context, "취소 되었습니다");
        });
        builder.show();
    }

    @Override
    public void finishMapActivity() {
        finish();
    }

    @Override
    public void moveOtherLocation(MemberLocation memberLocation) {
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        tvMapOtherName.setText(memberLocation.getLocationInfo().getName());
        tvDistanceFromMe.setText(MapHelper.calculateDistance(Iterables.getLast(myRoutes), memberLocation.getLocationInfo().getLatLng()));
        tvRecentUpdate.setText(memberLocation.getLocationInfo().getUpdateTime());
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(memberLocation.getLocationInfo().getLatLng(), 16));
        isPossibleRefreshMap = false;

        llRequestDirection.setOnClickListener(v -> drawDirection(memberLocation.getLocationInfo().getLatLng()));
    }

    private void drawDirection(LatLng otherLocation) {
        ToastUtils.showToast(context, "Direction Drawing...");

        GoogleDirectionConfiguration.getInstance().setLogEnabled(true);
        GoogleDirection.withServerKey(getString(R.string.google_direction_server_api_key))
                .from(Iterables.getLast(myRoutes))//내 위치
                .to(otherLocation)//상대 위치
                .transportMode(TransportMode.TRANSIT)
                .execute(this);
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        ToastUtils.showToast(context, "Success : " + direction.getStatus());
        isPossibleRefreshMap = false;
        isDrawPolylines = false;

        if (direction.isOK()) {
            Route route = direction.getRouteList().get(0);
            ArrayList<LatLng> directionPositions = route.getLegList().get(0).getDirectionPoint();
            mGoogleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositions, 5, Color.GREEN));
            setCameraWithCoordinationBounds(route);
        } else {
            ToastUtils.showToast(context, direction.getStatus());
        }
    }

    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Snackbar.make(llMapRoute, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
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
