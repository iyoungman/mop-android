package com.youngman.mop.view.signup.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.SignUp;
import com.youngman.mop.data.source.signup.SignUpRepository;
import com.youngman.mop.data.source.signup.SignUpSource;
import com.youngman.mop.model.domain.SignUpModel;
import com.youngman.mop.model.dto.SignUpDto;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View signUpView;
    private final SignUpRepository signUpRepository;


    public SignUpPresenter(@NonNull SignUpContract.View signUpView,
                           @NonNull SignUpRepository signUpRepository) {

        this.signUpView = signUpView;
        this.signUpRepos