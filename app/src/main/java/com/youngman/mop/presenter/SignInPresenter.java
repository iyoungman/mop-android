package com.youngman.mop.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.contract.SignInContract;
import com.youngman.mop.model.domain.SignInModel;

/**
 * Created by YoungMan on 2019-04-29.
 */

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View signInView;
    private SignInModel signInModel;

    public SignInPresenter(SignInContract.View signInView) {
        this.signInView = signInView;
        this.signInModel = new SignInModel();
    }

    @Override
    public void callSignIn(@NonNull String email, @NonNull String pw) {
        signInModel.setSignInData(email, pw);
        if(signInModel.checkData()) {
            signInModel.callSignIn(new SignInModel.ApiListener() {
                @Override
                public void onSuccess(String email) {
                    signInView.startMyClubActivity(email);
                }
                @Override
                public void onFail(String message) {
                    signInView.showErrorMessage(message);
                }
            });
            return;
        }
        signInView.showErrorMessage("내용을 입력해주세요");
    }
}
