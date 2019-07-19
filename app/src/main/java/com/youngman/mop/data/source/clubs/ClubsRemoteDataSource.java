package com.youngman.mop.data.source.clubs;

import com.youngman.mop.data.ClubPagingResponse;
import com.youngman.mop.net.RetrofitClient;

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
    public void callClubsByUserInfo(String email,
                                    int pageNo,
                                    ListApiListener listener) {

        Call<ClubPagingResponse> result = RetrofitClient.getInstance().getRetrofitApiService().callPagingClubsByMember(makeParams(email, pageNo));
        result.enqueue(new Callback<ClubPagingResponse>() {
            @Override
            public void onResponse(Call<ClubPagingResponse> call, Response<ClubPagingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClubPagingResponse clubPagingResponse = response.body();
                    listener.onSuccess(clubPagingResponse);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<ClubPagingResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeParams(String email, int pageNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("pageNo", pageNo);
        return params;
    }

    @Override
    public void callCreateMyClub(String email,
                                 Long clubId,
                                 CreateApiListener listener) {

        Call<Void> result = RetrofitClient.getInstance().getRetrofitApiService().callCreateMyClub(makeBody(email, clubId));
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("마이 동호회 생성에 실패하였습니다");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeBody(String email, Long clubId) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("clubId", clubId);
        return body;
    }
}
