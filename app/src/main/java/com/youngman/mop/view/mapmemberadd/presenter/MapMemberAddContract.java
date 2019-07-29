package com.youngman.mop.view.mapmemberadd.presenter;

import com.youngman.mop.view.mapmemberadd.adapter.MapMemberAddAdapterContract;

/**
 * Created by YoungMan on 2019-07-26.
 */

public interface MapMemberAddContract {

    interface View {
        void onSuccessCreateAddMember();
        void showErrorMessage(String message);
    }

    interface Presenter {
        void setMapMemberAddAdapterView(MapMemberAddAdapterContract.View adapterView);
        void setMapMemberAddAdapterModel(MapMemberAddAdapterContract.Model adapterModel);
        void callParticipants(Long scheduleId, Long clubId);
        void callCreateAddMember(Long clubId);
    }
}
