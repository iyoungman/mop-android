package com.youngman.mop.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.ClubListAdapterContract;

/**
 * Created by YoungMan on 2019-05-03.
 */

public interface ClubListContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startClubActivity(@NonNull Long clubId);
    }

    interface Presenter {
        void setClubListAdapterView(@NonNull ClubListAdapterContract.View adapterView);
        void setClubListAdapterModel(@NonNull ClubListAdapterContract.Model adapterModel);
        void callClubListByUserInfo(@NonNull String email, @NonNull Integer pageNum);
//        void callPagingClubsBySearch(@NonNull String searchClub);
    }
}
