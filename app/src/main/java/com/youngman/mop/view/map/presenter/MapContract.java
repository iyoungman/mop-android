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

    interface View {
        void isValidate();
        void isUnValidate(String message);
        void mapRefresh(List<MemberLocation> memberLocations, MemberLocation myLocation);
        void moveOtherLocation(MemberLocation memberLocation);
        void finishMapActivity();
        void showSuccessMessage();
        void showErrorMessage(String message);
    }

    interface Presenter {
        void callIsValidateMapAndMember(Long clubId, String email);
        void callMapRefresh(Long clubId, String email, LatLng latLng, String name, String updateTime);
        void callEmergencyToOthers();
        void callMapOut(Long clubId, String email);
        void setMemberLocationsAdapterView(MemberLocationsAdapterContract.View adapterView);
        void setMemberLocationsAdapterModel(MemberLocationsAdapterContract.Model adapterModel);
    }
}
