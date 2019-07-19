package com.youngman.mop.view.clubs.presenter;

import com.youngman.mop.data.ClubPagingResponse;
import com.youngman.mop.data.source.clubs.ClubsRepository;
import com.youngman.mop.data.source.clubs.ClubsSource;
import com.youngman.mop.view.clubs.adapter.ClubsAdapterContract;
import com.youngman.mop.view.clubs.adapter.OnClubsItemClickListener;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubsPresenter implements ClubsContract.Presenter, OnClubsItemClickListener {

    private ClubsContract.View clubsView;
    private final ClubsRepository clubsRepository;

    private ClubsAdapterContract.View adapterView;
    private ClubsAdapterContract.Model adapterModel;


    public ClubsPresenter(ClubsContract.View clubsView, ClubsRepository clubsRepository) {
        this.clubsView = clubsView;
        this.clubsRepository = clubsRepository;
    }

    @Override
    public void callClubsByUserInfo(String email, int pageNo) {
        clubsRepository.callClubsByUserInfo(email, pageNo, new ClubsSource.ListApiListener() {
            @Override
            public void onSuccess(ClubPagingResponse clubPagingResponse) {
                adapterModel.addItems(clubPagingResponse.getClubs());
                adapterView.notifyAdapter();
                adapterModel.setIsLast(clubPagingResponse.isLast());
                adapterModel.setMoreLoading(false);
            }

            @Override
            public void onFail(String message) {
                clubsView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void onStartClubClick(Long clubId) {
        clubsView.startClubActivity(clubId);
    }

    @Override
    public void onJoinClubClick(String email, Long clubId) {
        clubsRepository.callCreateMyClub(email, clubId, new ClubsSource.CreateApiListener() {
            @Override
            public void onSuccess() {
                clubsView.successCreateMyClub();
            }

            @Override
            public void onFail(String message) {
                clubsView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void setClubsAdapterView(ClubsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClubsItemClickListener(this);
    }

    @Override
    public void setClubsAdapterModel(ClubsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
