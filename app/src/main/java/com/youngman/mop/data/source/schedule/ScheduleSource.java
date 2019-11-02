package com.youngman.mop.data.source.schedule;

import com.youngman.mop.data.Schedule;

import java.util.Map;

/**
 * Created by YoungMan on 2019-07-06.
 */

public interface ScheduleSource {

    interface ListApiListener {
        void onSuccess(Map<String, Schedule> scheduleMap);
        void onFail(String message);
    }

    interface DeleteApiListener {
        void onSuccess();
        void onFail(String message);
    }

    interface ParticipantApiListener {
        void onSuccess(int participantCount);
        void onFail(String message);
    }

    void callSchedules(Long clubId, String email,
                       String date, ListApiListener listener);

    void callCreateParticipant(Long scheduleId, String email,
                               String name, ParticipantApiListener listener);

    void callParticipantCount(Long scheduleId, ParticipantApiListener listener);
}
