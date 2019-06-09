package com.youngman.mop.view.schedulecreate.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.schedulecreate.ScheduleCreateRepository;
import com.youngman.mop.data.source.schedulecreate.ScheduleCreateSource;

/**
 * Created by YoungMan on 2019-06-09.
 */

public class ScheduleCreatePresenter implements ScheduleCreateContract.Presenter {

    private ScheduleCreateContract.View scheduleCreateView;
    private final ScheduleCreateRepository scheduleCreateRepository;


    public ScheduleCreatePresenter(@NonNull ScheduleCreateContract.View scheduleCreateView,
                                   @NonNull ScheduleCreateRepository scheduleCreateRepository) {

        this.scheduleCreateView = scheduleCreateView;
        this.scheduleCreateRepository = scheduleCreateRepository;
    }

    @Override
    public void callCreateSchedule(@NonNull Schedule schedule) {

        if (schedule.checkData()) {
            scheduleCreateRepository.callCreateSchedule(schedule, new ScheduleCreateSource.ApiListener() {

                @Override
                public void onSuccess() {
                    scheduleCreateView.startScheduleFragment();
                }

                @Override
                public void onFail(String message) {
                    scheduleCreateView.showErrorMessage(message);
                }
            });
            return;
        }
        scheduleCreateView.showErrorMessage("입력한 내용이 올바르지 않습니다.");
    }
}
