package com.youngman.mop.data.source.clubs;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class ClubsRepository implements ClubsSource {

    private static ClubsRepository clubsRepository;
    private ClubsRemoteDataSource clubsRemoteDataSource;


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
    public void callClubListByUserInfo(String email,
                                       int pageNo,
                                       ListApiListener listener) {

        clubsRemoteDataSource.callClubListByUserInfo(email, pageNo, listener);
    }
}
