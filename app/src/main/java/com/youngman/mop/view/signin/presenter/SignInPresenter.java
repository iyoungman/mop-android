package com.youngman.mop.view.signin.presenter;

import com.youngman.mop.data.SignIn;
import com.youngman.mop.data.source.signin.SignInRepository;
import com.youngman.mop.data.source.signin.SignInSource;

/**
 * Created by YoungMan on 2019-04-29.
 */

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View signInView;
    private final SignInRepository signInRepository;


    public SignInPresenter(SignInContract.View signInView,
                           SignInRepository signInRepository) {

        this.signInView = signInView;
        this.signInRepository = signInRepository;
    }

    @Override
    public void callSignIn(String email, String pw) {
        SignIn signIn = SignIn.builder()
                .email(email)
                .pw(pw)
                .build();

        if (signIn.isAllNonNull()) {
            signInRepository.callSignIn(signIn, new SignInSource.ApiListener() {

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
