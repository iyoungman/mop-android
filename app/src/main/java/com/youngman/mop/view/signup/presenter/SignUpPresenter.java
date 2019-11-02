package com.youngman.mop.view.signup.presenter;

import com.youngman.mop.data.SignUp;
import com.youngman.mop.data.source.signup.SignUpRepository;
import com.youngman.mop.data.source.signup.SignUpSource;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View signUpView;
    private final SignUpRepository signUpRepository;


    public SignUpPresenter(SignUpContract.View signUpView,
                           SignUpRepository signUpRepository) {
        this.signUpView = signUpView;
        this.signUpRepository = signUpRepository;
    }

    @Override
    public void callSignUp(SignUp signUp) {
        if (signUp.isAllNonNull()) {
            signUpRepository.callSignUp(signUp, new SignUpSource.ApiListener() {
                @Override
                public void onSuccess() {
                    signUpView.startSignInActivity();
                }

                @Override
                public void onFail(String message) {
                    signUpView.showErrorMessage(message);
                }
            });
            return;
        }
        signUpView.showErrorMessage("입력한 내용이 올바르지 않습니다.");
    }
}
