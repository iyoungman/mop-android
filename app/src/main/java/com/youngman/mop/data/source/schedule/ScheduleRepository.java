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
    public void callSchedules(Long clubId, String email, String date,
                              ScheduleSource.ListApiListener listener) {
        scheduleRemoteDataSource.callSchedules(clubId, email, date, listener);
    }

    @Override
    public void callCreateParticipant(Long scheduleId, String email,
                                      String name, ParticipantApiListener listener) {
        scheduleRemoteDataSource.callCreateParticipant(scheduleId, email, name, listener);
    }

    @Override
    public void callParticipantCount(Long scheduleId, ParticipantApiListener listener) {
        scheduleRemoteDataSource.callParticipantCount(scheduleId, listener);
    }
}
