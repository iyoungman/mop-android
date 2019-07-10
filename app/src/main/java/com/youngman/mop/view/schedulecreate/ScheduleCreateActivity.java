package com.youngman.mop.view.schedulecreate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.schedulecreate.ScheduleCreateRepository;
import com.youngman.mop.util.DateUtils;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.schedulecreate.presenter.ScheduleCreateContract;
import com.youngman.mop.view.schedulecreate.presenter.ScheduleCreatePresenter;

public class ScheduleCreateActivity extends AppCompatActivity implements ScheduleCreateContract.View {

    private Context context;
    private EditText etName;
    private EditText etContent;
    private EditText etRegion;
    private EditText etMeetingDate;
    private EditText etMeetingHour;
    private EditText etMeetingMinute;
    private Button btnCreateSchedule;
    private ScheduleCreateContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_create);
        init();
    }

    private void init() {
        context = getApplicationContext();
        etName = (EditText) findViewById(R.id.et_name);
        etContent = (EditText) findViewById(R.id.et_content);
        etRegion = (EditText) findViewById(R.id.et_region);
        etMeetingDate = (EditText) findViewById(R.id.et_meeting_date);
        etMeetingHour = (EditText) findViewById(R.id.et_meeting_hour);
        etMeetingMinute = (EditText) findViewById(R.id.et_meeting_minute);
        btnCreateSchedule = (Button) findViewById(R.id.btn_create_schedule);
        presenter = new ScheduleCreatePresenter(this, ScheduleCreateRepository.getInstance());

        Long clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 0);
        String scheduleDate = getIntent().getStringExtra("EXTRA_SELECTED_DATE");
        etMeetingDate.setText(scheduleDate);

        btnCreateSchedule.setOnClickListener(view -> presenter.callCreateSchedule(Schedule.builder()
                .name(etName.getText().toString())
                .content(etContent.getText().toString())
                .region(etRegion.getText().toString())
                .writer(SignUtils.readMemberNameFromPref(context))
                .meetingTime(getMeetingDateTime())
                .clubId(clubId)
                .build()
        ));
    }

    private String getMeetingDateTime() {
        return DateUtils.convertToStrDateTime(etMeetingDate.getText().toString(),
                etMeetingHour.getText().toString(),
                etMeetingMinute.getText().toString()
        );
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startScheduleFragment() {
        ToastUtils.showToast(context, "일정 저장 성공");
        Intent intent = new Intent();
        intent.putExtra("result", "1111");
        setResult(1111, intent);
        finish();
    }
}