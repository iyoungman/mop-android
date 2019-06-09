package com.youngman.mop.data.source.schedulecreate;

import android.support.annotation.NonNull;

import com.youngman.mop.data.Schedule;

/**
 * Created by YoungMan on 2019-06-09.
 */

public class ScheduleCreateRepository implements ScheduleCreateSource {

    private ScheduleCreateRemoteDataSource scheduleCreateRemoteDataSource;
    private static ScheduleCreateRepository scheduleCreateRepository;


    public static ScheduleCreateRepository getInstance() {
        if(scheduleCreateRepository == null) {
            scheduleCreateRepository = new ScheduleCreateRepository();
        }

        return scheduleCreateRepository;
    }

    private ScheduleCreateRepository() {
        scheduleCreateRemoteDataSource = ScheduleCreateRemoteDataSource.getInstance();
    }

    @Override
    public void callCreateSchedule(@NonNull Schedule schedule, @NonNull ApiListener listener) {
        scheduleCreateRemoteDataSource.callCreateSchedule(schedule, listener);
    }

}
