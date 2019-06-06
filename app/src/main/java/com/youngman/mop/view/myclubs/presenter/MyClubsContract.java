package com.youngman.mop.view.myclubs.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.view.myclubs.adapter.MyClubsAdapterContract;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubsContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startClubActivity(@NonNull Long clubId);
    }

    interface Presenter {
        void callMyClubList(@NonNull String userId);
        void setMyClubAdapterView(@NonNull MyClubsAdapterContract.View adapterView);
        void setMyClubAdapterModel(@NonNull MyClubsAdapterContract.Model adapterModel);
    }
}
