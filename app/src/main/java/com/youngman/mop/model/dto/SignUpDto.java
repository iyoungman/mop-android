package com.youngman.mop.model.dto;

import android.support.annotation.NonNull;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-04-28.
 */

@Getter
public class SignUpDto {

    private String email;
    private String pw;
    private String name;
    private String mobile;
    private String hobby;

    @Builder
    public SignUpDto(@NonNull String email, @NonNull String pw, @NonNull String name,
                     @NonNull String mobile, @NonNull String hobby) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.mobile = mobile;
        this.hobby = hobby;
    }
}
