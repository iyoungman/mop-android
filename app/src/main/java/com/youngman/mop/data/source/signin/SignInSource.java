package com.youngman.mop.data.source.signin;

import android.support.annotation.NonNull;

import com.youngman.mop.data.SignIn;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface SignInSource {

    interface ApiListener {
        void onSuccess(String email);
        void onFail(String message);
    }

    void callSignIn(@NonNull SignIn signIn, @NonNull final ApiListener listener);
}
