package com.youngman.mop.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.MyClubDto;

/**
 * Created by YoungMan on 2019-04-29.
 */

public interface SignInContract {

    interface View {
        void showErrorMessage(@NonNull String message);
        void startMyClubActivity(@NonNull String userId);
    }

    interface Presenter {
        void callSignIn(@NonNull String id, @NonNull String pw);
    }
}
