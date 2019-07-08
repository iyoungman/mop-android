package com.youngman.mop.lib.otto;

import android.content.Intent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by YoungMan on 2019-07-08.
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ActivityResultEvent {

    private int requestCode;
    private int resultCode;
    private Intent data;


    public ActivityResultEvent(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }
}

