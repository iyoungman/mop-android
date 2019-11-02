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
    private boolean isChecked;

    public Participant(String name, String email,
                       boolean isParticipate, boolean isChecked) {
        this.name = name;
        this.email = email;
        this.isParticipate = isParticipate;
        this.isChecked = isChecked;
    }

    public String isParticipate() {
        return isParticipate ? "참석" : "미정";
    }

    public void setCheck(boolean check) {
        isChecked = check;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
