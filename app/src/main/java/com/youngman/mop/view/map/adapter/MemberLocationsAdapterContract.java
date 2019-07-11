package com.youngman.mop.view.map.adapter;

import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.listener.OnBasicItemClickListener;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-28.
 */

public interface MemberLocationsAdapterContract {

    interface View {
        void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener);
        void notifyAdapter();
    }

    interface Model {
        void addItems(List<MemberLocation> memberLocations);
        void deleteItem(int position);
        MemberLocation getItem(int position);
    }
}
