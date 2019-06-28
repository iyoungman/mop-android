package com.youngman.mop.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YoungMan on 2019-04-28.
 */

public class ToastUtils {

  public static void showToast(Context context, String title) {
    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
  }
}