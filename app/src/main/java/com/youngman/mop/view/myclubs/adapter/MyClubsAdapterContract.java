package com.youngman.mop.view.myclubs.adapter;

import com.youngman.mop.data.Club;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubsAdapterContract {

    interface View {
        void setOnMyClubsItemClickListener(OnMyClubsItemClickListener onMyClubsItemClickListener);
        void notifyAdapter();
    }

    interface Model {
        void addItems(List<Club> myClubs);
        void deleteItem(int position);
        Club getItem(int position);
    }
}
