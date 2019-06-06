package com.youngman.mop.data.source.myclubs;

import android.support.annotation.NonNull;

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

    void callMyClubList(@NonNull String email, @NonNull final ListApiListener listener);

    void callDeleteMyClubModel(@NonNull String email, @NonNull Long clubId,
                               @NonNull int position, @NonNull final DeleteApiListener listener);


}
