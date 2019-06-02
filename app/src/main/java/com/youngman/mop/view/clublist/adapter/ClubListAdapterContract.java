package com.youngman.mop.view.clublist.adapter;

import android.support.annotation.NonNull;

import com.youngman.mop.model.dto.ClubDto;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-03.
 */

public interface ClubListAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        ClubDto getItem(@NonNull Integer position);
        void addItems(@NonNull List<ClubDto> clubDtos);
        void setIsLast(@NonNull Boolean isLast);
        void setMoreLoading(@NonNull Boolean isMoreLoading);
    }
}
