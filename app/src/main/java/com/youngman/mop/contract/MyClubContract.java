package com.youngman.mop.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.model.domain.MyClubModel;
import com.youngman.mop.model.dto.MyClubDto;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startClubActivity(@NonNull Long clubId);
    }

    interface Presenter {
        void setMyClubAdapterView(@NonNull MyClubAdapterContract.View adapterView);
        void setMyClubAdapterModel(@NonNull MyClubAdapterContract.Model adapterModel);
        void callMyClubList(@NonNull String userId);
    }
}
