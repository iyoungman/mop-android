package com.youngman.mop.contract;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-04-29.
 */

public interface SignInContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startMyPageActivity();
    }

    interface Presenter {
        void callSignIn(@NonNull String id, @NonNull String pw);
    }
}
