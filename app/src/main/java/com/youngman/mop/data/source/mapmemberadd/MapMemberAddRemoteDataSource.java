package com.youngman.mop.data.source.mapmemberadd;

import com.youngman.mop.data.Participant;
import com.youngman.mop.net.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-07-26.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapMemberAddRemoteDataSource {

    private static MapMemberAddRemoteDataSource INSTANCE;

    public static MapMemberAddRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapMemberAddRemoteDataSource();
        }
        return INSTANCE;
    }

    public void callParticipants(Long scheduleId, Long clubId, ParticipantsApiListener listener) {
        Call<List<Participant>> result = RetrofitClient.getInstance().getRetrofitApiService().callParticipants(makeParams(scheduleId, clubId));
        result.enqueue(new Callback<List<Participant>>() {
            @Override
            public void onResponse(Call<List<Participant>> call, Response<List<Participant>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFail("리스트를 받는데 실패하였습니다");
            }
            @Override
            public void onFailure(Call<List<Participant>> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeParams(Long scheduleId, Long clubId) {
        Map<String, Object> params = new HashMap<>();
        params.put("scheduleId", scheduleId);
        params.put("clubId", clubId);

        return params;
    }

    public interface ParticipantsApiListener {
        void onSuccess(List<Participant> participants);
        void onFail(String message);
    }
}
