package com.youngman.mop.view.schedulecreate.presenter;

import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.schedulecreate.ScheduleCreateRepository;
import com.youngman.mop.data.source.schedulecreate.ScheduleCreateSource;

/**
 * Created by YoungMan on 2019-06-09.
 */

public class ScheduleCreatePresenter implements ScheduleCreateContract.Presenter {

    private ScheduleCreateContract.View scheduleCreateView;
    private final ScheduleCreateRepository scheduleCreateRepository;


    public ScheduleCreatePresenter(ScheduleCreateContract.View scheduleCreateView,
                                   ScheduleCreateRepository scheduleCreateRepository) {
        this.scheduleCreateView = scheduleCreateView;
        this.scheduleCreateRepository = scheduleCreateRepository;
    }

    @Override
    public void callCreateSchedule(Schedule schedule) {
        if (schedule.isAllNonNull()) {
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
