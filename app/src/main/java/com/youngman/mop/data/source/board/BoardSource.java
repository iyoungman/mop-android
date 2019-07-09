package com.youngman.mop.data.source.board;

import com.youngman.mop.data.BoardPagingResponse;

/**
 * Created by YoungMan on 2019-07-09.
 */

public interface BoardSource {

    interface ListApiListener {
        void onSuccess(BoardPagingResponse boardPagingResponse);
        void onFail(String message);
    }

    void callBoardsByClub(Long clubId,
                          int pageNo,
                          BoardSource.ListApiListener listener);
}
