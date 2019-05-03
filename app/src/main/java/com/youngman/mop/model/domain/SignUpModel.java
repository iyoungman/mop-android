package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.MyClubDto;
import com.youngman.mop.model.dto.SignUpDto;
import com.youngman.mop.network.NetRetrofit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class SignUpModel {

    private String id;
    private String pw;
    private String name;
    private String mobile;
    private String hobby;

    public void setSignUpData(@NonNull SignUpDto signUpDto) {
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

        Map<String, String> signUpParams = new HashMap<>();
        signUpParams.put("id", id);
        signUpParams.put("pw", pw);
        signUpParams.put("name", name);
        signUpParams.put("mobile", mobile);
        signUpParams.put("hobby", hobby);

        Call<Boolean> result = NetRetrofit.getInstance().getNetRetrofitInterface().callSingUp(signUpParams);
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

    public interface ApiListener {
        void onSuccess();
        void onFail(String message);
    }
}
