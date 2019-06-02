package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.youngman.mop.model.dto.InfoDto;
import com.youngman.mop.net.NetRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-05-28.
 */

public class InfoModel {

    @SerializedName("clubDto")
    private ClubModel clubModel;

    @SerializedName("memberDtos")
    private List<MemberModel> memberModels = new ArrayList<>();


    public void callClubInfoByClubId(@NonNull Long clubId,
                                     @NonNull final ApiListener listener) {

        Call<InfoModel> result = NetRetrofit.getInstance().getNetRetrofitInterface().callClubInfoById(clubId);
        result.enqueue(new Callback<InfoModel>() {
            @Override
            public void onResponse(Call<InfoModel> call, Response<InfoModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    clubModel = response.body().clubModel;
                    memberModels = response.body().memberModels;
                    listener.onSuccess(modelToDto());
                    return;
                }
                listener.onFail("동호회 정보를 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<InfoModel> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private InfoDto modelToDto() {
        return InfoDto.of(clubModel, memberModels);
    }

    public interface ApiListener {
        void onSuccess(InfoDto infoDto);

        void onFail(String message);
    }
}
