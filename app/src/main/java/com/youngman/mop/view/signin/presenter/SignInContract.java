package com.youngman.mop.view.signin.presenter;

import com.youngman.mop.data.SignInResponse;

/**
 * Created by YoungMan on 2019-04-29.
 */

public interface SignInContract {

    interface View {
        void showErrorMessage(String message);
        void startMyClubActivity(SignInResponse signInResponse);
    }

    interface Presenter {
        void callSignIn(String email, String pw);
    }
}
