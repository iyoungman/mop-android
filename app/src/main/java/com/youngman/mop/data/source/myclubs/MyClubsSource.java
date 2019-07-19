package com.youngman.mop.data.source.myclubs;

import com.youngman.mop.data.MyClubsResponse;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface MyClubsSource {

    interface ListApiListener {
        void onSuccess(MyClubsResponse myClubsResponse);
        void onFail(String message);
    }

    interface DeleteApiListener {
        void onSuccess();
        void onFail(String message);
    }

    void callMyClubs(String email, ListApiListener listener);

    void callDeleteMyClubModel(String email,
                               Long clubId,
                               int position,
                               DeleteApiListener listener);

}
