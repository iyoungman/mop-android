package com.youngman.mop.view.boardcreate.presenter;

import com.youngman.mop.data.BoardCreateRequest;
import com.youngman.mop.data.source.boardcreate.BoardCreateRemoteDataSource;

/**
 * Created by YoungMan on 2019-11-01.
 */

public class BoardCreatePresenter implements BoardCreateContract.Presenter {

    private BoardCreateContract.View boardCreateView;
    private final BoardCreateRemoteDataSource boardCreateRemoteDataSource;

    public BoardCreatePresenter(BoardCreateContract.View boardCreateView) {
        this.boardCreateView = boardCreateView;
        this.boardCreateRemoteDataSource = BoardCreateRemoteDataSource.getInstance();
    }

    @Override
    public void callCreateBoard(BoardCreateRequest boardCreateRequest) {
        if (boardCreateRequest.isAllNonNull()) {
            boardCreateRemoteDataSource.callCreateBoard(boardCreateRequest, new BoardCreateRemoteDataSource.BoardCreateApiListener() {
                @Override
                public void onSuccess() {
                    boardCreateView.startBoardFragment();
                }
                @Override
                public void onFail(String message) {
                    boardCreateView.showErrorMessage(message);
                }
            });
        }
    }


}
