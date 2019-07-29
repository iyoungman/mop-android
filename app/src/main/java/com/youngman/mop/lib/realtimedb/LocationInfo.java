package com.youngman.mop.lib.realtimedb;

import com.google.android.gms.maps.model.LatLng;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-07-29.
 */

@Getter
@NoArgsConstructor
public class LocationInfo {

    private LatLng latLng = new LatLng(0, 0);
    private String name;
    private String updateTime;

    @Builder
    public LocationInfo(LatLng latLng, String name, String updateTime) {
        this.latLng = latLng;
        this.name = name;
        this.updateTime = updateTime;
    }

    public static LocationInfo of(LatLng latLng, String name, String updateTime) {
        return LocationInfo.builder()
                .latLng(latLng)
                .name(name)
                .updateTime(updateTime)
                .build();
    }

    public static LocationInfo of(double latitude, double longitude, String name, String updateTime) {
        return LocationInfo.builder()
                .latLng(new LatLng(latitude, longitude))
                .name(name)
                .updateTime(updateTime)
                .build();
    }

    public boolean isWrongLocation() {
        long count = Stream.of(latLng.latitude, latLng.longitude)
                .filter(data -> data.equals(0d))
                .count();

        Predicate<Long> isAllZero = cnt -> cnt == 2;
        return isAllZero.test(count);
    }

}
