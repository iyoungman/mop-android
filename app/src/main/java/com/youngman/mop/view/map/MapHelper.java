package com.youngman.mop.view.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

/**
 * Created by YoungMan on 2019-07-28.
 */

public class MapHelper {

    public static String calculateDistance(LatLng start, LatLng end) {
        double distance = SphericalUtil.computeDistanceBetween(start, end);
        return Math.round(distance) + "m";
    }
}
