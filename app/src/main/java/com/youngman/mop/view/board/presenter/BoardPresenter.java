package com.youngman.mop.view.board.presenter;

import com.youngman.mop.data.BoardPagingResponse;
import com.youngman.mop.data.source.board.BoardRepository;
import com.youngman.mop.data.source.board.BoardSource;
import com.youngman.mop.listener.OnBasicItemClickListener;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.board.adpater.BoardAdapterContract;

/**
 * Created by YoungMan on 2019-07-09.
 */

public class BoardPresenter implements BoardContract.Presenter, OnBasicItemClickListener {

    private BoardContract.View boardView;
    private final BoardRepository boardRepository;

    private BoardAdapterContract.View adapterView;
    private BoardAdapterContract.Model adapterModel;


    public BoardPresenter(BoardContract.View boardView, BoardRepository boardRepository) {
        this.boardView = boardView;
        this.boardRepository = boardRepository;
    }

    @Override
    public void callBoardByClub(Long clubId, int pageNo) {
        boardRepository.callBoardsByClub(clubId, pageNo, new BoardSource.ListApiListener() {
            @Override
            public void onSuccess(BoardPagingResponse boardPagingResponse) {
                adapterModel.addItems(boardPagingResponse.getNotices());
                adapterModel.addItems(boardPagingResponse.getPosts());
                adapterView.notifyAdapter();
                adapterModel.setIsLast(boardPagingResponse.isLast());
                adapterModel.setMoreLoading(false);
            }

            @Override
            public void onFail(String message) {
                boardView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void onStartItemClick(int position) {
//        Long boardId = adapterModel.getItem(position).getId();
        boardView.startBoardDetailActivity(0L);
    }

    @Override
    public void setBoardAdapterView(BoardAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setBoardAdapterModel(BoardAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
