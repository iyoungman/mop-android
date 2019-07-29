package com.youngman.mop.lib.realtimedb;

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
    private LocationInfo locationInfo;

    @Builder
    public MemberLocation(String email, LocationInfo locationInfo) {
        this.email = email;
        this.locationInfo = locationInfo;
    }
}
