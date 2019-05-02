package com.youngman.mop.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.contract.MyClubContract;
import com.youngman.mop.listener.OnItemClickListener;
import com.youngman.mop.model.domain.MyClubModel;
import com.youngman.mop.model.dto.MyClubDto;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubPresenter implements MyClubContract.Presenter, OnItemClickListener {

    private MyClubContract.View myClubView;
    private MyClubModel myClubModel;
    private MyClubAdapterContract.View adapterView;
    private MyClubAdapterContract.Model adapterModel;

    public MyClubPresenter(@NonNull MyClubContract.View myClubView) {
        this.myClubView = myClubView;
        this.myClubModel = new MyClubModel();
    }

    @Override
    public void setMyClubAdapterView(@NonNull MyClubAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setMyClubAdapterModel(@NonNull MyClubAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void callMyClubList(@NonNull String userId) {
            myClubModel.callMyClubList(userId, new MyClubModel.ListApiListener() {
                @Override
                public void onSuccess(MyClubDto myClubDto) {
                    adapterModel.addItems(myClubDto.getClubDtos());
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
        String myClubId = adapterModel.getClubDto(position).getId();
        myClubModel.callDeleteMyClubModel(myClubId, new MyClubModel.DeleteApiListener() {
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
        //클럽 소개 페이지의 정보를 가져온다.
        //Dto 에 있는 내용으로 충분?
        //ClubModel에 대해서 설계를 한후 생각해야할듯
        //id주고 받아올지 그냐 Dto내용 넘기면 될지
        //동호회 id만 넘겨주자
    }
}
