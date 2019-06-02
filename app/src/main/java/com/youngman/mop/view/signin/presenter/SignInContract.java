package com.youngman.mop.view.signin.presenter;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-04-29.
 */

public interface SignInContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startMyClubActivity(@NonNull String email);
    }

    interface Presenter {
        void callSignIn(@NonNull String email, @NonNull String pw);
    }
}
