package com.youngman.mop.data.source.signin;

import android.support.annotation.NonNull;
import android.util.Log;

import com.youngman.mop.data.SignIn;
import com.youngman.mop.net.NetRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class SignInRemoteDataSource implements SignInSource {

    private static SignInRemoteDataSource INSTANCE;


    private SignInRemoteDataSource() {
    }

    public static SignInRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignInRemoteDataSource();
        }

        return INSTANCE;
    }

    public void callSignIn(@NonNull SignIn signIn, @NonNull final ApiListener listener) {

        Call<Boolean> result = NetRetrofit.getInstance().getNetRetrofitInterface().callSingIn(signIn);
        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(signIn.getEmail());
                    return;
                }
                listener.onFail("로그인에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("SignInModel  " , t.toString());
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }


}