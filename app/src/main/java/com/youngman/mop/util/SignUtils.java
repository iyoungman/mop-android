package com.youngman.mop.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by YoungMan on 2019-05-02.
 */

public class SignUtils {

    public static void writeUserIdToPref(Context context, String userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PREF_USER_ID", userId);
        editor.apply();
    }

    public static String readUserIdFromPref(Context context) {
        SharedPreferences token = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return token.getString("PREF_USER_ID", null);
    }
}