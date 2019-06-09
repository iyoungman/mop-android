package com.youngman.mop.view.schedulecreate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.source.schedulecreate.ScheduleCreateRepository;
import com.youngman.mop.data.source.signup.SignUpRepository;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.schedulecreate.presenter.ScheduleCreateContract;
import com.youngman.mop.view.schedulecreate.presenter.ScheduleCreatePresenter;

public class ScheduleCreateActivity extends AppCompatActivity implements ScheduleCreateContract.View {

    private Context context;
    private EditText etName;
    private EditText etContent;
    private EditText etRegion;
    private EditText etMeetingTime;
    private Button btnCreateSchedule;
    private ScheduleCreateContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_create);
        presenter = new ScheduleCreatePresenter(this, ScheduleCreateRepository.getInstance());
        initView();
    }

    private void initView() {
        context = getApplicationContext();
        etName = (EditText) findViewById(R.id.et_name);
        etContent = (EditText) findViewById(R.id.et_content);
        etRegion = (EditText) findViewById(R.id.et_region);
        etMeetingTime = (EditText) findViewById(R.id.et_meeting_time);
        btnCreateSchedule = (Button) findViewById(R.id.btn_create_schedule);

        btnCreateSchedule.setOnClickListener(view -> presenter.callCreateSchedule(Schedule.builder()
                .name(etName.getText().toString())
                .content(etContent.getText().toString())
                .region(etRegion.getText().toString())
                .writer("Name")
                .meetingTime(etMeetingTime.getText().toString())
                .build()
        ));
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startScheduleFragment() {
        ToastUtils.showToast(context, "일정 저장 성공");
        finish();
    }
}
