package com.youngman.mop.lib.fcm;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by YoungMan on 2019-10-25.
 */

public class FcmTokenService {

    public static String getFcmToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }
}
