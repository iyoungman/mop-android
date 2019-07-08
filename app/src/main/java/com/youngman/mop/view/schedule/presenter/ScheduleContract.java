package com.youngman.mop.view.schedule.presenter;

import com.youngman.mop.data.Schedule;

import java.util.Map;
import java.util.Set;

/**
 * Created by YoungMan on 2019-07-06.
 */

public interface ScheduleContract {

    interface View {
        void setSchedules(Set<String> scheduleDates);
        void setScheduleMap(Map<String, Schedule> scheduleMap);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void callSchedulesByClubIdAndMonth(Long clubId, String date);
        void callDeleteSchedule(Long scheduleId);
    }
}
