package com.youngman.mop.adapter.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.listener.OnMemberItemClickListener;
import com.youngman.mop.model.dto.MemberDto;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-30.
 */

public interface MemberListAdapterContract {

    interface View {
        void setOnMemberItemClickListener(OnMemberItemClickListener onMemberItemClickListener);
        void notifyAdapter();
    }

    interface Model {
        void addItems(@NonNull List<MemberDto> memberDtos);
        void deleteItem(@NonNull Integer position);
        MemberDto getItem(@NonNull Integer position);
    }
}
