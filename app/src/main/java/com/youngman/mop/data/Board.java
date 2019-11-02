package com.youngman.mop.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-07-09.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private BoardType boardType;
    private String createdTime;

    @Builder
    public Board(Long id, String title, String content,
                 String writer, BoardType boardType, String createdTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardType = boardType;
        this.createdTime = createdTime;
    }

    public String getStrBoardType() {
        return (boardType.equals(BoardType.NOTICE)) ? "공지" : "자유";
    }

    public String getSimpleTime() {
        String[] split = createdTime.split("T");
        String date = split[0].substring(2);

        String[] split2 = split[1].split(":");
        String time = split2[0] + "시 " + split2[1] + "분";
        return date + " " + time;
    }
}
