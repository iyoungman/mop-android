package com.youngman.mop.view.clubs.adapter;

import android.support.annotation.NonNull;

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
        Club getItem(@NonNull Integer position);
        void addItems(@NonNull List<Club> clubs);
        void setIsLast(@NonNull Boolean isLast);
        void setMoreLoading(@NonNull Boolean isMoreLoading);
    }
}
