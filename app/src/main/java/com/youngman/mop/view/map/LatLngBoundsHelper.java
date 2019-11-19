package com.youngman.mop.view.map;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by YoungMan on 2019-11-18.
 */

public class LatLngBoundsHelper {

    private static final double EARTH_RADIUS = 6366198;

    public static LatLng move(LatLng startLL, double toNorth, double toEast) {
        double lonDiff = meterToLongitude(toEast, startLL.latitude);
        double latDiff = meterToLatitude(toNorth);
        return new LatLng(startLL.latitude + latDiff, startLL.longitude + lonDiff);
    }

    private static double meterToLongitude(double meterToEast, double latitude) {
        double latArc = Math.toRadians(latitude);
        double radius = Math.cos(latArc) * EARTH_RADIUS;
        double rad = meterToEast / radius;
        return Math.toDegrees(rad);
    }


    private static double meterToLatitude(double meterToNorth) {
        double rad = meterToNorth / EARTH_RADIUS;
        return Math.toDegrees(rad);
    }
}
