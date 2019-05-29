package com.youngman.mop.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.InfoDto;

/**
 * Created by YoungMan on 2019-05-28.
 */

public interface InfoContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void setClubInfo(InfoDto infoDto);
    }

    interface Presenter {
        void callClubInfoByClubId(@NonNull Long clubId);
    }
}
