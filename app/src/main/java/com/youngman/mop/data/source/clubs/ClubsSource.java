package com.youngman.mop.data.source.clubs;

import com.youngman.mop.data.ClubPagingResponse;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface ClubsSource {

    interface ListApiListener {
        void onSuccess(ClubPagingResponse clubPagingResponse);
        void onFail(String message);
    }

    void callClubsByUserInfo(String email,
                             int pageNo,
                             ListApiListener listener);

}
