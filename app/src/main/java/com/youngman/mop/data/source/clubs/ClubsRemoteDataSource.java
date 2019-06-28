package com.youngman.mop.data.source.clubs;

import android.support.annotation.NonNull;

import com.youngman.mop.data.ClubsResponse;
import com.youngman.mop.net.NetRetrofit;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-06.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubsRemoteDataSource implements ClubsSource {

    private static ClubsRemoteDataSource INSTANCE;


    public static ClubsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClubsRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callClubListByUserInfo(String email,
                                       int pageNo,
                                       ListApiListener listener) {

        Call<ClubsResponse> result = NetRetrofit.getInstance().getNetRetrofitInterface().callPagingClubsByMember(makeParams(email, pageNo));
        result.enqueue(new Callback<ClubsResponse>() {
            @Override
            public void onResponse(Call<ClubsResponse> call, Response<ClubsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClubsResponse clubsResponse = response.body();
                    listener.onSuccess(clubsResponse);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<ClubsResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeParams(String email, Integer pageNo) {
        Map<String, Object> pagingClubsByMemberParams = new HashMap<>();
        pagingClubsByMemberParams.put("email", email);
        pagingClubsByMemberParams.put("pageNo", pageNo);

        return pagingClubsByMemberParams;
    }
}
