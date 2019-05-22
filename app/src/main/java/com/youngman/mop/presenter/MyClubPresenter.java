package com.youngman.mop.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.contract.MyClubContract;
import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.domain.MyClubModel;
import com.youngman.mop.model.dto.MyClubDto;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubPresenter implements MyClubContract.Presenter, OnMyClubItemClickListener {

    private MyClubContract.View myClubView;
    private MyClubModel myClubModel;
    private MyClubAdapterContract.View adapterView;
    private MyClubAdapterContract.Model adapterModel;

    public MyClubPresenter(@NonNull MyClubContract.View myClubView) {
        this.myClubView = myClubView;
        this.myClubModel = new MyClubModel();
    }

    @Override
    public void callMyClubList(@NonNull String userId) {
            myClubModel.callMyClubList(userId, new MyClubModel.ListApiListener() {
                @Override
                public void onSuccess(MyClubDto myClubDto) {
                    adapterModel.addItems(myClubDto.getClubDtoList());
                    adapterView.notifyAdapter();
                }
                @Override
                public void onFail(String message) {
                    myClubView.showErrorMessage(message);
                }
            });
    }

    @Override
    public void onDeleteMyClubClick(@NonNull int position) {
        String myClubId = adapterModel.getItem(position).getId();
        myClubModel.callDeleteMyClubModel(position, myClubId, new MyClubModel.DeleteApiListener() {
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
    public void onStartMyClubClick(@NonNull int position) {
        String myClubId = adapterModel.getItem(position).getId();
        myClubView.startClubActivity(myClubId);
    }

    @Override
    public void setMyClubAdapterView(@NonNull MyClubAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setMyClubAdapterModel(@NonNull MyClubAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
