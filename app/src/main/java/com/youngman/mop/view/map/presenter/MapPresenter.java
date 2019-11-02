package com.youngman.mop.view.map.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.youngman.mop.data.Emergency;
import com.youngman.mop.data.source.emergency.EmergencyRemoteDataSource;
import com.youngman.mop.lib.realtimedb.LocationInfo;
import com.youngman.mop.lib.realtimedb.MapFirebaseService;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.listener.OnBasicItemClickListener;
import com.youngman.mop.view.map.adapter.MemberLocationsAdapterContract;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YoungMan on 2019-06-07.
 */

public class MapPresenter implements MapContract.Presenter, OnBasicItemClickListener {

    private MapContract.View mapView;
    private MapFirebaseService mapFirebaseService;
    private MemberLocationsAdapterContract.View adapterView;
    private MemberLocationsAdapterContract.Model adapterModel;

    private EmergencyRemoteDataSource emergencyRemoteDataSource;
    private List<MemberLocation> others = new ArrayList<>();
    private MemberLocation my;

    public MapPresenter(MapContract.View mapView) {
        this.mapView = mapView;
        mapFirebaseService = new MapFirebaseService();
        emergencyRemoteDataSource = EmergencyRemoteDataSource.getInstance();
    }

    @Override
    public void callIsValidateMapAndMember(Long clubId, String email) {
        mapFirebaseService.callIsValidateMapAndMember(clubId, email, new MapFirebaseService.ValidateApiListener() {
            @Override
            public void onSuccess() {
                mapView.isValidate();
            }

            @Override
            public void onFail(String message) {
                mapView.isUnValidate(message);
            }
        });
    }

    @Override
    public void callMapRefresh(Long clubId, String email, LatLng latLng, String name, String updateTime) {
        LocationInfo locationInfo = LocationInfo.of(latLng, name, updateTime);
        mapFirebaseService.callMapRefresh(clubId, email, locationInfo, new MapFirebaseService.RefreshApiListener() {
            @Override
            public void onSuccess(List<MemberLocation> otherLocations, MemberLocation myLocation) {
                adapterModel.addItems(otherLocations);
                adapterView.notifyAdapter();
                mapView.mapRefresh(otherLocations, myLocation);

                others = otherLocations;
                my = myLocation;
            }

            @Override
            public void onFail(String message) {
                mapView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void callEmergencyToOthers() {
        if (others.size() == 0 || my == null) {
            return;
        }
        Emergency emergency = Emergency.builder()
                .otherEmails(others.stream()
                        .map(MemberLocation::getEmail)
                        .collect(Collectors.toList())
                )
                .sender(my.getEmail())
                .build();

        emergencyRemoteDataSource.callEmergencyToOthers(emergency, new EmergencyRemoteDataSource.EmergencyApiListener() {
            @Override
            public void onSuccess() {
                mapView.showSuccessMessage();
            }

            @Override
            public void onFail(String message) {
                mapView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void callMapOut(Long clubId, String email) {
        mapFirebaseService.callMapOut(clubId, email, new MapFirebaseService.DeleteApiListener() {
            @Override
            public void onSuccess() {
                mapView.finishMapActivity();
            }

            @Override
            public void onFail(String message) {
                mapView.showErrorMessage("지도 단체방을 나가는데 실패하였습니다.");
            }
        });
    }

    @Override
    public void onStartItemClick(int position) {
        MemberLocation otherLocation = adapterModel.getItem(position);
        if (otherLocation.getLocationInfo().isWrongLocation()) {
            mapView.showErrorMessage("저장된 위치 기록이 없습니다.");
        } else {
            mapView.moveOtherLocation(otherLocation);
        }
    }

    public void setMemberLocationsAdapterView(MemberLocationsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnBasicItemClickListener(this);
    }

    public void setMemberLocationsAdapterModel(MemberLocationsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
