package com.youngman.mop.data.source.signup;

import android.support.annotation.NonNull;

import com.youngman.mop.data.SignUp;
import com.youngman.mop.net.NetRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class SignUpRemoteDataSource implements SignUpSource {

    private static SignUpRemoteDataSource INSTANCE;


    private SignUpRemoteDataSource() {
    }

    public static SignUpRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignUpRemoteDataSource();
        }

        return INSTANCE;
    }

    public void callSignUp(@NonNull SignUp signUp, @NonNull final ApiListener listener) {

        Call<Boolean> result = NetRetrofit.getInstance().getNetRetrofitInterface().callSingUp(signUp);
        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("저장에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
