package com.youngman.mop.data;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-06.
 */

@Getter
public class Member {

    private String email;
    private String name;
    private String mobile;
    private String hobby;
    private String introduce;


    @Builder
    public Member(String email,
                  String name,
                  String mobile,
                  String hobby,
                  String introduce) {

        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.hobby = hobby;
        this.introduce = introduce;
    }
}
