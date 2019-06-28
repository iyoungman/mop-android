package com.youngman.mop.view.myclubs.presenter;

import com.youngman.mop.view.myclubs.adapter.MyClubsAdapterContract;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubsContract {

    interface View {
        void showErrorMessage(String message);
        void startClubActivity(Long clubId);
    }

    interface Presenter {
        void callMyClubList(String userId);
        void setMyClubAdapterView(MyClubsAdapterContract.View adapterView);
        void setMyClubAdapterModel(MyClubsAdapterContract.Model adapterModel);
    }
}
