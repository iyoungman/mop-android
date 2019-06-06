package com.youngman.mop.view.myclubs.presenter;

import android.support.annotation.NonNull;

import com.youngman.mop.data.MyClubsResponse;
import com.youngman.mop.data.source.myclubs.MyClubsRepository;
import com.youngman.mop.data.source.myclubs.MyClubsSource;
import com.youngman.mop.view.myclubs.adapter.MyClubsAdapterContract;
import com.youngman.mop.listener.OnMyClubsItemClickListener;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubsPresenter implements MyClubsContract.Presenter, OnMyClubsItemClickListener {

    private MyClubsContract.View myClubView;
    private final MyClubsRepository myClubListRepository;

    private MyClubsAdapterContract.View adapterView;
    private MyClubsAdapterContract.Model adapterModel;


    public MyClubsPresenter(@NonNull MyClubsContract.View myClubView,
                            @NonNull MyClubsRepository myClubListRepository) {
        this.myClubView = myClubView;
        this.myClubListRepository = myClubListRepository;
    }

    @Override
    public void callMyClubList(@NonNull String userId) {
            myClubListRepository.callMyClubList(userId, new MyClubsSource.ListApiListener() {

                @Override
                public void onSuccess(MyClubsResponse myClubsResponse) {
                    adapterModel.addItems(myClubsResponse.getMyClubs());
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
        myClubListRepository.callDeleteMyClubModel(email, clubId, position, new MyClubsSource.DeleteApiListener() {
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
    public void setMyClubAdapterView(@NonNull MyClubsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnMyClubsItemClickListener(this);
    }

    @Override
    public void setMyClubAdapterModel(@NonNull MyClubsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
