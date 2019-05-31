package com.youngman.mop.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.contract.SignInContract;
import com.youngman.mop.presenter.SignInPresenter;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private Context context;
    private EditText etEmail;
    private EditText etPw;
    private Button btnSignIn;
    private Button btnStartSignUp;

    SignInContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        presenter = new SignInPresenter(this);
        initView();
    }

    private void initView() {
        context = getApplicationContext();
        etEmail = (EditText) findViewById(R.id.et_email);
        etPw = (EditText) findViewById(R.id.et_pw);
        btnSignIn = (Button) findViewById(R.id.btn_signin);
        btnStartSignUp = (Button) findViewById(R.id.btn_start_signup);

        btnSignIn.setOnClickListener(view -> presenter.callSignIn(etEmail.getText().toString(),
                etPw.getText().toString()
        ));

        btnStartSignUp.setOnClickListener(view -> startSignUpActivity());
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startMyClubActivity(@NonNull String email) {
        SignUtils.writeUserIdToPref(context, email);

        Intent intent = new Intent(context, MyClubListActivity.class);
        startActivity(intent);
        finish();
    }

    public void startSignUpActivity() {
        Intent intent = new Intent(context, SignUpActivity.class);
        startActivity(intent);
    }

}
