package com.youngman.mop.lib.tamp;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by YoungMan on 2019-11-15.
 */

public interface TMapDirectionServiceCallback {
    void onSuccess(ArrayList<LatLng> directionPoints);
    void onFailure(String message);
}
