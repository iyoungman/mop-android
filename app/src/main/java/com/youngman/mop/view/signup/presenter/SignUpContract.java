package com.youngman.mop.view.signup.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.SignUpDto;

/**
 * Created by YoungMan on 2019-04-28.
 */

public interface SignUpContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startSignInActivity();
    }

    interface Presenter {
        void callSignUp(@NonNull SignUpDto signUpDto);
    }
}
