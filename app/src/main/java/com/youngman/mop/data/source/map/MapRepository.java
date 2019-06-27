package com.youngman.mop.data.source.map;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-06-14.
 */

public class MapRepository implements MapSource {

    private static MapRepository mapRepository;
    private MapFirebaseDataSource mapFirebaseDataSource;


    public static MapRepository getInstance() {
        if(mapRepository == null) {
            mapRepository = new MapRepository();
        }

        return mapRepository;
    }

    private MapRepository() {
        mapFirebaseDataSource = new MapFirebaseDataSource();
    }

    @Override
    public void callMapMembersLocationByClubId(@NonNull Long clubId, @NonNull ApiListener listener) {
        mapFirebaseDataSource.callMapMembersLocationByClubId(clubId, listener);
    }
}
