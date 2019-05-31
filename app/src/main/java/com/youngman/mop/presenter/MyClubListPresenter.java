package com.youngman.mop.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.MyClubListAdapterContract;
import com.youngman.mop.contract.MyClubListContract;
import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.domain.MyClubListModel;
import com.youngman.mop.model.dto.MyClubListDto;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubListPresenter implements MyClubListContract.Presenter, OnMyClubItemClickListener {

    private MyClubListContract.View myClubView;
    private MyClubListModel myClubListModel;
    private MyClubListAdapterContract.View adapterView;
    private MyClubListAdapterContract.Model adapterModel;


    public MyClubListPresenter(@NonNull MyClubListContract.View myClubView) {
        this.myClubView = myClubView;
        this.myClubListModel = new MyClubListModel();
    }

    @Override
    public void callMyClubList(@NonNull String userId) {
            myClubListModel.callMyClubList(userId, new MyClubListModel.ListApiListener() {
                @Override
                public void onSuccess(MyClubListDto myClubListDto) {
                    adapterModel.addItems(myClubListDto.getClubDtos());
                    adapterView.notifyAdapter();
                }

                @Override
                public void onFail(String message) {
                    myClubView.showErrorMessage(message);
                }
            });
    }

    @Override
    public void onDeleteMyClubClick(@NonNull String email, @NonNull int position) {
        Long clubId = adapterModel.getItem(position).getClubId();
        myClubListModel.callDeleteMyClubModel(email, clubId, position, new MyClubListModel.DeleteApiListener() {
            @Override
            public void onSuccess() {
                adapterModel.deleteItem(position);
                adapterView.notifyAdapter();
            }
            @Override
            public void onFail(String message) {
                myClubView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void onStartMyClubClick(@NonNull Integer position) {
        Long clubId = adapterModel.getItem(position).getClubId();
        myClubView.startClubActivity(clubId);
    }

    @Override
    public void setMyClubAdapterView(@NonNull MyClubListAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnMyClubItemClickListener(this);
    }

    @Override
    public void setMyClubAdapterModel(@NonNull MyClubListAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
