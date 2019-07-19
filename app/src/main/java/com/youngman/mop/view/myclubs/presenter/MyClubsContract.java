package com.youngman.mop.view.myclubs.presenter;

import com.youngman.mop.view.myclubs.adapter.MyClubsAdapterContract;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubsContract {

    interface View {
        void writeMyClubsToPref(List<Long> myClubIds);
        void showErrorMessage(String message);
        void startClubActivity(Long clubId, String clubName);
    }

    interface Presenter {
        void callMyClubs(String userId);
        void setMyClubAdapterView(MyClubsAdapterContract.View adapterView);
        void setMyClubAdapterModel(MyClubsAdapterContract.Model adapterModel);
    }
}
