package com.youngman.mop.view.clubinfo.adapter;

import com.youngman.mop.data.Member;
import com.youngman.mop.listener.OnBasicItemClickListener;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-30.
 */

public interface MembersAdapterContract {

    interface View {
        void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener);
        void notifyAdapter();
    }

    interface Model {
        void addItems(List<Member> members);
        void deleteItem(int position);
        Member getItem(int position);
    }
}
