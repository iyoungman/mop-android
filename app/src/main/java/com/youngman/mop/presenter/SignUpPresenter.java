package com.youngman.mop.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.contract.SignUpContract;
import com.youngman.mop.model.domain.SignUpModel;
import com.youngman.mop.model.dto.SignUpDto;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    SignUpContract.View signUpView;
    SignUpModel signUpModel;

    public SignUpPresenter(@NonNull SignUpContract.View signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void callSignUp(@NonNull SignUpDto signUpDto) {
        signUpModel.setUserData(signUpDto);
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
