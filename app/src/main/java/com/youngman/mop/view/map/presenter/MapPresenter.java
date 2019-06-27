package com.youngman.mop.view.map.presenter;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.youngman.mop.data.Member;
import com.youngman.mop.lib.realtimedb.MapFirebaseService;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.listener.OnMemberItemClickListener;
import com.youngman.mop.util.EmptyCheckUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapterContract;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-07.
 */

public class MapPresenter implements MapContract.Presenter, OnMemberItemClickListener {

    private MapContract.View mapView;
    private MapFirebaseService mapFirebaseService;

    private MembersAdapterContract.View adapterView;
    private MembersAdapterContract.Model adapterModel;


    public MapPresenter(@NonNull MapContract.View mapView) {

        this.mapView = mapView;
        mapFirebaseService = new MapFirebaseService();
    }

    //TODO Member 목록과 위치 따로 가져오자

    @Override
    public void callMapRefresh(Long clubId, String email, LatLng latLng) {
        mapFirebaseService.callMapRefresh(clubId, email, latLng, new MapFirebaseService.ApiListener() {
            @Override
            public void onSuccess(List<MemberLocation> memberLocations) {
                mapView.mapRefresh(memberLocations);
            }

            @Override
            public void onFail(String message) {
                mapView.showErrorMessage("지도를 갱신하는데 실패하였습니다.");
            }
        });

//        mapRepository.callMapMembersLocationByClubId(clubId, new MapSource.ApiListener() {
//            @Override
//            public void onSuccess(ClubInfoResponse clubInfoResponse) {
////                infoView.setClubInfo(clubInfoResponse.getClub());
////                adapterModel.addItems(clubInfoResponse.getMembers());
////                adapterView.notifyAdapter();
//            }
//
//            @Override
//            public void onFail(String message) {
//                mapView.showErrorMessage(message);
//            }
//        });
    }

    @Override
    public void onStartMemberClick(int position) {
        Member member = adapterModel.getItem(position);
//        infoView.startMemberInfoActivity(member);
        //유저 자세정보
    }

    @Override
    public void setMembersAdapterView(@NonNull MembersAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnMemberItemClickListener(this);
    }

    @Override
    public void setMembersAdapterModel(@NonNull MembersAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
