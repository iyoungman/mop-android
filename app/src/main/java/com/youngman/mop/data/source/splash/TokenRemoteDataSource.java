package com.youngman.mop.data.source.splash;

import com.youngman.mop.net.RetrofitClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-07-18.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenRemoteDataSource {

    private static TokenRemoteDataSource INSTANCE;

    public static TokenRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TokenRemoteDataSource();
        }

        return INSTANCE;
    }


    public void callIsValidToken(String token, TokenApiListener listener) {
        Call<Boolean> result = RetrofitClient.getInstance().getRetrofitApiService().callIsValidToken(token);
        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("올바르지 않은 토큰입니다.");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    public interface TokenApiListener {
        void onSuccess();
        void onFail(String message);
    }
}
