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
    public Board(Long id,
                 String title,
                 String content,
                 String writer,
                 BoardType boardType,
                 String createdTime) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardType = boardType;
        this.createdTime = createdTime;
    }

    public String getStrBoardType() {
        return (this.boardType.equals(BoardType.NOTICE)) ? "공지" : "게시판";
    }
}