package com.youngman.mop.view.splash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youngman.mop.R;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.myclubs.MyClubsActivity;
import com.youngman.mop.view.signin.SignInActivity;
import com.youngman.mop.view.splash.presenter.SplashContract;
import com.youngman.mop.view.splash.presenter.SplashPresenter;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private Context context;
    private SplashContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        context = getApplicationContext();
        presenter = new SplashPresenter(this);

        loading();
        confirmAutoSignIn();
    }

    private void loading() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void confirmAutoSignIn() {
        if (PrefUtils.readAutoSignInFrom(context)) {
            String token = PrefUtils.readMemberTokenFrom(context);
            presenter.callIsValidToken(token);
        } else {
            Intent intent = new Intent(context, SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void startSignInActivityByToken() {
        ToastUtils.showToast(context, "자동 로그인 실패");
        Intent intent = new Intent(context, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startMyClubActivityByToken() {
        ToastUtils.showToast(context, "자동 로그인 성공");
        Intent intent = new Intent(context, MyClubsActivity.class);
        startActivity(intent);
        finish();
    }
}
