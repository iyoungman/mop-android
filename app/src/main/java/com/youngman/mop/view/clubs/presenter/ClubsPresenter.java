package com.youngman.mop.view.clubs.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.ClubsResponse;
import com.youngman.mop.data.source.clubs.ClubsRepository;
import com.youngman.mop.data.source.clubs.ClubsSource;
import com.youngman.mop.listener.OnClubsItemClickListener;
import com.youngman.mop.view.clubs.adapter.ClubsAdapterContract;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubsPresenter implements ClubsContract.Presenter, OnClubsItemClickListener {

    private ClubsContract.View clubListView;
    private final ClubsRepository clubsRepository;

    private ClubsAdapterContract.View adapterView;
    private ClubsAdapterContract.Model adapterModel;


    public ClubsPresenter(@NonNull ClubsContract.View clubListView,
                          @NonNull ClubsRepository clubsRepository) {

        this.clubListView = clubListView;
        this.clubsRepository = clubsRepository;
    }

    @Override
    public void callClubListByUserInfo(@NonNull String email, @NonNull Integer pageNo) {
        clubsRepository.callClubListByUserInfo(email, pageNo, new ClubsSource.ListApiListener() {
            @Override
            public void onSuccess(ClubsResponse clubsResponse) {
                adapterModel.addItems(clubsResponse.getClubs());
                adapterView.notifyAdapter();
                adapterModel.setIsLast(clubsResponse.isLast());
                adapterModel.setMoreLoading(false);
            }

            @Override
            public void onFail(String message) {
                clubListView.showErrorMessage(message);
            }
        });
    }

    /*@Override
    public void callPagingClubsBySearch(@NonNull String searchClub) {
        clubListModel.callPagingClubsBySearch(searchClub, new ClubListModel.ListApiListener() {
            @Override
            public void onSuccess(ClubListDto clubListDto) {
                adapterModel.addItems(clubListDto.getClubDtos());
                adapterView.notifyAdapter();
            }
            @Override
            public void onFail(String message) {
                clubListView.showErrorMessage(message);
            }
        });
    }*/

    @Override
    public void onStartClubClick(@NonNull int position) {
        Long clubId = adapterModel.getItem(position).getClubId();
        clubListView.startClubActivity(clubId);
    }

    @Override
    public void setClubListAdapterView(@NonNull ClubsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setClubListAdapterModel(@NonNull ClubsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
