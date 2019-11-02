package com.youngman.mop.view.boardcreate.presenter;

import com.youngman.mop.data.BoardCreateRequest;

/**
 * Created by YoungMan on 2019-11-01.
 */

public interface BoardCreateContract {

    interface View {
        void showErrorMessage(String message);
        void startBoardFragment();
    }

    interface Presenter {
        void callCreateBoard(BoardCreateRequest boardCreateRequest);
    }
}
