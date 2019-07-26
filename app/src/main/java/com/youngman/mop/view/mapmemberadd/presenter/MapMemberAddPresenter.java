package com.youngman.mop.view.mapmemberadd.presenter;

import com.youngman.mop.data.Participant;
import com.youngman.mop.data.source.mapmemberadd.MapMemberAddRemoteDataSource;
import com.youngman.mop.view.mapmemberadd.adapter.MapMemberAddAdapterContract;

import java.util.List;

/**
 * Created by YoungMan on 2019-07-26.
 */

public class MapMemberAddPresenter implements MapMemberAddContract.Presenter {

    private MapMemberAddContract.View mapMemberAddView;
    private MapMemberAddAdapterContract.View adapterView;
    private MapMemberAddAdapterContract.Model adapterModel;
    private MapMemberAddRemoteDataSource mapMemberAddRemoteDataSource;

    public MapMemberAddPresenter(MapMemberAddContract.View mapMemberAddView) {
        this.mapMemberAddView = mapMemberAddView;
        mapMemberAddRemoteDataSource = MapMemberAddRemoteDataSource.getInstance();
    }

    @Override
    public void callParticipants(Long scheduleId, Long clubId) {
        mapMemberAddRemoteDataSource.callParticipants(scheduleId, clubId, new MapMemberAddRemoteDataSource.ParticipantsApiListener() {
            @Override
            public void onSuccess(List<Participant> participants) {
                adapterModel.addItems(participants);
                adapterView.notifyAdapter();
            }
            @Override
            public void onFail(String message) {
                mapMemberAddView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void setMapMemberAddAdapterView(MapMemberAddAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setMapMemberAddAdapterModel(MapMemberAddAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
