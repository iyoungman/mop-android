package com.youngman.mop.view.clubinfo.presenter;

import com.youngman.mop.data.ClubInfoResponse;
import com.youngman.mop.data.Member;
import com.youngman.mop.data.source.clubinfo.ClubInfoRepository;
import com.youngman.mop.data.source.clubinfo.ClubInfoSource;
import com.youngman.mop.listener.OnBasicItemClickListener;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapterContract;

/**
 * Created by YoungMan on 2019-05-28.
 */

public class ClubInfoPresenter implements ClubInfoContract.Presenter, OnBasicItemClickListener {

    private ClubInfoContract.View infoView;
    private final ClubInfoRepository clubInfoRepository;

    private MembersAdapterContract.View adapterView;
    private MembersAdapterContract.Model adapterModel;


    public ClubInfoPresenter(ClubInfoContract.View infoView, ClubInfoRepository clubInfoRepository) {
        this.infoView = infoView;
        this.clubInfoRepository = clubInfoRepository;
    }

    @Override
    public void callClubInfoByClubId(Long clubId) {
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
    public void onStartItemClick(int position) {
        Member member = adapterModel.getItem(position);
        infoView.startMemberInfoActivity(member);
    }

    @Override
    public void setMembersAdapterView(MembersAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnBasicItemClickListener(this);
    }

    @Override
    public void setMembersAdapterModel(MembersAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
