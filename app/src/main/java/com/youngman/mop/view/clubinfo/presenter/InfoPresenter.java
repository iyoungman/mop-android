package com.youngman.mop.view.clubinfo.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.listener.OnMemberItemClickListener;
import com.youngman.mop.model.domain.InfoModel;
import com.youngman.mop.model.dto.InfoDto;
import com.youngman.mop.model.dto.MemberDto;
import com.youngman.mop.view.clubinfo.adapter.MemberListAdapterContract;

/**
 * Created by YoungMan on 2019-05-28.
 */

public class InfoPresenter implements InfoContract.Presenter, OnMemberItemClickListener {

    private InfoContract.View infoView;
    private InfoModel infoModel;
    private MemberListAdapterContract.View adapterView;
    private MemberListAdapterContract.Model adapterModel;


    public InfoPresenter(@NonNull InfoContract.View infoView) {
        this.infoView = infoView;
        this.infoModel = new InfoModel();
    }

    @Override
    public void callClubInfoByClubId(@NonNull Long clubId) {
        infoModel.callClubInfoByClubId(clubId, new InfoModel.ApiListener() {
            @Override
            public void onSuccess(InfoDto infoDto) {
                infoView.setClubInfo(infoDto.getClubDto());
                adapterModel.addItems(infoDto.getMemberDtos());
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
        MemberDto memberDto = adapterModel.getItem(position);
        infoView.startMemberInfoActivity(memberDto);
    }

    @Override
    public void setMemberListAdapterView(@NonNull MemberListAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnMemberItemClickListener(this);
    }

    @Override
    public void setMemberListAdapterModel(@NonNull MemberListAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
