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


    @Builder
    public MemberLocation(String email, LatLng latLng) {
        this.email = email;
        this.latLng = latLng;
    }

}
