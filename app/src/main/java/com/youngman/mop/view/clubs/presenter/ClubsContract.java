package com.youngman.mop.view.clubs.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.view.clubs.adapter.ClubsAdapterContract;

/**
 * Created by YoungMan on 2019-05-03.
 */

public interface ClubsContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startClubActivity(@NonNull Long clubId);
    }

    interface Presenter {
        void setClubListAdapterView(@NonNull ClubsAdapterContract.View adapterView);
        void setClubListAdapterModel(@NonNull ClubsAdapterContract.Model adapterModel);
        void callClubListByUserInfo(@NonNull String email, @NonNull Integer pageNum);
//        void callPagingClubsBySearch(@NonNull String searchClub);
    }
}
