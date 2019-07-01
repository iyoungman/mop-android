package com.youngman.mop.view.map;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.youngman.mop.R;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.util.LogUtils;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.map.adapter.MemberLocationsAdapter;
import com.youngman.mop.view.map.presenter.MapContract;
import com.youngman.mop.view.map.presenter.MapPresenter;

import java.util.ArrayList;
import java.util.List;

import im.delight.android.location.SimpleLocation;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, RoutingListener, MapContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private SupportMapFragment mapFragment;
    private LinearLayout llMapRefresh, llMapRoute, llMapAddMember, llMapOut;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private TextView tvMapOtherName;
    private MemberLocationsAdapter memberLocationsAdapter;
    private MapContract.Presenter presenter;

    private Long clubId;
    private GoogleMap mGoogleMap;
    private SimpleLocation simpleLocation;
    private boolean isPossibleAnimateCamera = true;
    private ArrayList<LatLng> myRoutes = new ArrayList<>();


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
        memberLocationsAdapter = new MemberLocationsAdapter(context);
        recyclerView.setAdapter(memberLocationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 1);
        simpleLocation = new SimpleLocation(context, false, false, 15 * 1000, true);//15초
        mapFragment.getMapAsync(this);

        presenter = new MapPresenter(this);
        presenter.setMemberLocationsAdapterView(memberLocationsAdapter);
        presenter.setMemberLocationsAdapterModel(memberLocationsAdapter);

        checkLocationSetting();
        setPanelSlideListener();

        llMapRefresh.setOnClickListener(v -> {
            presenter.callMapRefresh(clubId, SignUtils.readUserIdFromPref(context),
                    new LatLng(simpleLocation.getLatitude(), simpleLocation.getLongitude())
            );
        });

        llMapRoute.setOnClickListener(v -> {
            drawMyRoute();
        });

        llMapAddMember.setOnClickListener(v -> {

        });

        llMapOut.setOnClickListener(v -> {
            showMapOutDialog();
        });
    }

    private void checkLocationSetting() {
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
                    isPossibleAnimateCamera = true;
                }
            }
        });
    }

    private void drawMyRoute() {
        if(myRoutes.size() <= 1) {
            return;
        }

        Routing routing = new Routing.Builder()
                .travelMode(Routing.TravelMode.WALKING)
                .withListener(this)
                .waypoints(myRoutes)
                .key("AIzaSyDKMUtW3JdEoBLMcZzG8zjtqGSEq0vJUzo")
                .build();
        routing.execute();
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        ToastUtils.showToast(context, e.getMessage());
        LogUtils.logDebug(e.getMessage());
    }

    @Override
    public void onRoutingStart() {
        ToastUtils.showToast(context, "시작");
    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> routes, int i) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(myRoutes.get(0));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        mGoogleMap.moveCamera(center);

        List<Polyline> polylines = new ArrayList<>();

        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        int color = ResourcesCompat.getColor(getResources(), R.color.md_grey_600, null);

        polylines = new ArrayList<>();
        for (int s = 0; s < routes.size(); s++) {
            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(color);
            polyOptions.width(10 + s * 3);
            polyOptions.addAll(routes.get(s).getPoints());
            Polyline polyline = mGoogleMap.addPolyline(polyOptions);
            polylines.add(polyline);
        }

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(myRoutes.get(0));
        options.title("start");
        mGoogleMap.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(myRoutes.get(myRoutes.size() - 1));
        options.title("end");
        mGoogleMap.addMarker(options);
    }

    @Override
    public void onRoutingCancelled() {
        ToastUtils.showToast(context, "취소");
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
            llMapRefresh.performClick();
        });
    }

    @Override
    public void mapRefresh(List<MemberLocation> otherLocations, MemberLocation myLocation) {
        mGoogleMap.clear();
        otherLocations.forEach(m -> mGoogleMap.addMarker(markLocation(m)));
        mGoogleMap.addMarker(markLocation(myLocation));
        if (isPossibleAnimateCamera) {
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation.getLatLng(), 16));
        }

        myRoutes.add(myLocation.getLatLng());
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
    public void moveOtherLocation(MemberLocation memberLocation) {
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        tvMapOtherName.setText(memberLocation.getEmail());
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(memberLocation.getLatLng(), 16));
        isPossibleAnimateCamera = false;
    }

    private void showMapOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("지도 단체방을 나가시겠습니까?");
        builder.setPositiveButton("예", (d, w) -> {
            presenter.callMapOut(clubId, SignUtils.readUserIdFromPref(context));
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
