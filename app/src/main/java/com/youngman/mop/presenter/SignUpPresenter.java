package com.youngman.mop.presenter;

import com.youngman.mop.contract.SignUpContract;
import com.youngman.mop.model.domain.UserModel;
import com.youngman.mop.model.dto.SignUpDto;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    SignUpContract.View signUpView;
    UserModel userModel;

    public SignUpPresenter(SignUpContract.View signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void callSignUp(SignUpDto signUpDto) {
        userModel.setUserData(signUpDto);
        if (userModel.checkData()) {
            userModel.callSignUp(new UserModel.ApiListener() {
                @Override
                public void onSuccess() {
                    signUpView.startSignInActivity();
                }

                @Override
                public void onFail(String message) {
                    signUpView.showErrorMessage(message);
                }
            });
        } else {
            signUpView.showErrorMessage("입력한 내용이 올바르지 않습니다.");
        }
    }
}
