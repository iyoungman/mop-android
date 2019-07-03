package com.youngman.mop.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-06.
 */

@Getter
public class ClubsResponse {

    @SerializedName("myClubResponseDtos")
    private List<Club> clubs = new ArrayList<>();

    @SerializedName("last")
    private boolean isLast;


    public ClubsResponse(List<Club> clubs, boolean isLast) {
        this.clubs = clubs;
        this.isLast = isLast;
    }
}
