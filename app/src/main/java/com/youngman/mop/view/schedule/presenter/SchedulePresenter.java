package com.youngman.mop.view.schedule.presenter;

import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.schedule.ScheduleRepository;
import com.youngman.mop.data.source.schedule.ScheduleSource;

import java.util.Map;

/**
 * Created by YoungMan on 2019-07-06.
 */

public class SchedulePresenter implements ScheduleContract.Presenter {

    private ScheduleContract.View scheduleView;
    private final ScheduleRepository scheduleRepository;


    public SchedulePresenter(ScheduleContract.View scheduleView, ScheduleRepository scheduleRepository) {
        this.scheduleView = scheduleView;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void callSchedules(Long clubId, String email, String date) {
        scheduleRepository.callSchedules(clubId, email, date, new ScheduleSource.ListApiListener() {
            @Override
            public void onSuccess(Map<String, Schedule> scheduleMap) {
                scheduleView.setSchedules(scheduleMap.keySet());
                scheduleView.setScheduleMap(scheduleMap);
            }
            @Override
            public void onFail(String message) {
                scheduleView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void callDeleteSchedule(Long scheduleId) {

    }

    @Override
    public void callChangeParticipant(Long scheduleId, String email, String name) {
        scheduleRepository.callCreateParticipant(scheduleId, email, name, new ScheduleSource.ParticipantApiListener() {
            @Override
            public void onSuccess(int participantCount) {
                scheduleView.changeParticipant(participantCount);
            }
            @Override
            public void onFail(String message) {
                scheduleView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void callParticipantCount(Long scheduleId) {
        scheduleRepository.callParticipantCount(scheduleId, new ScheduleSource.ParticipantApiListener() {
            @Override
            public void onSuccess(int participantCount) {
                scheduleView.setParticipantCount(participantCount);
            }
            @Override
            public void onFail(String message) {
                scheduleView.showErrorMessage(message);
            }
        });
    }
}
