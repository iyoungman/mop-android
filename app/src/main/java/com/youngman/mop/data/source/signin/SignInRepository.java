package com.youngman.mop.data.source.signin;

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
    public void callSignIn(SignIn signIn, SignInApiListener listener) {
        signInRemoteDataSource.callSignIn(signIn, listener);
    }

    @Override
    public void callIsValidToken(String token, TokenApiListener listener) {
        signInRemoteDataSource.callIsValidToken(token, listener);
    }
}
