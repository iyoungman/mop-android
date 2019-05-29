package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.youngman.mop.model.dto.MyClubDto;
import com.youngman.mop.network.NetRetrofit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubModel {

    private List<ClubModel> clubModels = new ArrayList<>();

    public void callMyClubList(@NonNull String email, @NonNull final ListApiListener listener) {
        Call<List<ClubModel>> result = NetRetrofit.getInstance().getNetRetrofitInterface().callMyClubsByMemberEmail(email);
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
                Log.d("MyClubModel", t.toString());
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    public void callDeleteMyClubModel(@NonNull String email,
                                      @NonNull Long clubId,
                                      @NonNull int position,
                                      @NonNull final DeleteApiListener listener) {

        Call<Void> result = NetRetrofit.getInstance().getNetRetrofitInterface().callDeleteMyClub(makeParams(email, clubId));
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    clubModels.remove(position);
                    listener.onSuccess();
                    return;
                }
                listener.onFail("삭제에 실패하였습니다.");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map makeParams(String email, Long clubId) {
        Map deleteMyClubParams = new HashMap();
        deleteMyClubParams.put("email", email);
        deleteMyClubParams.put("clubId", clubId);

        return deleteMyClubParams;
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
