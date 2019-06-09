package com.youngman.mop.data.source.clubinfo;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class ClubInfoRepository implements ClubInfoSource{

    private static ClubInfoRepository clubInfoRepository;
    private ClubInfoRemoteDataSource clubInfoRemoteDataSource;


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
