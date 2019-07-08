package com.youngman.mop.data.source.signin;

import com.youngman.mop.data.SignIn;
import com.youngman.mop.data.SignInResponse;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface SignInSource {

    interface ApiListener {
        void onSuccess(SignInResponse signInResponse);
        void onFail(String message);
    }

    void callSignIn(SignIn signIn, ApiListener listener);
}
