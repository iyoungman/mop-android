package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.SignUpDto;
import com.youngman.mop.network.NetRetrofit;

import java.util.function.Predicate;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class UserModel {

    private String id;
    private String pw;
    private String name;
    private String mobile;
    private String hobby;

    public void setUserData(@NonNull SignUpDto signUpDto) {
        this.id = signUpDto.getId();
        this.pw = signUpDto.getPw();
        this.name = signUpDto.getName();
        this.mobile = signUpDto.getMobile();
        this.hobby = signUpDto.getHobby();
    }

    public boolean checkData() {
        long count =  Stream.of(id, pw, name, mobile, hobby)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 5;
        return isAllNonNull.test(count);
    }

    public void callSignUp(@NonNull final ApiListener listener) {
        Call<Void> result = NetRetrofit.getInstance().getNetRetrofitInterface().singup(this);
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });

    }

    public interface ApiListener {
        void onSuccess();

        void onFail(String message);
    }
}
