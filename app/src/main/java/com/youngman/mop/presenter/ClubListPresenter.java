package com.youngman.mop.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.ClubListAdapterContract;
import com.youngman.mop.contract.ClubListContract;
import com.youngman.mop.listener.OnClubListItemClickListener;
import com.youngman.mop.model.domain.ClubListModel;
import com.youngman.mop.model.dto.ClubListDto;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubListPresenter implements ClubListContract.Presenter, OnClubListItemClickListener {

    private ClubListContract.View clubListView;
    private ClubListModel clubListModel;
    private ClubListAdapterContract.View adapterView;
    private ClubListAdapterContract.Model adapterModel;

    public ClubListPresenter(@NonNull ClubListContract.View clubListView) {
        this.clubListView = clubListView;
        this.clubListModel = new ClubListModel();
    }

    @Override
    public void callClubListByUserInfo(@NonNull String email, @NonNull Integer pageNo) {
        clubListModel.callClubListByUserInfo(email, pageNo, new ClubListModel.ListApiListener() {
            @Override
            public void onSuccess(ClubListDto clubListDto, boolean isLast) {
                adapterModel.addItems(clubListDto.getClubDtoList());
                adapterView.notifyAdapter();
                adapterModel.setIsLast(isLast);
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
                adapterModel.addItems(clubListDto.getClubDtoList());
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
    public void setClubListAdapterView(@NonNull ClubListAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setClubListAdapterModel(@NonNull ClubListAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
