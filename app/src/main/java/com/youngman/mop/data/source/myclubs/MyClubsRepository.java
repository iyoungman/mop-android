package com.youngman.mop.data.source.myclubs;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class MyClubsRepository implements MyClubsSource {

    private static MyClubsRepository myClubListRepository;
    private MyClubsRemoteDataSource myClubsRemoteDataSource;


    public static MyClubsRepository getInstance() {
        if (myClubListRepository == null) {
            myClubListRepository = new MyClubsRepository();
        }

        return myClubListRepository;
    }

    private MyClubsRepository() {
        myClubsRemoteDataSource = MyClubsRemoteDataSource.getInstance();
    }

    @Override
    public void callMyClubList(String email, ListApiListener listener) {
        myClubsRemoteDataSource.callMyClubList(email, listener);
    }

    @Override
    public void callDeleteMyClubModel(String email,
                                      Long clubId,
                                      int position,
                                      DeleteApiListener listener) {

        myClubsRemoteDataSource.callDeleteMyClubModel(email, clubId, position, listener);
    }

}
