package com.youngman.mop.view.clubinfo.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.view.clubinfo.adapter.MemberListAdapterContract;
import com.youngman.mop.model.dto.ClubDto;
import com.youngman.mop.model.dto.MemberDto;

/**
 * Created by YoungMan on 2019-05-28.
 */

public interface InfoContract {

    interface View {
        void setClubInfo(@NonNull ClubDto clubDto);
        void startMemberInfoActivity(@NonNull MemberDto memberDto);
        void showErrorMessage(@NonNull String message);
    }

    interface Presenter {
        void callClubInfoByClubId(@NonNull Long clubId);
        void setMemberListAdapterView(@NonNull MemberListAdapterContract.View adapterView);
        void setMemberListAdapterModel(@NonNull MemberListAdapterContract.Model adapterModel);
    }
}
