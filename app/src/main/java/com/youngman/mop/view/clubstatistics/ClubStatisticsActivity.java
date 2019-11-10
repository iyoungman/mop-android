package com.youngman.mop.view.clubstatistics;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.ClubSignCount;
import com.youngman.mop.util.DateUtils;
import com.youngman.mop.util.LogUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.clubstatistics.presenter.ClubStatisticsContract;
import com.youngman.mop.view.clubstatistics.presenter.ClubStatisticsPresenter;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.time.LocalDate;
import java.util.List;

public class ClubStatisticsActivity extends AppCompatActivity implements ClubStatisticsContract.View {

    private Context context;
    private BarChart barChartSignCount;
    private TextView tvDateRange;
    private TextView tvCurrentCount;
    private TextView tvAllCount;
    private TextView tvOutCount;
    private ClubStatisticsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_statistics);
        init();
    }

    private void init() {
        context = getApplicationContext();
        barChartSignCount = (BarChart) findViewById(R.id.bar_chart_sign_count);
        tvDateRange = (TextView) findViewById(R.id.tv_date_range);
        tvCurrentCount = (TextView) findViewById(R.id.tv_current_count);
        tvAllCount = (TextView) findViewById(R.id.tv_all_count);
        tvOutCount = (TextView) findViewById(R.id.tv_out_count);
        presenter = new ClubStatisticsPresenter(this);
        Long clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", -1);

        presenter.callSignCount(clubId);
    }

    @Override
    public void drawBarChart(List<BarModel> barModels) {
        try {
            barChartSignCount.clearChart();
            barChartSignCount.addBarList(barModels);
            barChartSignCount.startAnimation();
        } catch (Exception e) {
            LogUtils.logInfo(e.getMessage());
        }
    }

    @Override
    public void setDateRange(String startDate, String endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(startDate + " ~ " + endDate);
        sb.append(")");

        tvDateRange.setText(sb.toString());
    }

    @Override
    public void setSignCount(int currentCount, int allCount) {
        tvCurrentCount.setText(currentCount + "명");
        tvAllCount.setText(allCount + "명");
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

}
