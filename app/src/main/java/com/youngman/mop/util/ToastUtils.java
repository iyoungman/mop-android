package com.youngman.mop.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class ToastUtils {

    public static void showToast(@NonNull Context context, @NonNull String title) {
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
    }
}
