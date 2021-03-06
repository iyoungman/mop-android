package com.youngman.mop.data.source.signup;

import com.youngman.mop.data.SignUp;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class SignUpRepository implements SignUpSource {

    private static SignUpRepository signUpRepository;
    private SignUpRemoteDataSource signUpRemoteDataSource;


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
    public void callSignUp(SignUp signUp, ApiListener listener) {
        signUpRemoteDataSource.callSignUp(signUp, listener);
    }
}
