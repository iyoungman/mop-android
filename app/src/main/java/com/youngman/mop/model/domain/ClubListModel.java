package com.youngman.mop.model.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.youngman.mop.model.dto.ClubListDto;
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

    public void callClubListByUserInfo(@NonNull String email,
                                       @NonNull Integer pageNo,
                                       @NonNull final ListApiListener listener) {

        Call<List<ClubModel>> result = NetRetrofit.getInstance().getNetRetrofitInterface().callPagingClubsByMember(makeParams(email, pageNo));
        result.enqueue(new Callback<List<ClubModel>>() {
            @Override
            public void onResponse(Call<List<ClubModel>> call, Response<List<ClubModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("CLub",String.valueOf(response.body().size()));
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

    private Map makeParams(String email, Integer pageNo) {
        Map<String, Object> pagingClubsByMemberParams = new HashMap<>();
        pagingClubsByMemberParams.put("email", email);
        pagingClubsByMemberParams.put("pageNo", pageNo);

        return pagingClubsByMemberParams;
    }

    /*public void callPagingClubsBySearch(@NonNull String searchClub, @NonNull int pageSize, @NonNull final ListApiListener listener) {
        Call<List<ClubModel>> result = NetRetrofit.getInstance().getNetRetrofitInterface().callPagingClubsBySearch(searchClub);
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
        ClubListDto c = ClubListDto.of(clubModelList);
        Log.d("CLub",String.valueOf(c.getClubDtoList().size()));
        return ClubListDto.of(clubModelList);
    }

    public interface ListApiListener {
        void onSuccess(ClubListDto clubListDto);
        void onFail(String message);
    }


}
