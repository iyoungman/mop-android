package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.MyClubDto;
import com.youngman.mop.network.NetRetrofit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-04-29.
 */

public class SignInModel {

    private String id;
    private String pw;

    public void setSignInData(@NonNull String id, @NonNull String pw) {
        this.id = id;
        this.pw = pw;
    }

    public boolean checkData() {
        long count =  Stream.of(id, pw)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 2;
        return isAllNonNull.test(count);
    }

    public void callSignIn(@NonNull final ApiListener listener) {

        Map<String, String> signInParams = new HashMap<>();
        signInParams.put("id", id);
        signInParams.put("pw", pw);

        Call<Boolean> result = NetRetrofit.getInstance().getNetRetrofitInterface().callSingIn(signInParams);
        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(id);
                    return;
                }
                listener.onFail("로그인에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    public interface ApiListener {
        void onSuccess(String userId);
        void onFail(String message);
    }
}
