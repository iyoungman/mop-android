package com.youngman.mop.contract;

import com.youngman.mop.model.dto.SignUpDto;

/**
 * Created by YoungMan on 2019-04-28.
 */

public interface SignUpContract {

    interface View {
        void showErrorMessage(String message);
        void startSignInActivity();
    }

    interface Presenter {
        void callSignUp(SignUpDto signUpDto);
    }
}
