package com.youngman.mop.data.source.clubinfo;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class ClubInfoRepository implements ClubInfoSource{

    private ClubInfoRemoteDataSource clubInfoRemoteDataSource;

    private static ClubInfoRepository clubInfoRepository;

    public static ClubInfoRepository getInstance() {
        if(clubInfoRepository == null) {
            clubInfoRepository = new ClubInfoRepository();
        }

        return clubInfoRepository;
    }

    private ClubInfoRepository() {
        clubInfoRemoteDataSource = ClubInfoRemoteDataSource.getInstance();
    }

    @Override
    public void callClubInfoByClubId(@NonNull Long clubId, @NonNull ApiListener listener) {
        clubInfoRemoteDataSource.callClubInfoByClubId(clubId, listener);
    }
}
