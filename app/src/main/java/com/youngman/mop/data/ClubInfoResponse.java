package com.youngman.mop.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-06.
 */

@Getter
public class ClubInfoResponse {

    @SerializedName("clubInfo")
    private Club club;

    @SerializedName("memberInfos")
    private List<Member> members = new ArrayList<>();


    @Builder
    public ClubInfoResponse(Club club, List<Member> members) {
        this.club = club;
        this.members = members;
    }
}
