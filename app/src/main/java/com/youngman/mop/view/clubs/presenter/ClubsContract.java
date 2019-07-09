package com.youngman.mop.view.clubs.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.view.clubs.adapter.ClubsAdapterContract;

/**
 * Created by YoungMan on 2019-05-03.
 */

public interface ClubsContract {

    interface View {
        void showErrorMessage(String message);
        void startClubActivity(Long clubId);
    }

    interface Presenter {
        void setClubsAdapterView(ClubsAdapterContract.View adapterView);
        void setClubsAdapterModel(ClubsAdapterContract.Model adapterModel);
        void callClubsByUserInfo(String email, int pageNum);
    }
}
