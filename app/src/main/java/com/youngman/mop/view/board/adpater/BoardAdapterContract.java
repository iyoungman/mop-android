package com.youngman.mop.view.board.adpater;

import com.youngman.mop.data.Board;

import java.util.List;

/**
 * Created by YoungMan on 2019-07-09.
 */

public interface BoardAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        Board getItem(int position);
        void addItems(List<Board> boards);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }
}
