package com.youngman.mop.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.ClubListAdapter;
import com.youngman.mop.adapter.contract.ClubListAdapterContract;
import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.contract.ClubListContract;
import com.youngman.mop.model.domain.ClubListModel;
import com.youngman.mop.model.dto.ClubListDto;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubListPresenter implements ClubListContract.Presenter {

    private ClubListContract.View clubListView;
    private ClubListModel clubListModel;
    private ClubListAdapterContract.View adapterView;
    private ClubListAdapterContract.Model adapterModel;

    public ClubListPresenter(@NonNull ClubListContract.View clubListView) {
        this.clubListView = clubListView;
        this.clubListModel = new ClubListModel();
    }

    @Override
    public void callClubListByUserInfo(@NonNull String userId) {
        clubListModel.callClubListByUserInfo(userId, new ClubListModel.ListApiListener() {
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
    }

    @Override
    public void callClubListBySearch(@NonNull String searchClub) {
        clubListModel.callClubListBySearch(searchClub, new ClubListModel.ListApiListener() {
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
    }

    @Override
    public void setMyClubAdapterView(@NonNull ClubListAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setMyClubAdapterModel(@NonNull ClubListAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
