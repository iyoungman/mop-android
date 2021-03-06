package com.youngman.mop.view.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.data.SignInResponse;
import com.youngman.mop.data.source.signin.SignInRepository;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.myclubs.MyClubsActivity;
import com.youngman.mop.view.signin.presenter.SignInContract;
import com.youngman.mop.view.signin.presenter.SignInPresenter;
import com.youngman.mop.view.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private Context context;
    private EditText etEmail;
    private EditText etPw;
    private Button btnSignIn;
    private Button btnStartSignUp;
    private CheckBox cbAutoSignIn;
    private SignInContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        init();
    }

    private void init() {
        context = getApplicationContext();
        etEmail = (EditText) findViewById(R.id.et_email);
        etPw = (EditText) findViewById(R.id.et_pw);
        btnSignIn = (Button) findViewById(R.id.btn_signin);
        btnStartSignUp = (Button) findViewById(R.id.btn_start_signup);
        cbAutoSignIn = (CheckBox) findViewById(R.id.cb_auto_signin);
        presenter = new SignInPresenter(this, SignInRepository.getInstance());

        btnSignIn.setOnClickListener(v -> presenter.callSignIn(etEmail.getText().toString(),
                etPw.getText().toString()
        ));

        btnStartSignUp.setOnClickListener(v -> startSignUpActivity());
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startMyClubActivity(SignInResponse signInResponse) {
        PrefUtils.writeMemberInfoTo(context, signInResponse);

        if(cbAutoSignIn.isChecked()) {
            PrefUtils.writeAutoSignInTo(context);
        }

        Intent intent = new Intent(context, MyClubsActivity.class);
        startActivity(intent);
        finish();
    }

    private void startSignUpActivity() {
        Intent intent = new Intent(context, SignUpActivity.class);
        startActivity(intent);
    }

}
