package com.youngman.mop.view.splash.presenter;

/**
 * Created by YoungMan on 2019-07-18.
 */

public interface SplashContract {

    interface View {
        void startSignInActivityByToken();
        void startMyClubActivityByToken();
    }

    interface Presenter {
        void callIsValidToken(String token);
    }
}
