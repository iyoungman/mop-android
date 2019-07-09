package com.youngman.mop.view.board.presenter;

import com.youngman.mop.view.board.adpater.BoardAdapterContract;

/**
 * Created by YoungMan on 2019-07-09.
 */

public interface BoardContract {

    interface View {
        void showErrorMessage(String message);
        void startBoardDetailActivity(Long boardId);
    }

    interface Presenter {
        void callBoardByClub(Long clubId, int pageNo);
        void setBoardAdapterView(BoardAdapterContract.View adapterView);
        void setBoardAdapterModel(BoardAdapterContract.Model adapterModel);
    }
}
