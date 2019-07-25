package com.youngman.mop.view.club.presenter;

/**
 * Created by YoungMan on 2019-07-25.
 */

public interface ClubContract {

    interface View {
        void setIsClubChair(boolean isClubChair);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void callIsClubChair(Long clubId, String email);
    }
}
