package com.youngman.mop.view.signup.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.model.domain.SignUpModel;
import com.youngman.mop.model.dto.SignUpDto;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View signUpView;
    private SignUpModel signUpModel;

    public SignUpPresenter(@NonNull SignUpContract.View signUpView) {
        this.signUpView = signUpView;
        this.signUpModel = new SignUpModel();
    }

    @Override
    public void callSignUp(@NonNull SignUpDto signUpDto) {
        signUpModel.setSignUpData(signUpDto);
        if (signUpModel.checkData()) {
            signUpModel.callSignUp(new SignUpModel.ApiListener() {
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