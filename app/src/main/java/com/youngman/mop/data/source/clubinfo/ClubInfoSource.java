package com.youngman.mop.data.source.clubinfo;

import com.youngman.mop.data.ClubInfoResponse;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface ClubInfoSource {

    interface ApiListener {
        void onSuccess(ClubInfoResponse clubInfoResponse);
        void onFail(String message);
    }

    void callClubInfoByClubId(Long clubId, ApiListener listener);

}
