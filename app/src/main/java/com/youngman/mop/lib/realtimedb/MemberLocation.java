package com.youngman.mop.lib.realtimedb;

import com.google.android.gms.maps.model.LatLng;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-06-27.
 */

@Getter
@NoArgsConstructor
public class MemberLocation {

    private String email;
    private LatLng latLng;
//    private String name;
//    private String updateTime;
//    private LocationInfo locationInfo;

//    @Builder
//    public MemberLocation(String email, LocationInfo locationInfo) {
//        this.email = email;
//        this.locationInfo = locationInfo;
//    }


        @Builder
    public MemberLocation(String email, LatLng latLng) {
        this.email = email;
        this.latLng = latLng;
//    }

//    @Getter
//    @NoArgsConstructor
//    public static class LocationInfo {
//        private LatLng latLng;
//        private String name;
//        private String updateTime;
//
//        @Builder
//        public LocationInfo(LatLng latLng, String name, String updateTime) {
//            this.latLng = latLng;
//            this.name = name;
//            this.updateTime = updateTime;
//        }
    }
}
