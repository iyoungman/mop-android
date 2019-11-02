package com.youngman.mop.data;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-11-01.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateRequest {

    private String title;
    private String content;
    private String writer;
    private BoardType boardType;
    private Long clubId;

    @Builder
    public BoardCreateRequest(String title, String content, String writer,
                              BoardType boardType, Long clubId) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardType = boardType;
        this.clubId = clubId;
    }

    public boolean isAllNonNull() {
        long count =  Stream.of(title, content, writer)
                .filter(data -> !data.isEmpty())
                .count();

        Predicate<Long> isAllNonNull = cnt -> cnt == 3;
        return isAllNonNull.test(count);
    }
}
