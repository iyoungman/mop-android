package com.youngman.mop.data;

import android.support.annotation.NonNull;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-06.
 */

@Getter
public class SignIn {

    private String email;
    private String pw;


    @Builder
    public SignIn(@NonNull String email, @NonNull String pw) {
        this.email = email;
        this.pw = pw;
    }

    /**
     * email, pw 가 빈 문자열인지 검사
     */
    public boolean checkData() {
        long count =  Stream.of(email, pw)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 2;
        return isAllNonNull.test(count);
    }
}
