package com.youngman.mop.data.source.board;

/**
 * Created by YoungMan on 2019-07-09.
 */

public class BoardRepository implements BoardSource{

    private static BoardRepository boardRepository;
    private BoardRemoteDataSource boardRemoteDataSource;


    public static BoardRepository getInstance() {
        if (boardRepository == null) {
            boardRepository = new BoardRepository();
        }

        return boardRepository;
    }

    private BoardRepository() {
        boardRemoteDataSource = BoardRemoteDataSource.getInstance();
    }

    @Override
    public void callBoardsByClub(Long clubId,
                                 int pageNo,
                                 ListApiListener listener) {

        boardRemoteDataSource.callBoardsByClub(clubId, pageNo, listener);
    }
}
