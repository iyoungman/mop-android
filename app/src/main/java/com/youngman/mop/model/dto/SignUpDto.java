package com.youngman.mop.model.dto;

import android.support.annotation.NonNull;

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
    public SignUpDto(@NonNull String id,
                     @NonNull String pw,
                     @NonNull String name,
                     @NonNull String mobile,
                     @NonNull String hobby) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.mobile = mobile;
        this.hobby = hobby;
    }
}
