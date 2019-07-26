package com.youngman.mop.data;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-07-26.
 */

@Getter
public class Participant {

    private String name;
    private String email;
    private boolean isParticipate;

    public Participant(String name, String email, boolean isParticipate) {
        this.name = name;
        this.email = email;
        this.isParticipate = isParticipate;
    }

    public String isParticipate() {
        return isParticipate ? "참석" : "미정";
    }
}
