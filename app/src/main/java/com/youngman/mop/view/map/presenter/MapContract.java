package com.youngman.mop.view.map.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapterContract;
import com.youngman.mop.view.map.adapter.MemberLocationsAdapterContract;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-07.
 */

public interface MapContract {

    //방장 - 유저목록(단톡방) 생성, 단톡방 삭제, (멤버 추가)
    interface View {
        void mapRefresh(List<MemberLocation> memberLocations, MemberLocation myLocation);
        void moveLocation(MemberLocation memberLocation);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void callMapRefresh(Long clubId, String email, LatLng latLng);
        void setMemberLocationsAdapterView(MemberLocationsAdapterContract.View adapterView);
        void setMemberLocationsAdapterModel(MemberLocationsAdapterContract.Model adapterModel);
    }
}
