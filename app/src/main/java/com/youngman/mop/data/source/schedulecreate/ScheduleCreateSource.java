package com.youngman.mop.data.source.schedulecreate;

import android.support.annotation.NonNull;

import com.youngman.mop.data.Schedule;

/**
 * Created by YoungMan on 2019-06-09.
 */

public interface ScheduleCreateSource {

    interface ApiListener {
        void onSuccess();
        void onFail(String message);
    }

    void callCreateSchedule(@NonNull Schedule schedule, @NonNull final ApiListener listener);
}
