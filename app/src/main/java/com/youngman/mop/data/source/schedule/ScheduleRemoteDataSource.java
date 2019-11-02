package com.youngman.mop.data.source.schedule;

import com.youngman.mop.data.Schedule;
import com.youngman.mop.net.api.RetrofitApiClient;
import com.youngman.mop.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-07-06.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRemoteDataSource implements ScheduleSource {

    private static ScheduleRemoteDataSource INSTANCE;


    public static ScheduleRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScheduleRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void callSchedules(Long clubId, String email, String date, ListApiListener listener) {
        Call<Map<String, Schedule>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSchedules(makeScheduleParams(clubId, email, date));
        result.enqueue(new Callback<Map<String, Schedule>>() {
            @Override
            public void onResponse(Call<Map<String, Schedule>> call, Response<Map<String, Schedule>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, Schedule> scheduleMap = response.body();
                    listener.onSuccess(scheduleMap);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }
            @Override
            public void onFailure(Call<Map<String, Schedule>> call, Throwable t) {
                LogUtils.logInfo(t.getMessage());
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeScheduleParams(Long clubId, String email, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubId", clubId);
        params.put("email", email);
        params.put("date", date);

        return params;
    }

    @Override
    public void callCreateParticipant(Long scheduleId, String email,
                                      String name, ParticipantApiListener listener) {

        Call<Integer> result = RetrofitApiClient.getInstance().getRetrofitApiService().callChangeParticipant(makeParticipantParams(scheduleId, email, name));
        result.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    int participantCount = response.body();
                    listener.onSuccess(participantCount);
                    return;
                }
                listener.onFail("작업에 실패하였습니다");
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeParticipantParams(Long scheduleId, String email, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("scheduleId", scheduleId);
        params.put("email", email);
        params.put("name", name);

        return params;
    }

    @Override
    public void callParticipantCount(Long scheduleId, ParticipantApiListener listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("scheduleId", scheduleId);

        Call<Integer> result = RetrofitApiClient.getInstance().getRetrofitApiService().callParticipantCount(params);
        result.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    int participantCount = response.body();
                    listener.onSuccess(participantCount);
                    return;
                }
                listener.onFail("작업에 실패하였습니다");
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
