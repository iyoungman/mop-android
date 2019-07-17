package com.youngman.mop.view.myclubs.adapter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.Club;
import com.youngman.mop.listener.OnMyClubsItemClickListener;

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
