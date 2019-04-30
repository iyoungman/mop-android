package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;

import com.youngman.mop.network.NetRetrofit;

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

    public void setUserData(@NonNull String id, @NonNull String pw) {
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

    public void callSignIn(@NonNull final SignInModel.ApiListener listener) {
        Call<Boolean> result = NetRetrofit.getInstance().getNetRetrofitInterface().singIn(this);
        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    public interface ApiListener {
        void onSuccess();

        void onFail(String message);
    }
}
