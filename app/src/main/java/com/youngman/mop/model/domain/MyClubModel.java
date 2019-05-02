package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.MyClubDto;
import com.youngman.mop.network.NetRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubModel {

    private List<ClubModel> clubModels = new ArrayList<>();

    public void callMyClubList(@NonNull String userId, @NonNull final ListApiListener listener) {
        Call<List<ClubModel>> result = NetRetrofit.getInstance().getNetRetrofitInterface().callClubList(userId);
        result.enqueue(new Callback<List<ClubModel>>() {
            @Override
            public void onResponse(Call<List<ClubModel>> call, Response<List<ClubModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    clubModels = response.body();
                    listener.onSuccess(modelToDto());
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }
            @Override
            public void onFailure(Call<List<ClubModel>> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    public void callDeleteMyClubModel(@NonNull String userId, @NonNull final DeleteApiListener listener) {
        Call<Boolean> result = NetRetrofit.getInstance().getNetRetrofitInterface().deleteMyClub(userId);
        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("삭제에 실패하였습니다.");
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private MyClubDto modelToDto() {
        return MyClubDto.of(clubModels);
    }

    public interface ListApiListener {
        void onSuccess(MyClubDto myClubDto);
        void onFail(String message);
    }

    public interface DeleteApiListener {
        void onSuccess();
        void onFail(String message);
    }

}
