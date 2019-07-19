package com.youngman.mop.view.splash.presenter;

import com.youngman.mop.data.source.splash.TokenRemoteDataSource;

/**
 * Created by YoungMan on 2019-07-18.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View splashView;
    private TokenRemoteDataSource tokenRemoteDataSource;


    public SplashPresenter(SplashContract.View splashView) {
        this.splashView = splashView;
        this.tokenRemoteDataSource = TokenRemoteDataSource.getInstance();
    }

    @Override
    public void callIsValidToken(String token) {
        tokenRemoteDataSource.callIsValidToken(token, new TokenRemoteDataSource.TokenApiListener() {
            @Override
            public void onSuccess() {
                splashView.startMyClubActivityByToken();
            }

            @Override
            public void onFail(String message) {
                splashView.startSignInActivityByToken();
            }
        });
    }
}
