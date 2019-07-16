package com.youngman.mop.data;

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


    @Builder
    public SignUp(String email,
                  String pw,
                  String name,
                  String mobile,
                  String hobby) {

        this.email = email;
        this.pw = pw;
        this.name = name;
        this.mobile = mobile;
        this.hobby = hobby;
    }

    public boolean isAllNonNull() {
        long count =  Stream.of(email, pw, name, mobile, hobby)
                .filter(data -> !data.isEmpty())
                .count();

        Predicate<Long> isAllNonNull = cnt -> cnt == 5;
        return isAllNonNull.test(count);
    }
}
