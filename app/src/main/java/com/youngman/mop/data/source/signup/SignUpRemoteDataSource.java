package com.youngman.mop.data.source.signup;

import com.youngman.mop.data.SignUp;
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
public class SignUpRemoteDataSource implements SignUpSource {

    private static SignUpRemoteDataSource INSTANCE;

    public static SignUpRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignUpRemoteDataSource();
        }
        return INSTANCE;
    }

    public void callSignUp(SignUp signUp, ApiListener listener) {
        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSingUp(signUp);
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("저장에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
