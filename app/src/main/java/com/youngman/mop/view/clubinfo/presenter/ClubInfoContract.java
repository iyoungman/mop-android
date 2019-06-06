package com.youngman.mop.view.clubinfo.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.Club;
import com.youngman.mop.data.Member;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapterContract;

/**
 * Created by YoungMan on 2019-05-28.
 */

public interface ClubInfoContract {

    interface View {
        void setClubInfo(@NonNull Club club);
        void startMemberInfoActivity(@NonNull Member member);
        void showErrorMessage(@NonNull String message);
    }

    interface Presenter {
        void callClubInfoByClubId(@NonNull Long clubId);
        void setMemberListAdapterView(@NonNull MembersAdapterContract.View adapterView);
        void setMemberListAdapterModel(@NonNull MembersAdapterContract.Model adapterModel);
    }
}
