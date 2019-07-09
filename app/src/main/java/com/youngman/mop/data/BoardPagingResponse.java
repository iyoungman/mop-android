package com.youngman.mop.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-07-09.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPagingResponse {

    private List<Board> notices = new ArrayList<>();
    private List<Board> posts = new ArrayList<>();

    @SerializedName("last")
    private boolean isLast;


    @Builder
    public BoardPagingResponse(List<Board> notices,
                               List<Board> posts,
                               boolean isLast) {
        this.notices = notices;
        this.posts = posts;
        this.isLast = isLast;
    }
}
