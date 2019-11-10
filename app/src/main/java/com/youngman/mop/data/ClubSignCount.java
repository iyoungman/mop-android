package com.youngman.mop.data;

import android.graphics.Color;

import com.youngman.mop.R;

import org.eazegraph.lib.models.BarModel;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-11-02.
 */

@Getter
public class ClubSignCount {

    private String statisticsDate;
    private String day;
    private long count;

    public ClubSignCount(String statisticsDate, String day, long count) {
        this.statisticsDate = statisticsDate;
        this.day = day;
        this.count = count;
    }

    public BarModel toBarModel() {
        return new BarModel(day, count, Color.parseColor("#FFE082"));
    }

}
