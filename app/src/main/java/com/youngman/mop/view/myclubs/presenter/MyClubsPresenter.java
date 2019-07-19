package com.youngman.mop.view.myclubs.presenter;

import com.youngman.mop.data.MyClubsResponse;
import com.youngman.mop.data.source.myclubs.MyClubsRepository;
import com.youngman.mop.data.source.myclubs.MyClubsSource;
import com.youngman.mop.view.myclubs.adapter.OnMyClubsItemClickListener;
import com.youngman.mop.view.myclubs.adapter.MyClubsAdapterContract;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubsPresenter implements MyClubsContract.Presenter, OnMyClubsItemClickListener {

    private MyClubsContract.View myClubView;
    private final MyClubsRepository myClubListRepository;

    private MyClubsAdapterContract.View adapterView;
    private MyClubsAdapterContract.Model adapterModel;


    public MyClubsPresenter(MyClubsContract.View myClubView, MyClubsRepository myClubListRepository) {
        this.myClubView = myClubView;
        this.myClubListRepository = myClubListRepository;
    }

    @Override
    public void callMyClubs(String userId) {
            myClubListRepository.callMyClubs(userId, new MyClubsSource.ListApiListener() {
                @Override
                public void onSuccess(MyClubsResponse myClubsResponse) {
                    adapterModel.addItems(myClubsResponse.getMyClubs());
                    adapterView.notifyAdapter();
                    myClubView.writeMyClubsToPref(myClubsResponse.getMyClubIds());
                }

                @Override
                public void onFail(String message) {
                    myClubView.showErrorMessage(message);
                }
            });
    }

    @Override
    public void onDeleteMyClubClick(String email, int position) {
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
    public void onStartMyClubClick(int position) {
        Long clubId = adapterModel.getItem(position).getClubId();
        String clubName = adapterModel.getItem(position).getName();
        myClubView.startClubActivity(clubId, clubName);
    }

    @Override
    public void setMyClubAdapterView(MyClubsAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnMyClubsItemClickListener(this);
    }

    @Override
    public void setMyClubAdapterModel(MyClubsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
