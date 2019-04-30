package com.youngman.mop.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.contract.SignInContract;
import com.youngman.mop.contract.SignUpContract;
import com.youngman.mop.model.domain.MyPageModel;
import com.youngman.mop.model.dto.SignUpDto;
import com.youngman.mop.presenter.SignInPresenter;
import com.youngman.mop.presenter.SignUpPresenter;
import com.youngman.mop.util.ToastUtils;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private Context context;
    private EditText etId;
    private EditText etPw;
    private Button btnSignIn;

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
        etId = (EditText) findViewById(R.id.et_id);
        etPw = (EditText) findViewById(R.id.et_pw);
        btnSignIn = (Button) findViewById(R.id.btn_signin);

        btnSignIn.setOnClickListener(v -> presenter.callSignIn(etId.getText().toString(),
                etPw.getText().toString()
        ));
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startMyPageActivity() {
        Intent intent = new Intent(context, MyPageActivity.class);
        intent.putExtra("MyPageModel", MyPageModel);//MyPage에 셋팅할 정보를 가져온다.
        startActivity(intent);
    }

    public void startSignUpActivity() {
        Intent intent = new Intent(context, SignUpActivity.class);
        startActivity(intent);
    }

}
