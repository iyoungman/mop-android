package com.youngman.mop.view.myclubs.adapter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.Club;
import com.youngman.mop.listener.OnMyClubItemClickListener;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubsAdapterContract {

    interface View {
        void setOnMyClubItemClickListener(@NonNull OnMyClubItemClickListener onMyClubItemClickListener);
        void notifyAdapter();
    }

    interface Model {
        void addItems(@NonNull List<Club> myClubs);
        void deleteItem(@NonNull Integer position);
        Club getItem(@NonNull Integer position);
    }
}
