package com.youngman.mop.data.source.clubinfo;

import android.support.annotation.NonNull;

import com.youngman.mop.data.ClubInfoResponse;
import com.youngman.mop.net.NetRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class ClubInfoRemoteDataSource implements ClubInfoSource {

    private static ClubInfoRemoteDataSource INSTANCE;


    private ClubInfoRemoteDataSource() {
    }

    public static ClubInfoRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClubInfoRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callClubInfoByClubId(@NonNull Long clubId,
                                     @NonNull final ApiListener listener) {

        Call<ClubInfoResponse> result = NetRetrofit.getInstance().getNetRetrofitInterface().callClubInfoById(clubId);
        result.enqueue(new Callback<ClubInfoResponse>() {
            @Override
            public void onResponse(Call<ClubInfoResponse> call, Response<ClubInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClubInfoResponse clubInfoResponse = response.body();
                    listener.onSuccess(clubInfoResponse);
                    return;
                }
                listener.onFail("동호회 정보를 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<ClubInfoResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}