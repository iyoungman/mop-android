package com.youngman.mop.data.source.clubs;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class ClubsRepository implements ClubsSource {

    private ClubsRemoteDataSource clubsRemoteDataSource;

    private static ClubsRepository clubsRepository;

    public static ClubsRepository getInstance() {
        if (clubsRepository == null) {
            clubsRepository = new ClubsRepository();
        }

        return clubsRepository;
    }

    private ClubsRepository() {
        clubsRemoteDataSource = ClubsRemoteDataSource.getInstance();
    }

    @Override
    public void callClubListByUserInfo(@NonNull String email, @NonNull Integer pageNo,
                                       @NonNull ListApiListener listener) {

        clubsRemoteDataSource.callClubListByUserInfo(email, pageNo, listener);
    }
}
