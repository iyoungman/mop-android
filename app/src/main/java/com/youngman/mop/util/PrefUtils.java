package com.youngman.mop.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.youngman.mop.data.SignInResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-02.
 */

public class PrefUtils {

    public static void writeMemberInfoTo(Context context, SignInResponse signInResponse) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PREF_MEMBER_TOKEN", signInResponse.getToken());
        editor.putString("PREF_MEMBER_EMAIL", signInResponse.getEmail());
        editor.putString("PREF_MEMBER_NAME", signInResponse.getName());
        editor.apply();
    }

    public static String readMemberTokenFrom(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return sharedPreferences.getString("PREF_MEMBER_TOKEN", null);
    }

    public static String readMemberEmailFrom(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return sharedPreferences.getString("PREF_MEMBER_EMAIL", null);
    }

    public static String readMemberNameFrom(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return sharedPreferences.getString("PREF_MEMBER_NAME", null);
    }

    public static void writeAutoSignInTo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("PREF_AUTO_SIGNIN", true);
        editor.apply();
    }

    public static boolean readAutoSignInFrom(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("PREF_AUTO_SIGNIN", false);
    }

    public static void writeMyClubIdsTo(Context context, List<Long> myClubIds) {
        Type listType = new TypeToken<ArrayList<Long>>() {}.getType();
        String json = new GsonBuilder().create()
                .toJson(myClubIds, listType);

        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PREF_MY_CLUB_IDS", json);
        editor.apply();
    }

    public static List<Long> readMyClubIdsFrom(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_MOP", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("PREF_MY_CLUB_IDS", null);

        Type listType = new TypeToken<ArrayList<Long>>() {}.getType();
        return new GsonBuilder().create()
                .fromJson(json, listType);
    }
}

