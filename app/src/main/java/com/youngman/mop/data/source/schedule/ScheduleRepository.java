package com.youngman.mop.data.source.schedule;

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
    public void callSchedules(Long clubId,
                              String date,
                              ScheduleSource.ListApiListener listener) {

        scheduleRemoteDataSource.callSchedules(clubId, date, listener);
    }

    @Override
    public void callCreateParticipant(Long scheduleId,
                                      String email,
                                      String name,
                                      ParticipantApiListener listener) {


    }
}
