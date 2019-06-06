package com.youngman.mop.data.source.myclubs;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class MyClubsRepository implements MyClubsSource {

    private MyClubsRemoteDataSource myClubsRemoteDataSource;

    private static MyClubsRepository myClubListRepository;

    public static MyClubsRepository getInstance() {
        if(myClubListRepository == null) {
            myClubListRepository = new MyClubsRepository();
        }

        return myClubListRepository;
    }

    private MyClubsRepository() {
        myClubsRemoteDataSource = MyClubsRemoteDataSource.getInstance();
    }

    @Override
    public void callMyClubList(@NonNull String email, @NonNull ListApiListener listener) {
        myClubsRemoteDataSource.callMyClubList(email, listener);
    }

    @Override
    public void callDeleteMyClubModel(@NonNull String email, @NonNull Long clubId,
                                      @NonNull int position, @NonNull DeleteApiListener listener) {

        myClubsRemoteDataSource.callDeleteMyClubModel(email, clubId, position, listener);
    }

}
