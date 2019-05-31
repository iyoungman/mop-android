package com.youngman.mop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YoungMan on 2019-05-30.
 */

public class ClubIdIntent implements Parcelable {

    Long clubId;

    public ClubIdIntent(Long clubId) {
        this.clubId = clubId;
    }

    protected ClubIdIntent(Parcel in) {
        this.clubId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<ClubIdIntent> CREATOR = new Creator<ClubIdIntent>() {
        @Override
        public ClubIdIntent createFromParcel(Parcel source) {
            return new ClubIdIntent(source);
        }

        @Override
        public ClubIdIntent[] newArray(int size) {
            return new ClubIdIntent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.clubId);
    }
}
