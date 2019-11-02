package com.youngman.mop.data;

import com.youngman.mop.lib.fcm.FcmTokenService;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-06.
 */

@Getter
public class SignUp {

    private String email;
    private String pw;
    private String name;
    private String mobile;
    private String hobby;
    private String fcmToken;

    @Builder
    public SignUp(String email, String pw, String name,
                  String mobile, String hobby, String fcmToken) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.mobile = mobile;
        this.hobby = hobby;
        this.fcmToken = fcmToken;
    }

    public boolean isAllNonNull() {
        long count = Stream.of(email, pw, name, mobile, hobby, fcmToken)
                .filter(data -> !data.isEmpty())
                .count();

        Predicate<Long> isAllNonNull = cnt -> cnt == 6;
        return isAllNonNull.test(count);
    }
}
