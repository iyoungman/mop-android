package com.youngman.mop.view.signin.presenter;

/**
 * Created by YoungMan on 2019-04-29.
 */

public interface SignInContract {

    interface View {
        void showErrorMessage(String message);
        void startMyClubActivity(String email);
    }

    interface Presenter {
        void callSignIn(String email, String pw);
    }
}
