package com.youngman.mop.data.source.signin;

import android.support.annotation.NonNull;

import com.youngman.mop.data.SignIn;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class SignInRepository implements SignInSource {

    private static SignInRepository signInRepository;
    private SignInRemoteDataSource signInRemoteDataSource;


    public static SignInRepository getInstance() {
        if(signInRepository == null) {
            signInRepository = new SignInRepository();
        }

        return signInRepository;
    }

    private SignInRepository() {
        signInRemoteDataSource = SignInRemoteDataSource.getInstance();
    }

    @Override
    public void callSignIn(@NonNull SignIn signIn, @NonNull ApiListener listener) {
        signInRemoteDataSource.callSignIn(signIn, listener);
    }
}
