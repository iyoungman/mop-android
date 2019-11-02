package com.youngman.mop.data.source.clubstatistics;

import com.youngman.mop.data.BoardCreateRequest;
import com.youngman.mop.data.ClubSignCountResponse;
import com.youngman.mop.data.source.boardcreate.BoardCreateRemoteDataSource;
import com.youngman.mop.net.api.RetrofitApiClient;
import com.youngman.mop.net.batch.RetrofitBatchApiClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-11-02.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubStatisticsRemoteDataSource {

    private static ClubStatisticsRemoteDataSource INSTANCE;

    public static ClubStatisticsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClubStatisticsRemoteDataSource();
        }
        return INSTANCE;
    }

    public void callSignCount(Long clubId, SignCountApiListener listener) {
        Call<ClubSignCountResponse> result = RetrofitBatchApiClient.getInstance().getRetrofitApiService().callSignCount(clubId);
        result.enqueue(new Callback<ClubSignCountResponse>() {
            @Override
            public void onResponse(Call<ClubSignCountResponse> call, Response<ClubSignCountResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFail("통계 조회에 실패했습니다");
            }

            @Override
            public void onFailure(Call<ClubSignCountResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    public interface SignCountApiListener {
        void onSuccess(ClubSignCountResponse clubSignCountResponse);
        void onFail(String message);
    }
}
