package com.youngman.mop.view.clubs.adapter;

import com.youngman.mop.data.Club;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-03.
 */

public interface ClubsAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        Club getItem(int position);
        void addItems(List<Club> clubs);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }
}
