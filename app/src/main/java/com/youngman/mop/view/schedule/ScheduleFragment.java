package com.youngman.mop.view.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.squareup.otto.Subscribe;
import com.youngman.mop.R;
import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.schedule.ScheduleRepository;
import com.youngman.mop.lib.decorators.EventDecorator;
import com.youngman.mop.lib.decorators.HighlightWeekendsDecorator;
import com.youngman.mop.lib.decorators.OneDayDecorator;
import com.youngman.mop.lib.otto.ActivityResultEvent;
import com.youngman.mop.util.DateUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.schedule.presenter.ScheduleContract;
import com.youngman.mop.view.schedule.presenter.SchedulePresenter;
import com.youngman.mop.view.schedulecreate.ScheduleCreateActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class ScheduleFragment extends Fragment implements OnDateSelectedListener, OnMonthChangedListener, ScheduleContract.View {

    private Context context;
    private MaterialCalendarView calendarView;
    private TextView tvSelectedDate;
    private LinearLayout llCreateSchedule;
    private LinearLayout llDeleteSchedule;
    private LinearLayout llScheduleInfo;
    private TextView tvScheduleName;
    private TextView tvScheduleContent;
    private TextView tvScheduleRegion;
    private TextView tvScheduleMeetingTime;
    private TextView tvScheduleWriter;
    private ScheduleContract.Presenter presenter;

    private Long clubId;
    private Map<String, Schedule> scheduleMap = new HashMap<>();


    public static ScheduleFragment createFragment(Long clubId) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("EXTRA_CLUB_ID", clubId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_schedule, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        context = view.getContext();
        calendarView = (MaterialCalendarView) view.findViewById(R.id.material_calendar_view);
        tvSelectedDate = (TextView) view.findViewById(R.id.tv_selected_date);
        llCreateSchedule = (LinearLayout) view.findViewById(R.id.ll_create_schedule);
        llDeleteSchedule = (LinearLayout) view.findViewById(R.id.ll_delete_schedule);
        llScheduleInfo = (LinearLayout) view.findViewById(R.id.ll_schedule_info);
        tvScheduleName = (TextView) view.findViewById(R.id.tv_schedule_name);
        tvScheduleContent = (TextView) view.findViewById(R.id.tv_schedule_content);
        tvScheduleRegion = (TextView) view.findViewById(R.id.tv_schedule_region);
        tvScheduleMeetingTime = (TextView) view.findViewById(R.id.tv_schedule_meeting_time);
        tvScheduleWriter = (TextView) view.findViewById(R.id.tv_schedule_writer);
        presenter = new SchedulePresenter(this, ScheduleRepository.getInstance());

        clubId = getArguments().getLong("EXTRA_CLUB_ID");

        calendarView.addDecorators(
                new HighlightWeekendsDecorator(),
                new OneDayDecorator()
        );
        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);
        calendarView.setTitleFormatter(DateUtils::convertMonthFormat);

        llCreateSchedule.setOnClickListener(v -> {
            startScheduleCreateActivity();
        });

        llDeleteSchedule.setOnClickListener(v -> {
            callDeleteSchedule();
        });

        presenter.callSchedulesByClubIdAndMonth(clubId, calendarView.getCurrentDate().getDate().toString());
        onTodaySelected();
    }

    private void onTodaySelected() {
        calendarView.setSelectedDate(CalendarDay.today());

        String strDate = DateUtils.convertDateFormatNow();
        tvSelectedDate.setText(strDate);
        setScheduleInfo(strDate);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView,
                               @NonNull CalendarDay calendarDay,
                               boolean isSelected) {

        String strDate = DateUtils.convertDateFormat(calendarDay);
        tvSelectedDate.setText(isSelected ? strDate : "No Selected");
        setScheduleInfo(strDate);
    }

    private void setScheduleInfo(String strDate) {
        if (!scheduleMap.containsKey(strDate)) {
            llScheduleInfo.setVisibility(View.INVISIBLE);
            return;
        }
        Schedule schedule = scheduleMap.get(strDate);

        tvScheduleName.setText(schedule.getName());
        tvScheduleContent.setText(schedule.getContent());
        tvScheduleRegion.setText(schedule.getRegion());
        tvScheduleMeetingTime.setText(schedule.getOnlyTime());
        tvScheduleWriter.setText(schedule.getWriter());
        llScheduleInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {
        String strDate = DateUtils.convertDateFormat(calendarDay);
        presenter.callSchedulesByClubIdAndMonth(clubId, strDate);
    }

    @Override
    public void setSchedules(Set<String> scheduleDates) {
        Set<CalendarDay> calendarDays = scheduleDates.stream()
                .map(DateUtils::convertStrDateToCalendarDay)
                .collect(Collectors.toSet());

        calendarView.addDecorator(new EventDecorator(calendarDays));
    }

    @Override
    public void setScheduleMap(Map<String, Schedule> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    private void startScheduleCreateActivity() {
        if(calendarView.getSelectedDate() == null) {
            ToastUtils.showToast(context, "날짜를 선택해주세요");
            return;
        }
        Intent intent = new Intent(getActivity(), ScheduleCreateActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        intent.putExtra("EXTRA_SELECTED_DATE", calendarView.getSelectedDate().getDate().toString());
        getActivity().startActivityForResult(intent, 1111);
    }

    @Subscribe
    public void onActivityResult(ActivityResultEvent activityResultEvent) {
        onActivityResult(activityResultEvent.getRequestCode(), activityResultEvent.getResultCode(), activityResultEvent.getData());
        if (activityResultEvent.getRequestCode() == 1111 && activityResultEvent.getResultCode() == 1111) {
            presenter.callSchedulesByClubIdAndMonth(clubId, calendarView.getCurrentDate().getDate().toString());
        }
    }

    private void callDeleteSchedule() {
        if(calendarView.getSelectedDate() == null) {
            ToastUtils.showToast(context, "날짜를 선택해주세요");
            return;
        }
        String strDate = calendarView.getSelectedDate().getDate().toString();

        if (!scheduleMap.containsKey(strDate)) {
            return;
        }
        Long scheduleId = scheduleMap.get(strDate).getId();
        presenter.callDeleteSchedule(scheduleId);
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }
}
