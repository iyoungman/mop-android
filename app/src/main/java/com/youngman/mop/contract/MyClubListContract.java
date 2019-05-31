package com.youngman.mop.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.MyClubListAdapterContract;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubListContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startClubActivity(@NonNull Long clubId);
    }

    interface Presenter {
        void callMyClubList(@NonNull String userId);
        void setMyClubAdapterView(@NonNull MyClubListAdapterContract.View adapterView);
        void setMyClubAdapterModel(@NonNull MyClubListAdapterContract.Model adapterModel);
    }
}
