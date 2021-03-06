package com.youngman.mop.data.source.signin;

import com.youngman.mop.data.SignIn;
import com.youngman.mop.data.SignInResponse;
import com.youngman.mop.net.api.RetrofitApiClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-06.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInRemoteDataSource implements SignInSource {

    private static SignInRemoteDataSource INSTANCE;


    public static SignInRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignInRemoteDataSource();
        }

        return INSTANCE;
    }

    public void callSignIn(SignIn signIn, SignInApiListener listener) {
        Call<SignInResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSingIn(signIn);
        result.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFail("로그인에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
