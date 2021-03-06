package com.youngman.mop.view.map;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.common.collect.Iterables;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.ui.IconGenerator;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.youngman.mop.R;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.lib.tamp.TMapDirectionService;
import com.youngman.mop.lib.tamp.TMapDirectionServiceCallback;
import com.youngman.mop.util.DateUtils;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.map.adapter.MemberLocationsAdapter;
import com.youngman.mop.view.map.presenter.MapContract;
import com.youngman.mop.view.map.presenter.MapPresenter;

import java.util.ArrayList;
import java.util.List;

import im.delight.android.location.SimpleLocation;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapContract.View {

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
        simpleLocation = new SimpleLocation(this, true, false, 3 * 1000, false);//1000이 1초
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showToast(context, "Return");
            return;
        }

        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.setMaxZoomPreference(20);
        mGoogleMap.setMinZoomPreference(10);

        simpleLocation.setListener(this::callMapRefresh);
    }

    private void callMapRefresh() {
        presenter.callMapRefresh(clubId, PrefUtils.readMemberEmailFrom(context),
                new LatLng(simpleLocation.getLatitude(), simpleLocation.getLongitude()),
                PrefUtils.readMemberNameFrom(context), DateUtils.convertDateTimeFormatNow()
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

        CameraUpdate center = CameraUpdateFactory.newLatLngZoom(myLocation.getLocationInfo().getLatLng(), 17);
        mGoogleMap.moveCamera(center);
        myRoutes.add(myLocation.getLocationInfo().getLatLng());
    }

    private MarkerOptions markLocation(MemberLocation memberLocation) {
        String imageName = "img_map_red";
        if (memberLocation.getEmail().equals(PrefUtils.readMemberEmailFrom(context))) {
            imageName = "img_map_blue";
        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(memberLocation.getLocationInfo().getLatLng());
        markerOptions.title(memberLocation.getLocationInfo().getName());
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(imageName, 55, 55)));
        return markerOptions;
    }

    private Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
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
                String title = (i == 0) ? "START" : "END";
                addTextMarkerToMap(iconGenerator, title, myRoutes.get(i));
            }
        }
    }

    private void addPolylineOptionsToMap() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(9);
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
        presenter.callEmergencyToOthers();
//        Intent intent = new Intent(context, MapMemberAddActivity.class);
//        intent.putExtra("EXTRA_CLUB_ID", clubId);
//        startActivity(intent);
    }

    @Override
    public void showSuccessMessage() {
        ToastUtils.showToast(context, "긴급 메세지 전송");
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
        TMapDirectionService tMapDirectionService = new TMapDirectionService(context, new TMapDirectionServiceCallback() {
            @Override
            public void onSuccess(ArrayList<LatLng> directionPoints) {
                if (directionPoints == null || directionPoints.size() == 0) {
                    ToastUtils.showToast(context, "해당 경로가 없습니다");
                } else {
                    mGoogleMap.addPolyline(DirectionConverter.createPolyline(context, directionPoints, 5, Color.GREEN));
                    setCameraWithCoordinationBounds(directionPoints.get(0), Iterables.getLast(directionPoints));
                }
            }
            @Override
            public void onFailure(String message) {
                ToastUtils.showToast(context, message);
            }
        });
        tMapDirectionService.execute(Iterables.getLast(myRoutes), otherLocation);
    }

    private void setCameraWithCoordinationBounds(LatLng start, LatLng end) {
        LatLngBounds.Builder bounds = new LatLngBounds.Builder();
        bounds.include(start);
        bounds.include(end);

        LatLngBounds tmpBounds = bounds.build();
        LatLng center = tmpBounds.getCenter();
        LatLng northEast = LatLngBoundsHelper.move(center, 709, 709);
        LatLng southWest = LatLngBoundsHelper.move(center, -709, -709);
        bounds.include(southWest);
        bounds.include(northEast);

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 10));
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
