package com.youngman.mop.view.map.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.youngman.mop.lib.realtimedb.MapFirebaseService;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.listener.OnBasicItemClickListener;
import com.youngman.mop.view.map.adapter.MemberLocationsAdapterContract;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-07.
 */

public class MapPresenter implements MapContract.Presenter, OnBasicItemClickListener {

    private MapContract.View mapView;
    private MapFirebaseService mapFirebaseService;
    private MemberLocationsAdapterContract.View adapterView;
    private MemberLocationsAdapterContract.Model adapterModel;
    private boolean isFirst = true;


    public MapPresenter(MapContract.View mapView) {
        this.mapView = mapView;
        mapFirebaseService = new MapFirebaseService();
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
    public void callMapRefresh(Long clubId, String email, LatLng latLng, String updateTime) {
        mapFirebaseService.callMapRefresh(clubId, email, latLng, updateTime, new MapFirebaseService.RefreshApiListener() {
            @Override
            public void onSuccess(List<MemberLocation> otherLocations, MemberLocation myLocation) {
                adapterModel.addItems(otherLocations);
                adapterView.notifyAdapter();
                mapView.mapRefresh(otherLocations, myLocation);
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
        mapView.moveOtherLocation(otherLocation);
    }

    public void setMemberLocationsAdapterView(MemberLocationsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnBasicItemClickListener(this);
    }

    public void setMemberLocationsAdapterModel(MemberLocationsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
