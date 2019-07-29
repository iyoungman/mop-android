package com.youngman.mop.view.map;

import android.content.Context;
import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.ui.IconGenerator;
import com.youngman.mop.R;

/**
 * Created by YoungMan on 2019-07-28.
 */

public class MapHelper {

    public static String calculateDistance(LatLng start, LatLng end) {
        double distance = SphericalUtil.computeDistanceBetween(start, end);
        return Math.round(distance) + "m";
    }

    public static IconGenerator createIconGenerator(Context context) {
        IconGenerator iconGenerator = new IconGenerator(context);
        iconGenerator.setColor(Color.GREEN);
        iconGenerator.setTextAppearance(R.style.IconGenText);
        return iconGenerator;
    }
}
