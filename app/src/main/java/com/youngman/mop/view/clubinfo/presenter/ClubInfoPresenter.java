package com.youngman.mop.view.clubinfo.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.ClubInfoResponse;
import com.youngman.mop.data.Member;
import com.youngman.mop.data.source.clubinfo.ClubInfoRepository;
import com.youngman.mop.data.source.clubinfo.ClubInfoSource;
import com.youngman.mop.listener.OnMemberItemClickListener;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapterContract;

/**
 * Created by YoungMan on 2019-05-28.
 */

public class ClubInfoPresenter implements ClubInfoContract.Presenter, OnMemberItemClickListener {

    private ClubInfoContract.View infoView;
    private final ClubInfoRepository clubInfoRepository;

    private MembersAdapterContract.View adapterView;
    private MembersAdapterContract.Model adapterModel;


    public ClubInfoPresenter(@NonNull ClubInfoContract.View infoView,
                             @NonNull ClubInfoRepository clubInfoRepository) {

        this.infoView = infoView;
        this.clubInfoRepository = clubInfoRepository;
    }

    @Override
    public void callClubInfoByClubId(@NonNull Long clubId) {
        clubInfoRepository.callClubInfoByClubId(clubId, new ClubInfoSource.ApiListener() {
            @Override
            public void onSuccess(ClubInfoResponse clubInfoResponse) {
                infoView.setClubInfo(clubInfoResponse.getClub());
                adapterModel.addItems(clubInfoResponse.getMembers());
                adapterView.notifyAdapter();
            }

            @Override
            public void onFail(String message) {
                infoView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void onStartMemberClick(@NonNull Integer position) {
        Member member = adapterModel.getItem(position);
        infoView.startMemberInfoActivity(member);
    }

    @Override
    public void setMemberListAdapterView(@NonNull MembersAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnMemberItemClickListener(this);
    }

    @Override
    public void setMemberListAdapterModel(@NonNull MembersAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
