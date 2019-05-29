package com.youngman.mop.model.dto;

import android.support.annotation.NonNull;

import com.youngman.mop.model.domain.MemberModel;


import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-05-28.
 */

@Getter
public class MemberDto {

    private String email;
    private String name;
    private String mobile;
    private String hobby;
    private String introduce;

    @Builder
    public MemberDto(@NonNull String email, @NonNull String name, @NonNull String mobile,
                     @NonNull String hobby, @NonNull String introduce) {
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.hobby = hobby;
        this.introduce = introduce;
    }

    public static MemberDto of(@NonNull MemberModel memberModel) {
        return MemberDto.builder()
                .email(memberModel.getEmail())
                .name(memberModel.getName())
                .mobile(memberModel.getMobile())
                .hobby(memberModel.getHobby())
                .introduce(memberModel.getIntroduce())
                .build();
    }
}
