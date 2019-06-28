package com.youngman.mop.view.signup.presenter;

import com.youngman.mop.data.SignUp;

/**
 * Created by YoungMan on 2019-04-28.
 */

public interface SignUpContract {

    interface View {
        void showErrorMessage(String message);
        void startSignInActivity();
    }

    interface Presenter {
        void callSignUp(SignUp signUp);
    }
}
