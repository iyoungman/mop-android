package com.youngman.mop.view.clubinfo.presenter;

import com.youngman.mop.data.Club;
import com.youngman.mop.data.Member;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapterContract;

/**
 * Created by YoungMan on 2019-05-28.
 */

public interface ClubInfoContract {

    interface View {
        void setClubInfo(Club club);
        void startMemberInfoActivity(Member member);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void callClubInfoByClubId(Long clubId);
        void setMembersAdapterView(MembersAdapterContract.View adapterView);
        void setMembersAdapterModel(MembersAdapterContract.Model adapterModel);
    }
}
