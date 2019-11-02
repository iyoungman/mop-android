package com.youngman.mop.data;

import android.graphics.Color;

import org.eazegraph.lib.models.BarModel;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-11-02.
 */

@Getter
public class ClubSignCount {

    private String day;
    private long count;

    public ClubSignCount(String day, long count) {
        this.day = day;
        this.count = count;
    }

    public BarModel toBarModel() {
        return new BarModel(day, count, Color.parseColor("#FFCC80"));
    }

}
