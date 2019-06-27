package com.youngman.mop.data.source.map;

import android.support.annotation.NonNull;

import com.youngman.mop.data.ClubInfoResponse;
import com.youngman.mop.data.source.clubinfo.ClubInfoSource;

/**
 * Created by YoungMan on 2019-06-14.
 */

public interface MapSource {

    interface ApiListener {
        void onSuccess(ClubInfoResponse clubInfoResponse);
        void onFail(String message);
    }

    void callMapMembersLocationByClubId(@NonNull Long clubId,
                                        @NonNull final ApiListener listener);
}
