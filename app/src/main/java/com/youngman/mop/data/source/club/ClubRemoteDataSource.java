package com.youngman.mop.data.source.club;

import com.youngman.mop.net.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-07-25.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubRemoteDataSource {

    private static ClubRemoteDataSource INSTANCE;

    public static ClubRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClubRemoteDataSource();
        }
        return INSTANCE;
    }

    public void callIsClubChair(Long clubId, String email, ApiListener listener) {
        Call<Boolean> result = RetrofitClient.getInstance().getRetrofitApiService().callIsClubChair(makeParams(clubId, email));
        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body()) {
                    listener.onSuccess();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeParams(Long clubId, String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubId", clubId);
        params.put("email", email);
        return params;
    }

    public interface ApiListener {
        void onSuccess();
        void onFail(String message);
    }
}