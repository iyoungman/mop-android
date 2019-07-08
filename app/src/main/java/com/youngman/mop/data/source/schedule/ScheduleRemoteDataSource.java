package com.youngman.mop.data.source.schedule;

import android.util.Log;

import com.youngman.mop.data.Club;
import com.youngman.mop.data.MyClubsResponse;
import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.myclubs.MyClubsRemoteDataSource;
import com.youngman.mop.net.RetrofitClient;
import com.youngman.mop.util.LogUtils;

import java.util.HashMap;
import java.util.List;
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
    public void callSchedulesByClubIdAndMonth(Long clubId,
                                              String date,
                                              ListApiListener listener) {

        Call<Map<String, Schedule>> result = RetrofitClient.getInstance().getRetrofitApiService().callSchedulesByClubIdAndMonth(makeParams(clubId, date));

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

    private Map<String, Object> makeParams(Long clubId, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubId", clubId);
        params.put("date", date);

        return params;
    }
}
