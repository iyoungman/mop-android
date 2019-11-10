package com.youngman.mop.view.clubstatistics.presenter;

import org.eazegraph.lib.models.BarModel;

import java.util.List;

/**
 * Created by YoungMan on 2019-11-02.
 */

public interface ClubStatisticsContract {

    interface View {
        void drawBarChart(List<BarModel> barModels);
        void setDateRange(String startDate, String endDate);
        void setSignCount(int currentCount, int allCount);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void callSignCount(Long clubId);
    }
}
