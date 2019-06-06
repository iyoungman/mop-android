package com.youngman.mop.data.source.myclubs;

import android.support.annotation.NonNull;
import android.util.Log;

import com.youngman.mop.data.Club;
import com.youngman.mop.data.MyClubsResponse;
import com.youngman.mop.net.NetRetrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-06.
 */

public class MyClubsRemoteDataSource implements MyClubsSource {

    private static MyClubsRemoteDataSource INSTANCE;


    private MyClubsRemoteDataSource() {
    }

    public static MyClubsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MyClubsRemoteDataSource();
        }

        return INSTANCE;
    }

    /**
     * 마이 동호회 리스트 조회
     */
    public void callMyClubList(@NonNull String email, @NonNull final ListApiListener listener) {
        Call<List<Club>> result = NetRetrofit.getInstance().getNetRetrofitInterface().callMyClubsByMemberEmail(email);
        result.enqueue(new Callback<List<Club>>() {
            @Override
            public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MyClubsResponse myClubsResponse = new MyClubsResponse(response.body());
                    listener.onSuccess(myClubsResponse);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }
            @Override
            public void onFailure(Call<List<Club>> call, Throwable t) {
                Log.d("MyClubListModel", t.toString());
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    /**
     * 마이 동호회 삭제
     */
    public void callDeleteMyClubModel(@NonNull String email, @NonNull Long clubId,
                                      @NonNull int position, @NonNull final DeleteApiListener listener) {

        Call<Void> result = NetRetrofit.getInstance().getNetRetrofitInterface().callDeleteMyClub(makeParams(email, clubId));
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
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

    private Map<String, Object> makeParams(String email, Long clubId) {
        Map<String, Object> deleteMyClubParams = new HashMap<>();
        deleteMyClubParams.put("email", email);
        deleteMyClubParams.put("clubId", clubId);

        return deleteMyClubParams;
    }
}
