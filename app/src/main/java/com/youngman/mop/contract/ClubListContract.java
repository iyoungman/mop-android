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
        void startClubActivity(@NonNull String clubId);
    }

    interface Presenter {
        void setClubListAdapterView(@NonNull ClubListAdapterContract.View adapterView);
        void setClubListAdapterModel(@NonNull ClubListAdapterContract.Model adapterModel);
        void callClubListByUserInfo(@NonNull String userId);
        void callClubListBySearch(@NonNull String searchClub);
    }
}
