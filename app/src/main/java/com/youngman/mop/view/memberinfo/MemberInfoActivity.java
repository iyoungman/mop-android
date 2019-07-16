package com.youngman.mop.view.memberinfo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.Member;

public class MemberInfoActivity extends Activity {

    private Context context;
    private TextView tvMemberEmail;
    private TextView tvMemberName;
    private TextView tvMemberIntroduce;
    private TextView tvMemberHobby;
    private TextView tvMemberMobile;
    private Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_member_info);
        init();
    }

    public void init() {
        context = getApplicationContext();
        tvMemberEmail = (TextView) findViewById(R.id.tv_member_email);
        tvMemberName = (TextView) findViewById(R.id.tv_member_name);
        tvMemberIntroduce = (TextView) findViewById(R.id.tv_member_introduce);
        tvMemberHobby = (TextView) findViewById(R.id.tv_member_hobby);
        tvMemberMobile = (TextView) findViewById(R.id.tv_member_mobile);
        btnBack = (Button) findViewById(R.id.btn_back);

        Member member = (Member) getIntent().getSerializableExtra("EXTRA_MEMBER");
        setMemberInfo(member);

        btnBack.setOnClickListener(v -> finish());
    }

    private void setMemberInfo(Member member) {
        tvMemberEmail.setText(member.getEmail());
        tvMemberName.setText(member.getName());
        tvMemberIntroduce.setText(member.getIntroduce());
        tvMemberHobby.setText(member.getHobby());
        tvMemberMobile.setText(member.getMobile());
    }

}
