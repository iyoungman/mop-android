package com.youngman.mop.data.source.clubinfo;

import com.youngman.mop.data.ClubInfoResponse;

import java.io.File;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface ClubInfoSource {

    interface InfoApiListener {
        void onSuccess(ClubInfoResponse clubInfoResponse);
        void onFail(String message);
    }

    interface UploadApiListener {
        void onSuccess(String imageUri);
        void onFail(String message);
    }

    void callClubInfoByClubId(Long clubId, InfoApiListener listener);
    void callUploadClubImage(Long clubId, File imageFile, UploadApiListener listener);
}
