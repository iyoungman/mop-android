package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.ClubListDto;
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
 * Created by YoungMan on 2019-05-03.
 */

public class ClubListModel {

    private final int PAGE_SIZE = 24;
    private List<ClubModel> clubModelList = new ArrayList<>();

    public void callClubListByUserInfo(@NonNull String userId, @NonNull Integer pageNo, @NonNull final ListApiListener listener) {

        Map<String, Object> clubListParams = new HashMap<>();
        clubListParams.put("userId", userId);
        clubListParams.put("pageSize", PAGE_SIZE);
        clubListParams.put("pageNo", pageNo);

        Call<List<ClubModel>> result = NetRetrofit.getInstance().getNetRetrofitInterface().callClubListByUserInfo(clubListParams);
        result.enqueue(new Callback<List<ClubModel>>() {
            @Override
            public void onResponse(Call<List<ClubModel>> call, Response<List<ClubModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    clubModelList = response.body();
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

    /*public void callClubListBySearch(@NonNull String searchClub, @NonNull int pageSize, @NonNull final ListApiListener listener) {
        Call<List<ClubModel>> result = NetRetrofit.getInstance().getNetRetrofitInterface().callClubListBySearch(searchClub);
        result.enqueue(new Callback<List<ClubModel>>() {
            @Override
            public void onResponse(Call<List<ClubModel>> call, Response<List<ClubModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    clubModelList = response.body();
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
    }*/

    private ClubListDto modelToDto() {
        return ClubListDto.of(clubModelList);
    }

    public interface ListApiListener {
        void onSuccess(ClubListDto clubListDto);
        void onFail(String message);
    }


}
