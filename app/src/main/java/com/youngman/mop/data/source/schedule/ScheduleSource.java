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

    void callSchedulesByClubIdAndMonth(Long clubId,
                                       String date,
                                       ListApiListener listener);
}
