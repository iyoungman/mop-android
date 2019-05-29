package com.youngman.mop.presenter;

import com.youngman.mop.contract.InfoContract;
import com.youngman.mop.model.domain.InfoModel;
import com.youngman.mop.model.dto.ClubListDto;
import com.youngman.mop.model.dto.InfoDto;

import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-05-28.
 */

public class InfoPresenter implements InfoContract.Presenter {

    private InfoContract.View infoView;
    private InfoModel infoModel;

    public InfoPresenter(@NonNull InfoContract.View infoView) {
        this.infoView = infoView;
        this.infoModel = new InfoModel();
    }

    @Override
    public void callClubInfoByClubId(@NonNull Long clubId) {
        infoModel.callClubInfoByClubId(clubId, new InfoModel.ApiListener() {
            @Override
            public void onSuccess(InfoDto infoDto) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
