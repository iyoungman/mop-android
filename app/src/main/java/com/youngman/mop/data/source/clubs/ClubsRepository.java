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
    public void callClubsByUserInfo(String email,
                                    int pageNo,
                                    ListApiListener listener) {

        clubsRemoteDataSource.callClubsByUserInfo(email, pageNo, listener);
    }

    @Override
    public void callCreateMyClub(String email,
                                 Long clubId,
                                 CreateApiListener listener) {

        clubsRemoteDataSource.callCreateMyClub(email, clubId, listener);
    }
}
