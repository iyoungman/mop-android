package com.youngman.mop.data.source.signup;

import com.youngman.mop.data.SignUp;

/**
 * Created by YoungMan on 2019-06-06.
 */

public interface SignUpSource {

    interface ApiListener {
        void onSuccess();
        void onFail(String message);
    }

    void callSignUp(SignUp signUp, ApiListener listener);
}
