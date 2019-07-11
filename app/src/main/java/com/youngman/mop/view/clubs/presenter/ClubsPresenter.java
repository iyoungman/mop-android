package com.youngman.mop.view.clubs.presenter;

import com.youngman.mop.data.ClubPagingResponse;
import com.youngman.mop.data.source.clubs.ClubsRepository;
import com.youngman.mop.data.source.clubs.ClubsSource;
import com.youngman.mop.listener.OnBasicItemClickListener;
import com.youngman.mop.view.clubs.adapter.ClubsAdapterContract;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubsPresenter implements ClubsContract.Presenter, OnBasicItemClickListener {

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
    public void onStartItemClick(int position) {
        Long clubId = adapterModel.getItem(position).getClubId();
        clubsView.startClubActivity(clubId);
    }

    @Override
    public void setClubsAdapterView(ClubsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setClubsAdapterModel(ClubsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
