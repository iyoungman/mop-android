package com.youngman.mop.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.youngman.mop.data.SignInResponse;

/**
 * Created by YoungMan on 2019-05-02.
 */

public class SignUtils {

    public static String readUserIdFromPref(Context context) {
        SharedPreferences token = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return token.getString("PREF_USER_ID", null);
    }

    public static void writeMemberInfoToPref(Context context, SignInResponse signInResponse) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PREF_MEMBER_TOKEN", signInResponse.getToken());
        editor.putString("PREF_MEMBER_EMAIL", signInResponse.getEmail());
        editor.putString("PREF_MEMBER_NAME", signInResponse.getName());
        editor.apply();
    }

    public static String readMemberTokenFromPref(Context context) {
        SharedPreferences token = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return token.getString("PREF_MEMBER_TOKEN", null);
    }

    public static String readMemberEmailFromPref(Context context) {
        SharedPreferences token = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return token.getString("PREF_MEMBER_EMAIL", null);
    }

    public static String readMemberNameFromPref(Context context) {
        SharedPreferences token = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return token.getString("PREF_MEMBER_NAME", null);
    }
}

