package com.youngman.mop.data.source.schedulecreate;

import com.youngman.mop.data.Schedule;
import com.youngman.mop.net.api.RetrofitApiClient;
import com.youngman.mop.util.LogUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-09.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleCreateRemoteDataSource {

    private static ScheduleCreateRemoteDataSource INSTANCE;


    public static ScheduleCreateRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScheduleCreateRemoteDataSource();
        }

        return INSTANCE;
    }

    /**
     * 일정 저장
     */
    public void callCreateSchedule(Schedule schedule, ScheduleCreateSource.ApiListener listener) {
        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callCreateSchedule(schedule);
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                LogUtils.logInfo(response.message());
                listener.onFail("일정 저장에 실패하였습니다");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
