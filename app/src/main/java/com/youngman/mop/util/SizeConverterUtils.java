package com.youngman.mop.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by YoungMan on 2019-06-30.
 */

public class SizeConverterUtils {

    public static int convertToDp(int value, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
