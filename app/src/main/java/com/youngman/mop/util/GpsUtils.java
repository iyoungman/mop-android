package com.youngman.mop.util;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

/**
 * Created by YoungMan on 2019-06-04.
 */

public class GpsUtils {

    public static void checkGpsOn(Context context, LocationManager locationManager) {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            ToastUtils.showToast(context, "GPS를 켜주세요");

            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            context.startActivity(intent);
        }
    }
}
