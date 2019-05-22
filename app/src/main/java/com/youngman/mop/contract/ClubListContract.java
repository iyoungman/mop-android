package com.youngman.mop.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.ClubListAdapterContract;
import com.youngman.mop.adapter.contract.MyClubAdapterContract;

/**
 * Created by YoungMan on 2019-05-03.
 */

public interface ClubListContract {

    interface View {
        void showErrorMessage(@NonNull String message);
    }

    interface Presenter {
        void setMyClubAdapterView(@NonNull ClubListAdapterContract.View adapterView);
        void setMyClubAdapterModel(@NonNull ClubListAdapterContract.Model adapterModel);
        void callClubListByUserInfo(@NonNull String userId);
        void callClubListBySearch(@NonNull String searchClub);
    }
}
