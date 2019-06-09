package com.youngman.mop.view.schedulecreate.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.Schedule;

/**
 * Created by YoungMan on 2019-06-09.
 */

public interface ScheduleCreateContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startScheduleFragment();
    }

    interface Presenter {
        void callCreateSchedule(@NonNull Schedule schedule);
    }

}
