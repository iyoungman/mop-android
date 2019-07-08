package com.youngman.mop.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-07-08.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInResponse {

    private String token;
    private String email;
    private String name;


    @Builder
    public SignInResponse(String token,
                          String email,
                          String name) {
        this.token = token;
        this.email = email;
        this.name = name;
    }
}
