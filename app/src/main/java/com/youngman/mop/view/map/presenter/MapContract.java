package com.youngman.mop.view.map.presenter;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapterContract;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-07.
 */

public interface MapContract {

    //방장 - 유저목록(단톡방) 생성, 단톡방 삭제, (멤버 추가)
    interface View {
        void mapRefresh(List<MemberLocation> memberLocations);
        void showErrorMessage(@NonNull String message);
    }

    interface Presenter {
        void callMapRefresh(Long clubId, String email, LatLng latLng);
        void setMembersAdapterView(MembersAdapterContract.View adapterView);
        void setMembersAdapterModel(MembersAdapterContract.Model adapterModel);
    }
}
