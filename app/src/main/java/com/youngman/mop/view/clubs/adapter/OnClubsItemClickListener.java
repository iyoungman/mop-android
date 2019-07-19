package com.youngman.mop.view.clubs.adapter;

/**
 * Created by YoungMan on 2019-07-19.
 */

public interface OnClubsItemClickListener {
    void onStartClubClick(Long clubId);
    void onJoinClubClick(String email, Long clubId);
}
