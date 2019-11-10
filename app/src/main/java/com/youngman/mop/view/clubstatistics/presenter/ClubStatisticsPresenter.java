package com.youngman.mop.view.clubstatistics.presenter;

import com.youngman.mop.data.ClubSignCountResponse;
import com.youngman.mop.data.source.clubstatistics.ClubStatisticsRemoteDataSource;

import org.eazegraph.lib.models.BarModel;

import java.util.List;

/**
 * Created by YoungMan on 2019-11-02.
 */

public class ClubStatisticsPresenter implements ClubStatisticsContract.Presenter {

    private ClubStatisticsContract.View statisticsView;
    private final ClubStatisticsRemoteDataSource clubStatisticsRemoteDataSource;

    public ClubStatisticsPresenter(ClubStatisticsContract.View statiticsView) {
        this.statisticsView = statiticsView;
        this.clubStatisticsRemoteDataSource = ClubStatisticsRemoteDataSource.getInstance();
    }

    @Override
    public void callSignCount(Long clubId) {
        clubStatisticsRemoteDataSource.callSignCount(clubId, new ClubStatisticsRemoteDataSource.SignCountApiListener() {
            @Override
            public void onSuccess(ClubSignCountResponse clubSignCountResponse) {
                List<BarModel> barModels = clubSignCountResponse.toBarModels();
                statisticsView.drawBarChart(barModels);
                statisticsView.setDateRange(clubSignCountResponse.getStartDate(), clubSignCountResponse.getEndDate());
                statisticsView.setSignCount(clubSignCountResponse.getAllSignCount(), clubSignCountResponse.getAllSignCount());
            }

            @Override
            public void onFail(String message) {
                statisticsView.showErrorMessage(message);
            }
        });
    }
}
