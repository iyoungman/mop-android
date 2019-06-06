package com.youngman.mop.data.source.clubs;

import android.support.annotation.NonNull;

import com.youngman.mop.data.ClubsResponse;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface ClubsSource {

    interface ListApiListener {
        void onSuccess(ClubsResponse clubsResponse);

        void onFail(String message);
    }

    void callClubListByUserInfo(@NonNull String email, @NonNull Integer pageNo,
                                @NonNull final ListApiListener listener);

}
