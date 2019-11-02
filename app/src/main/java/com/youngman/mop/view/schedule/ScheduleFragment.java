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
import com.youngman.mop.lib.otto.BusProvider;
import com.youngman.mop.util.DateUtils;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.mapmemberadd.MapMemberAddActivity;
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
    private View viewContourOne;
    private View viewContourTwo;
    private LinearLayout llDeleteMapGroup;
    private LinearLayout llDeleteSchedule;
    private LinearLayout llCreateMapGroup;
    private LinearLayout llCreateSchedule;
    private LinearLayout llScheduleInfo;
    private TextView tvScheduleName;
    private TextView tvScheduleParticipate;
    private TextView tvScheduleContent;
    private TextView tvScheduleRegion;
    private TextView tvScheduleParticipantNum;
    private TextView tvScheduleMeetingTime;
    private TextView tvScheduleWriter;
    private ScheduleContract.Presenter presenter;

    private Long clubId;
    private boolean isClubChair;
    private Map<String, Schedule> scheduleMap = new HashMap<>();


    public static ScheduleFragment createFragment(Long clubId, boolean isClubChair) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("EXTRA_CLUB_ID", clubId);
        bundle.putBoolean("EXTRA_IS_CLUB_CHAIR", isClubChair);
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
        viewContourOne = (View) view.findViewById(R.id.view_contour_one);
        viewContourTwo = (View) view.findViewById(R.id.view_contour_two);
        llDeleteMapGroup = (LinearLayout) view.findViewById(R.id.ll_delete_map_group);
        llDeleteSchedule = (LinearLayout) view.findViewById(R.id.ll_delete_schedule);
        llCreateMapGroup = (LinearLayout) view.findViewById(R.id.ll_create_map_group);
        llCreateSchedule = (LinearLayout) view.findViewById(R.id.ll_create_schedule);
        llScheduleInfo = (LinearLayout) view.findViewById(R.id.ll_schedule_info);
        tvScheduleName = (TextView) view.findViewById(R.id.tv_schedule_name);
        tvScheduleParticipate = (TextView) view.findViewById(R.id.tv_schedule_participate);
        tvScheduleContent = (TextView) view.findViewById(R.id.tv_schedule_content);
        tvScheduleRegion = (TextView) view.findViewById(R.id.tv_schedule_region);
        tvScheduleParticipantNum = (TextView) view.findViewById(R.id.tv_schedule_participant_num);
        tvScheduleMeetingTime = (TextView) view.findViewById(R.id.tv_schedule_meeting_time);
        tvScheduleWriter = (TextView) view.findViewById(R.id.tv_schedule_writer);
        presenter = new SchedulePresenter(this, ScheduleRepository.getInstance());

        clubId = getArguments().getLong("EXTRA_CLUB_ID");
        isClubChair = getArguments().getBoolean("EXTRA_IS_CLUB_CHAIR");

        calendarView.addDecorators(
                new HighlightWeekendsDecorator(),
                new OneDayDecorator()
        );
        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);
        calendarView.setTitleFormatter(DateUtils::convertMonthFormat);

        llDeleteSchedule.setOnClickListener(v -> callDeleteSchedule());
        llCreateMapGroup.setOnClickListener(v -> startMapMemberAddActivity());
        llCreateSchedule.setOnClickListener(v -> startScheduleCreateActivity());
        tvScheduleParticipate.setOnClickListener(v -> callChangeParticipant());
        tvScheduleParticipantNum.setOnClickListener(v -> callChangeParticipant());

        presenter.callSchedules(clubId, PrefUtils.readMemberEmailFrom(context),
                calendarView.getCurrentDate().getDate().toString()
        );
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
                               @NonNull CalendarDay calendarDay, boolean isSelected) {
        String strDate = DateUtils.convertDateFormat(calendarDay);
        tvSelectedDate.setText(isSelected ? strDate : "No Selected");
        setScheduleInfo(strDate);
    }

    private void setScheduleInfo(String strDate) {
        if (!scheduleMap.containsKey(strDate)) {
            llScheduleInfo.setVisibility(View.INVISIBLE);
            llDeleteMapGroup.setVisibility(View.GONE);
            llCreateMapGroup.setVisibility(View.GONE);
            viewContourOne.setVisibility(View.GONE);
            viewContourTwo.setVisibility(View.GONE);
            return;
        }
        Schedule schedule = scheduleMap.get(strDate);

        tvScheduleName.setText(schedule.getName());
        tvScheduleContent.setText(schedule.getContent());
        tvScheduleRegion.setText(schedule.getRegion());
        tvScheduleMeetingTime.setText(schedule.getOnlyTime());
        tvScheduleWriter.setText(schedule.getWriter());

        llScheduleInfo.setVisibility(View.VISIBLE);
        llDeleteMapGroup.setVisibility(View.VISIBLE);
        llCreateMapGroup.setVisibility(View.VISIBLE);
        viewContourOne.setVisibility(View.VISIBLE);
        viewContourTwo.setVisibility(View.VISIBLE);

        String participateStr = (schedule.isParticipate()) ? "취소" : "참석";
        tvScheduleParticipate.setText(participateStr);
        presenter.callParticipantCount(schedule.getId());
    }

    @Override
    public void setParticipantCount(int participantCount) {
        tvScheduleParticipantNum.setText(participantCount + "명");
    }

    @Override
    public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {
        String strDate = DateUtils.convertDateFormat(calendarDay);
        presenter.callSchedules(clubId, PrefUtils.readMemberEmailFrom(context), strDate);
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

    private void callDeleteSchedule() {
        if (calendarView.getSelectedDate() == null) {
            ToastUtils.showToast(context, "날짜를 선택해주세요");
            return;
        }

        Long scheduleId = getScheduleId();
        if (scheduleId != null) {
            presenter.callDeleteSchedule(scheduleId);
        }
    }

    private void startMapMemberAddActivity() {
        if (!isClubChair) {
            ToastUtils.showToast(context, "동호회장 권한이 없습니다");
            return;
        }
        Intent intent = new Intent(context, MapMemberAddActivity.class);
        intent.putExtra("EXTRA_SCHEDULE_ID", getScheduleId());
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }

    private void startScheduleCreateActivity() {
        if (calendarView.getSelectedDate() == null) {
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
            presenter.callSchedules(clubId, PrefUtils.readMemberEmailFrom(context), calendarView.getCurrentDate().getDate().toString());
        }
    }

    private void callChangeParticipant() {
        String strDate = calendarView.getSelectedDate().getDate().toString();
        Long scheduleId = scheduleMap.get(strDate).getId();
        String email = PrefUtils.readMemberEmailFrom(context);
        String name = PrefUtils.readMemberNameFrom(context);

        presenter.callChangeParticipant(scheduleId, email, name);
    }

    @Override
    public void changeParticipant(int participantCount) {
        String participantStr = tvScheduleParticipate.getText().toString();

        if (participantStr.equals("참석")) {
            tvScheduleParticipate.setText("취소");
            tvScheduleParticipantNum.setText(participantCount + "명");
        } else {
            tvScheduleParticipate.setText("참석");
            tvScheduleParticipantNum.setText(participantCount + "명");
        }
    }

    private Long getScheduleId() {
        String strDate = calendarView.getSelectedDate().getDate().toString();

        if (!scheduleMap.containsKey(strDate)) {
            ToastUtils.showToast(context, "삭제할 일정이 없습니다");
            return null;
        }
        return scheduleMap.get(strDate).getId();
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
    }
}
