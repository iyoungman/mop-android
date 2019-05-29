package com.youngman.mop.adapter.contract;

import android.support.annotation.NonNull;

import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public interface MyClubAdapterContract {

    interface View {
        void setOnMyClubItemClickListener(@NonNull OnMyClubItemClickListener onMyClubItemClickListener);
        void notifyAdapter();
    }

    interface Model {
        void addItems(@NonNull List<ClubDto> clubDtos);
        void deleteItem(@NonNull Integer position);
        ClubDto getItem(@NonNull Integer position);
    }
}
