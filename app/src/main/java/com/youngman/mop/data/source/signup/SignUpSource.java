package com.youngman.mop.data.source.signup;

import android.support.annotation.NonNull;

import com.youngman.mop.data.SignUp;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface SignUpSource {

    interface ApiListener {
        void onSuccess();
        void onFail(String message);
    }

    void callSignUp(@NonNull SignUp signUp, @NonNull final ApiListener listener);
}
