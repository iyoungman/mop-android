package com.youngman.mop.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.contract.SignUpContract;
import com.youngman.mop.model.dto.SignUpDto;
import com.youngman.mop.presenter.SignUpPresenter;
import com.youngman.mop.util.ToastUtils;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class SignUpActivity extends Activity implements SignUpContract.View {

    private Context context;
    private EditText etEmail;
    private EditText etPw;
    private EditText etName;
    private EditText etMobile;
    private EditText etHobby;
    private Button btnSignUp;

    SignUpContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        presenter = new SignUpPresenter(this);
        initView();
    }

    private void initView() {
        context = getApplicationContext();
        etEmail = (EditText) findViewById(R.id.et_email);
        etPw = (EditText) findViewById(R.id.et_pw);
        etName = (EditText) findViewById(R.id.et_name);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etHobby = (EditText) findViewById(R.id.et_hobby);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(view -> presenter.callSignUp(SignUpDto.builder()
                .email(etEmail.getText().toString())
                .pw(etPw.getText().toString())
                .name(etName.getText().toString())
                .mobile(etMobile.getText().toString())
                .hobby(etHobby.getText().toString())
                .build()
        ));
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startSignInActivity() {
        ToastUtils.showToast(context, "회원가입 성공");
        finish();
    }
}
