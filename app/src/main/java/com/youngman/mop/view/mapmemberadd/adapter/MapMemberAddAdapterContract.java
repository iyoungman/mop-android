package com.youngman.mop.view.mapmemberadd.adapter;

import com.youngman.mop.data.Participant;

import java.util.List;

/**
 * Created by YoungMan on 2019-07-26.
 */

public interface MapMemberAddAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        Participant getItem(int position);
        void addItems(List<Participant> participants);
    }
}
