package com.youngman.mop.data.source.schedule;

import com.youngman.mop.data.source.myclubs.MyClubsRemoteDataSource;
import com.youngman.mop.data.source.myclubs.MyClubsRepository;
import com.youngman.mop.data.source.myclubs.MyClubsSource;

/**
 * Created by YoungMan on 2019-07-06.
 */

public class ScheduleRepository implements ScheduleSource {

    private static ScheduleRepository scheduleRepository;
    private ScheduleRemoteDataSource scheduleRemoteDataSource;


    public static ScheduleRepository getInstance() {
        if (scheduleRepository == null) {
            scheduleRepository = new ScheduleRepository();
        }

        return scheduleRepository;
    }

    private ScheduleRepository() {
        scheduleRemoteDataSource = ScheduleRemoteDataSource.getInstance();
    }

    @Override
    public void callSchedulesByClubIdAndMonth(Long clubId,
                                              String date,
                                              ScheduleSource.ListApiListener listener) {

        scheduleRemoteDataSource.callSchedulesByClubIdAndMonth(clubId, date, listener);
    }
}
