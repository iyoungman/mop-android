package com.youngman.mop.data.source.schedulecreate;

import com.youngman.mop.data.Schedule;

/**
 * Created by YoungMan on 2019-06-09.
 */

public class ScheduleCreateRepository implements ScheduleCreateSource {

    private static ScheduleCreateRepository scheduleCreateRepository;
    private ScheduleCreateRemoteDataSource scheduleCreateRemoteDataSource;


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
    public void callCreateSchedule(Schedule schedule, ApiListener listener) {
        scheduleCreateRemoteDataSource.callCreateSchedule(schedule, listener);
    }

}
