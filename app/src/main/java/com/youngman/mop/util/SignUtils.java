package com.youngman.mop.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by YoungMan on 2019-05-02.
 */

public class SignUtils {

    public static void writeUserIdToPref(@NonNull Context context, @NonNull String userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PREF_USER_ID", userId);
        editor.apply();
    }

    public static String readUserIdFromPref(@NonNull Context context) {
        SharedPreferences token = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return token.getString("PREF_USER_ID", null);
    }
}
