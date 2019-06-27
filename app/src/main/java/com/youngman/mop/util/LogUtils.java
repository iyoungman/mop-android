package com.youngman.mop.util;

import android.util.Log;

/**
 * Created by YoungMan on 2019-06-12.
 */

public class LogUtils {

    private static final String LOG_TAG = "MoP";
    private static final String FORMAT = "[%s]: %s";


    public static void logVerbose(String msg) {
        Log.v(LOG_TAG, String.format(FORMAT, getCallerInfo(), msg));
    }

    public static void logDebug(String msg) {
        Log.d(LOG_TAG, String.format(FORMAT, getCallerInfo(), msg));
    }

    public static void logInfo(String msg) {
        Log.i(LOG_TAG, String.format(FORMAT, getCallerInfo(), msg));
    }

    public static void logWarn(String msg) {
        Log.d(LOG_TAG, String.format(FORMAT, getCallerInfo(), msg));
    }

    public static void logError(String msg) {
        Log.e(LOG_TAG, String.format(FORMAT, getCallerInfo(), msg));
    }

    private static String getCallerInfo() {
        StackTraceElement[] elements = new Exception().getStackTrace();
        String className = elements[2].getClassName();
        return className.substring(className.lastIndexOf(".") + 1, className.length()) + "_" + elements[2].getLineNumber();
    }
}
