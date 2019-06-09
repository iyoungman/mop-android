package com.youngman.mop.data.source.schedulecreate;

import android.support.annotation.NonNull;
import android.util.Log;

import com.youngman.mop.data.Club;
import com.youngman.mop.data.MyClubsResponse;
import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.myclubs.MyClubsSource;
import com.youngman.mop.net.NetRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-09.
 */

public class ScheduleCreateRemoteDataSource {

    private static ScheduleCreateRemoteDataSource INSTANCE;


    private ScheduleCreateRemoteDataSource() {
    }

    public static ScheduleCreateRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScheduleCreateRemoteDataSource();
        }

        return INSTANCE;
    }

    /**
     * 일정 저장
     */
    public void callCreateSchedule(@NonNull Schedule schedule, @NonNull final ScheduleCreateSource.ApiListener listener) {
        Call<Void> result = NetRetrofit.getInstance().getNetRetrofitInterface().callCreateSchedule(schedule);
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("일정 저장에 실패하였습니다");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
