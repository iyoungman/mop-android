package com.youngman.mop.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-06.
 */

@Getter
public class MyClubsResponse {

    private List<Club> myClubs = new ArrayList<>();


    public MyClubsResponse(List<Club> myClubs) {
        this.myClubs = myClubs;
    }
}
