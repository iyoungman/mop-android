package com.youngman.mop.data.source.clubinfo;

import java.io.File;

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
    public void callClubInfoByClubId(Long clubId, InfoApiListener listener) {
        clubInfoRemoteDataSource.callClubInfoByClubId(clubId, listener);
    }

    @Override
    public void callUploadClubImage(Long clubId, File imageFile, UploadApiListener listener) {
        clubInfoRemoteDataSource.callUploadClubImage(clubId, imageFile, listener);
    }
}
