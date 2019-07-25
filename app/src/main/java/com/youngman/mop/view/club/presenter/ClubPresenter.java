package com.youngman.mop.view.club.presenter;

import com.youngman.mop.data.source.club.ClubRemoteDataSource;

/**
 * Created by YoungMan on 2019-07-25.
 */

public class ClubPresenter implements ClubContract.Presenter {

    private ClubContract.View clubView;
    private final ClubRemoteDataSource clubRemoteDataSource;


    public ClubPresenter(ClubContract.View clubView) {
        this.clubView = clubView;
        this.clubRemoteDataSource = ClubRemoteDataSource.getInstance();
    }

    @Override
    public void callIsClubChair(Long clubId, String email) {
        clubRemoteDataSource.callIsClubChair(clubId, email, new ClubRemoteDataSource.ApiListener() {
            @Override
            public void onSuccess() {
                clubView.setIsClubChair(true);
            }
            @Override
            public void onFail(String message) {
                clubView.showErrorMessage(message);
            }
        });
    }
}
