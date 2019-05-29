package com.youngman.mop.listener;

import lombok.NonNull;

/**
 * Created by YoungMan on 2019-05-02.
 */

public interface OnMyClubItemClickListener {
    void onDeleteMyClubClick(String email, int position);
//    void onAddMyClubClick(int position);
    void onStartMyClubClick(@NonNull Integer position);
}
