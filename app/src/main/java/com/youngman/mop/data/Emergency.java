package com.youngman.mop.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-10-14.
 */

@Getter
public class Emergency {

    private String sender;
    private List<String> otherEmails = new ArrayList<>();

    @Builder
    public Emergency(String sender, List<String> otherEmails) {
        this.sender = sender;
        this.otherEmails = otherEmails;
    }
}
