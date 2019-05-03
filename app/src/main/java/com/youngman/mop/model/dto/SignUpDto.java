package com.youngman.mop.model.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-04-28.
 */

@Getter
public class SignUpDto {
    private String id;
    private String pw;
    private String name;
    private String mobile;
    private String hobby;

    @Builder
    public SignUpDto(String id, String pw, String name, String mobile, String hobby) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.mobile = mobile;
        this.hobby = hobby;
    }
}
