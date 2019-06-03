package com.youngman.mop.view.map.presenter;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by YoungMan on 2019-06-03.
 */

public interface MapContract {

    interface View {
//        void showErrorMessage(@NonNull String message);
    }

    interface Presenter {
        void setDefaultLocationOnMap(@NonNull GoogleMap googleMap);
    }
}
