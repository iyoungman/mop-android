package com.youngman.mop.data.source.signup;

import android.support.annotation.NonNull;

import com.youngman.mop.data.SignUp;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class SignUpRepository implements SignUpSource {

    private SignUpRemoteDataSource signUpRemoteDataSource;

    private static SignUpRepository signUpRepository;

    public static SignUpRepository getInstance() {
        if(signUpRepository == null) {
            signUpRepository = new SignUpRepository();
        }

        return signUpRepository;
    }

    private SignUpRepository() {
        signUpRemoteDataSource = signUpRemoteDataSource.getInstance();
    }

    @Override
    public void callSignUp(@NonNull SignUp signUp, @NonNull ApiListener listener) {
        signUpRemoteDataSource.callSignUp(signUp, listener);
    }
}
