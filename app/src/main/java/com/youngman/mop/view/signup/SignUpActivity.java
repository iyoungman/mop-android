package com.youngman.mop.view.signup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.youngman.mop.R;
import com.youngman.mop.data.SignUp;
import com.youngman.mop.data.source.signup.SignUpRepository;
import com.youngman.mop.lib.fcm.FcmTokenService;
import com.youngman.mop.util.LogUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.signup.presenter.SignUpContract;
import com.youngman.mop.view.signup.presenter.SignUpPresenter;

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
    private SignUpContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }

    private void init() {
        context = getApplicationContext();
        etEmail = (EditText) findViewById(R.id.et_email);
        etPw = (EditText) findViewById(R.id.et_pw);
        etName = (EditText) findViewById(R.id.et_name);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etHobby = (EditText) findViewById(R.id.et_hobby);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        presenter = new SignUpPresenter(this, SignUpRepository.getInstance());

        btnSignUp.setOnClickListener(view -> presenter.callSignUp(SignUp.builder()
                .email(etEmail.getText().toString())
                .pw(etPw.getText().toString())
                .name(etName.getText().toString())
                .mobile(etMobile.getText().toString())
                .hobby(etHobby.getText().toString())
                .fcmToken(FcmTokenService.getFcmToken())
                .build()
        ));
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startSignInActivity() {
        ToastUtils.showToast(context, "회원가입 성공");
        finish();
    }
}
